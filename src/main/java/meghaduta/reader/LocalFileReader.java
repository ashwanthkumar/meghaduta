package meghaduta.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalFileReader implements Reader {

    private BufferedReader reader;
    private boolean hasMore = true;

    @Override
    public void init(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public String readLine() throws IOException {
        String line = reader.readLine();
        hasMore = line != null;
        return line;
    }

    @Override
    public boolean hasLines() {
        return hasMore;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
