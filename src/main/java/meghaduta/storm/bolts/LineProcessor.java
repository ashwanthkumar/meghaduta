package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.models.Event;
import meghaduta.parser.NoopParser;
import meghaduta.parser.Parser;

import java.util.Map;

/**
 * Parses a string as CSV and emits Event model
 */
public class LineProcessor extends BaseRichBolt {
    OutputCollector collector;
    private Parser parser;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        // FIXME - Use a proper parser
        parser = new NoopParser();
    }

    @Override
    public void execute(Tuple tuple) {
        String line = tuple.getStringByField("line");
        // parser.parse(line)
        Event event = new Event().setItemId("itemId").setName("name").setTimestamp("123456").setValue("foobar");
        collector.emit(Lists.<Object>of(event));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("event"));
    }
}
