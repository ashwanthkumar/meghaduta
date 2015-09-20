package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Iterables;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import meghaduta.models.Event;
import meghaduta.parser.CSV;
import meghaduta.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Parses a string as CSV and emits Event model
 */
public class LineProcessor extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(LineProcessor.class);

    OutputCollector collector;
    private Parser<List<Event>> parser;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        parser = new CSV();
    }

    @Override
    public void execute(Tuple tuple) {
        String line = tuple.getStringByField("line");
        try {
            List<Event> events = parser.parse(line);
            Iterables.foreach(events, new Function<Event, Void>() {
                @Override
                public Void apply(Event input) {
                    collector.emit(Lists.<Object>of(input));
                    return null;
                }
            });
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("event"));
    }
}
