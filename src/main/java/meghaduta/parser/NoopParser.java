package meghaduta.parser;

import org.apache.storm.guava.collect.Lists;

import java.util.List;

public class NoopParser implements Parser<List<String>> {
    @Override
    public List<String> parse(String input) {
        return Lists.newArrayList();
    }
}
