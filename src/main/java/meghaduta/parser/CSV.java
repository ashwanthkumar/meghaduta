package meghaduta.parser;

import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import meghaduta.models.Event;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

import static in.ashwanthkumar.utils.collections.Lists.map;

public class CSV implements Parser<List<Event>> {
    @Override
    public List<Event> parse(String input) throws IOException {
        return Lists.map(CSVParser.parse(input, CSVFormat.DEFAULT), parseCSV());
    }

    private Function<CSVRecord, Event> parseCSV() {
        return new Function<CSVRecord, Event>() {
            @Override
            public Event apply(CSVRecord record) {
                Event event = new Event();
                event.setItemId(record.get(0))
                        .setName(record.get(1))
                        .setValue(record.get(2));
                return event;
            }
        };
    }
}
