package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.store.Store;

import java.util.Map;

/**
 * Takes an Event and converts it to an Item, uses Store reference to do that.
 */
public class Event2Item extends BaseRichBolt {
    Store store;
    OutputCollector collector;

    public Event2Item(Store store) {
        this.store = store;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            Event event = (Event) tuple.getValue(0);
            Item item = store.get(event.getItemId());
            collector.emit(Lists.<Object>of(item, event));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("item", "event"));
    }
}
