package meghaduta.handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileWatcher {

    private WatchKey key;
    private WatchService watcher;
    private NewFileCreatedHandler handler;

    public FileWatcher(NewFileCreatedHandler handler) {
        this.handler = handler;
    }

    public void start(File file) throws IOException {
        Path directory = Paths.get(file.getAbsolutePath());
        watcher = directory.getFileSystem().newWatchService();
        directory.register(watcher, ENTRY_CREATE);
    }

    public void handle() {
        key = watcher.poll();
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                File changedFile = new File(event.context().toString());
                handler.handle(changedFile);
                key.reset();
            }
        }
    }

}
