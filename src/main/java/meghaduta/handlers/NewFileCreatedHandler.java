package meghaduta.handlers;

import java.io.File;

public interface NewFileCreatedHandler {
    void handle(File file);
}