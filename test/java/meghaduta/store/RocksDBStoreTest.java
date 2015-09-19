package meghaduta.store;

import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.config.MDConfig;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RocksDBStoreTest {
    RocksDBStore store = new RocksDBStore();
    File dbLocation;

    @Before
    public void before() throws Exception {
        dbLocation = new File("/tmp/meghaduta/rocksdb-test");
        dbLocation.mkdirs();
        store.init(testConfig());
    }

    @After
    public void cleanup() throws Exception {
        store.close();
        FileUtils.deleteDirectory(dbLocation);
    }

    @Test
    public void shouldGetAnItemFromStoreForAGivenId() throws Exception {
        Event e1 = createEvent("1", "name", "Ashwanth", 1);
        store.put(e1);
        Event e2 = createEvent("1", "age", "25", 2);
        store.put(e2);

        Item item = store.get("1");
        assertThat(item.getItemId(), is("1"));
        assertThat(item.getAttributes().get("name"), is("Ashwanth"));
        assertThat(item.getAttributes().get("age"), is("25"));
        assertThat(item.getLastUpdated(), is(2L));
    }

    @Test
    public void shouldPutAnEventToDb() throws Exception {
        Event e1 = createEvent("1", "name", "Ashwanth", 1);
        store.put(e1);
        Event e2 = createEvent("1", "age", "25", 2);
        store.put(e2);
        // assertion is not to throw any errors
    }


    public Event createEvent(String itemId, String name, String value, long ts) {
        return new Event()
                .setItemId(itemId)
                .setName(name)
                .setValue(value)
                .setTimestamp(String.valueOf(ts));
    }

    public MDConfig testConfig() {
        return new MDConfig()
                .setStoreImpl("rocksdb")
                .setDBLocation(dbLocation.getAbsolutePath());
    }

}