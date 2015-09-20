package meghaduta.parser;

import meghaduta.models.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CSVFileParserTest {

    CSVFileParser parse = new CSVFileParser();
    String value;

    @Before
    public void before() throws Exception {
        value = "13579,title,freakonomics";
    }

    @Test
    public void testParseFile() throws Exception{
        List<Event> events =  parse.parseFile(value);
        Event expectedEvent = new Event()
                .setItemId("13579")
                .setName("title")
                .setValue("freakonomics");

        assertThat(events, hasItem(expectedEvent));
    }
}
