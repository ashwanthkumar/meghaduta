package meghaduta.parser;

import java.io.IOException;

public interface Parser<T> {
    T parseFile(String input) throws IOException;
}
