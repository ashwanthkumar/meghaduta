package meghaduta.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalFileReader implements Reader {

    private BufferedReader reader;
    private String line = null;

    @Override
    public void init(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public String readLine() throws IOException {
        return line;
    }

    @Override
    public boolean hasLines() {
        try {
            line = reader.readLine();
        } catch (IOException ignored) {}
        return line != null;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
