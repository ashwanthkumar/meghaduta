package meghaduta.storm.spouts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.handlers.FileWatcher;
import meghaduta.handlers.NewFileCreatedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

public class LocalFileSpout extends BaseRichSpout {
    private static final Logger LOG = LoggerFactory.getLogger(LocalFileSpout.class);

    private SpoutOutputCollector collector;
    private FileWatcher watcher;
    private String directoryToWatch;

    public LocalFileSpout(String directoryToWatch) {
        this.directoryToWatch = directoryToWatch;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
        try {
            watcher = new FileWatcher(new NewFileCreatedHandler() {
                @Override
                public void handle(File file) {
                    collector.emit(Lists.<Object>of(file.getAbsolutePath()));
                }
            });
            watcher.start(new File(directoryToWatch));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void nextTuple() {
        watcher.handle();
    }
}
