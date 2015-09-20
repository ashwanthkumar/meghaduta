package meghaduta.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import meghaduta.config.MDConfig;
import meghaduta.config.MDConfigReader;
import meghaduta.filters.serde.HoconSubscriptionReader;
import meghaduta.models.Subscription;
import meghaduta.notifier.FileNotifier;
import meghaduta.store.Store;
import meghaduta.store.StoreFactory;
import meghaduta.storm.bolts.*;
import meghaduta.storm.spouts.LocalFileSpout;

import java.util.List;

public class MeghaDutaTopology {
    public static void main(String[] args) throws Exception {
        MDConfig appConfig = MDConfigReader.load();
        List<Subscription> subscriptions = HoconSubscriptionReader.read();
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("filewatcher", new LocalFileSpout(appConfig.getSharedFolder()), 1);
        builder.setBolt("fileprocessor", new FileProcessor(appConfig.getSharedFolder()), 5)
                .shuffleGrouping("filewatcher");
        builder.setBolt("lineprocessor", new LineProcessor(), 5)
                .shuffleGrouping("fileprocessor");
        builder.setBolt("event2item", new Event2Item(appConfig), 1)
                .shuffleGrouping("lineprocessor"); // FIXME - bottle-neck
        builder.setBolt("matchsubscriptions", new MatchSubscription(subscriptions), 5)
                .shuffleGrouping("event2item");
        builder.setBolt("notifier", new NotifierBolt(new FileNotifier(appConfig.getNotifierFileName())), 5)
                .shuffleGrouping("matchsubscriptions");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);

            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        }
        else {

            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(600 * 1000); // 10 min
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }

}
