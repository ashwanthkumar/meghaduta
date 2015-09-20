package meghaduta.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.reader.LocalFileReader;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Takes a file name and reads the file as lines and emits them out
 * <p/>
 * NB: You might need to set a very high level of parallelism for this bolt since it reads a file name
 * and emits all the lines in the file as tuples out. #execute is a blocking call where it reads the
 * entire file.
 * <p/>
 * FIXME - Move this to a more iterator based approach so that we don't put Storm under too much pressure
 */
public class FileProcessor extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(FileProcessor.class);
    public static final String OUT = "OUT";

    OutputCollector collector;
    private String directoryBeingWatched;

    public FileProcessor(String directoryBeingWatched) {
        this.directoryBeingWatched = directoryBeingWatched;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String fileName = tuple.getString(0);
        LocalFileReader reader = new LocalFileReader();
        try {
            LOG.info("Opening file {}", fileName);
            File source = new File(fileName);
            long modifiedTimestamp = source.lastModified();

            reader.init(fileName);
            // FIXME - Move this to an iterator model where we can read multiple files in a lazy fashion
            while (reader.hasLines()) {
                collector.emit(Lists.<Object>of(reader.readLine(), modifiedTimestamp));
            }
            reader.close();

            File parent = new File(directoryBeingWatched).getParentFile();
            File destFile = new File(String.format("%s/%s/%d_%s", parent.getAbsolutePath(), OUT, System.currentTimeMillis(), source.getName()));
            LOG.info("Moving {} to {}", source, destFile);
            FileUtils.moveFile(source, destFile);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line", "timestamp"));
    }
}
