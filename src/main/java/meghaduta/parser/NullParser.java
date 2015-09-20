package meghaduta.parser;

import org.apache.storm.guava.collect.Lists;

import java.util.List;

public class NullParser<T> implements Parser<T> {
    @Override
    public T parse(String input) {
        return (T) null;
    }
}
