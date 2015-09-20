package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import in.ashwanthkumar.utils.collections.Iterables;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.StringUtils;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class MatchSubscription extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(MatchSubscription.class);

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
        final Item item = (Item) tuple.getValue(0);
        final Event event = (Event) tuple.getValue(1);

        List<Subscription> matchedSubscriptions = Lists.filter(subscriptions, new Predicate<Subscription>() {
            @Override
            public Boolean apply(Subscription subscription) {
                // Raise a notification only if the item that matches the condition has some changes via the event
                List<String> attributesInFilter = subscription.getFilter().getAttributes();
                boolean filterAttributesPresentInItem = Iterables.forall(attributesInFilter, new Predicate<String>() {
                    @Override
                    public Boolean apply(String input) {
                        return StringUtils.isNotEmpty(item.getAttributes().get(input));
                    }
                });
                return filterAttributesPresentInItem && subscription.matches(item) && attributesInFilter.contains(event.getName());
            }
        });
        if (!matchedSubscriptions.isEmpty()) collector.emit(Lists.of(matchedSubscriptions, event, item));
        else {
            LOG.info("{} does not match any subscription", item);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("subscriptions", "event", "item"));
    }
}
