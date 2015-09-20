package meghaduta.parser;

import java.io.IOException;

public interface Parser<T> {
    T parse(String input) throws IOException;
}
