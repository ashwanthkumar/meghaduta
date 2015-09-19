package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Predicate;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.Subscription;

import java.util.List;
import java.util.Map;

public class MatchSubscription extends BaseRichBolt {
    OutputCollector collector;
    List<Subscription> subscriptions;

    public MatchSubscription(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Item item = (Item) tuple.getValue(0);
        Event event = (Event) tuple.getValue(1);

        List<Subscription> matchedSubscriptions = Lists.filter(subscriptions, new Predicate<Subscription>() {
            @Override
            public Boolean apply(Subscription input) {
//                return input.matches(input);
                return false;
            }
        });
        collector.emit(Lists.of(matchedSubscriptions, item, event));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("subscriptions", "event", "item"));
    }
}
