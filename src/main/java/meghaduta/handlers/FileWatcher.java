package meghaduta.handlers;

import backtype.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileWatcher {
    private static final Logger LOG = LoggerFactory.getLogger(FileWatcher.class);

    private WatchService watcher;
    private NewFileCreatedHandler handler;
    private String directoryBeingWatched;

    public FileWatcher(NewFileCreatedHandler handler) {
        this.handler = handler;
    }

    public void start(File file) throws IOException {
        directoryBeingWatched = file.getAbsolutePath();
        LOG.info("Monitoring {} directory for new files", directoryBeingWatched);
        Path directory = Paths.get(directoryBeingWatched);
        watcher = directory.getFileSystem().newWatchService();
        directory.register(watcher, ENTRY_CREATE);
    }

    public void handle() {
        WatchKey key = watcher.poll();
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                File changedFile = new File(directoryBeingWatched + "/" + event.context().toString());
                if(changedFile.isFile()) {
                    handler.handle(changedFile);
                } else {
                    LOG.info("Ignoring directory {}", changedFile.getAbsolutePath());
                }
            }
            key.reset();
        } else {
            Utils.sleep(100L);
        }
    }

}
