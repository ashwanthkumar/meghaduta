package meghaduta.reader;

import java.io.IOException;
import java.util.List;

public interface Reader {
    void init(String fileName) throws IOException;

    String readLine() throws IOException;

    boolean hasLines();

    void close() throws IOException;
}
