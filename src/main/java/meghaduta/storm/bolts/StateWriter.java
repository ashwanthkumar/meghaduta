package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.models.Event;
import meghaduta.store.Store;

import java.util.Map;

/***
 * Takes an Event and persists it to a Store
 */
public class StateWriter extends BaseRichBolt {
    Store store;
    OutputCollector collector;

    public StateWriter(Store store) {
        this.store = store;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Event event = (Event) tuple.getValue(0);
        collector.emit(Lists.<Object>of(event));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("event"));
    }
}
