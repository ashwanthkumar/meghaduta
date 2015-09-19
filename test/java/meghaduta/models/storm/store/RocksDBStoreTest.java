package meghaduta.models.storm.store;

import meghaduta.models.Event;
import meghaduta.models.config.MDConfig;
import org.junit.BeforeClass;
import org.junit.Test;

public class RocksDBStoreTest {
    RocksDBStore store = new RocksDBStore();

    @BeforeClass
    public void before() throws Exception {
        store.init(testConfig());
    }

    @Test
    public void shouldPutAnEventToDb() throws Exception {
        Event e1 = createEvent("1", "name", "Ashwanth", 1);
        store.put(e1);
        Event e2 = createEvent("1", "age", "25", 2);
    }

    @Test
    public void shouldGetAnItemFromStoreForAGivenId() {
    }


    public Event createEvent(String itemId, String name, String value, long ts) {
        return new Event()
                .setItemId(itemId)
                .setName(name)
                .setValue(value)
                .setTimestamp(String.valueOf(ts));
    }

    public MDConfig testConfig() {
        return new MDConfig();
    }

}