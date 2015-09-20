package meghaduta.notifier;

import meghaduta.models.Event;
import meghaduta.models.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class FileNotifier implements Notifier, Serializable {
    public static final String NAME = "file";

    transient private PrintWriter writer;
    private String fileName;

    public FileNotifier(String filename) {
        this.fileName = filename;
        initWriter(filename);
    }

    private void initWriter(String filename) {
        try {
            this.writer = new PrintWriter(new FileOutputStream(filename, true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void notify(Event event, Item item, String notifier) throws Exception {
        if (writer == null) initWriter(fileName);

        this.writer.println(String.format("[%d] Notifing %s about %s from item=%s", System.currentTimeMillis(), notifier, event, item));
        this.writer.flush();
    }
}
