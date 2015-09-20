package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.config.MDConfig;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.store.Store;
import meghaduta.store.StoreFactory;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Takes an Event and converts it to an Item, uses Store reference to do that.
 */
public class Event2Item extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(Event2Item.class);
    Store store;
    MDConfig config;
    OutputCollector collector;

    public Event2Item(MDConfig config) {
        this.config = config;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
        this.store = StoreFactory.get(config.getStoreImpl());
        try {
            store.init(config);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            Event event = (Event) tuple.getValue(0);
            Item item = store.get(event.getItemId());
            if(item == null) {
                item = new Item();
            }

            if (event.getTimestamp() >= item.getLastUpdated()) {
                item.update(event);
                store.put(event);
                collector.emit(Lists.<Object>of(item, event));
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("item", "event"));
    }
}
