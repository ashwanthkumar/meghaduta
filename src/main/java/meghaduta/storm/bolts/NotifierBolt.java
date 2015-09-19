package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Iterables;
import in.ashwanthkumar.utils.func.Function;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.Subscription;
import meghaduta.notifier.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Takes an Event and with known set of subscriptions invokes the corresponding notifier
 */
public class NotifierBolt extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(NotifierBolt.class);

    OutputCollector collector;
    Notifier notifier;

    public NotifierBolt(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        List<Subscription> subscriptions = (List<Subscription>) tuple.getValue(0);
        final Event event = (Event) tuple.getValue(1);
        final Item item = (Item) tuple.getValue(2);
        Iterables.foreach(subscriptions, new Function<Subscription, Void>() {
            @Override
            public Void apply(Subscription input) {
                try {
                    notifier.notify(event, item, input.getNotifier());
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
                return null;
            }
        });
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }
}
