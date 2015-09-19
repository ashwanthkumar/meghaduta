package meghaduta.store;

import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.config.MDConfig;
import org.apache.commons.lang.StringUtils;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;

import java.util.HashMap;
import java.util.Map;

public class RocksDBStore implements Store {
    static {
        RocksDB.loadLibrary();
    }

    public static final String NAME = "rocksdb";
    private static final String SEPARATOR = "___";
    private static final String LAST_UPDATED_AT_KEY = "lastUpdatedAt";

    protected String dbLocation;

    protected RocksDB db;

    @Override
    public void init(MDConfig config) throws Exception {
        dbLocation = config.getDBLocation();
        db = RocksDB.open(dbLocation);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void put(Event event) throws Exception {
        String key = event.getItemId() + SEPARATOR + event.getName();
        String ts = event.getItemId() + SEPARATOR + LAST_UPDATED_AT_KEY;
        db.put(key.getBytes(), event.getValue().getBytes());
        db.put(ts.getBytes(), String.valueOf(event.getTimestamp()).getBytes());
    }

    @Override
    public Item get(String itemId) throws Exception {
        RocksIterator iterator = db.newIterator();
        iterator.seek(itemId.getBytes());
        Item item = buildFromIterator(itemId, iterator);
        iterator.dispose();
        return item;
    }

    @Override
    public void close() throws Exception {
        if(db != null) db.close();
    }

    public Item buildFromIterator(String itemId, RocksIterator iterator) {
        Map<String, String> attributes = new HashMap<String, String>();
        Long lastUpdated = 0L;
        while (iterator.isValid()) {
            String name = stripIdPrefix(itemId, new String(iterator.key()));
            String value = new String(iterator.value());
            attributes.put(name, value);
            iterator.next();
        }

        if (attributes.containsKey(LAST_UPDATED_AT_KEY)) {
            lastUpdated = Long.parseLong(attributes.get(LAST_UPDATED_AT_KEY));
        }
        return new Item().setItemId(itemId).setAttributes(attributes).setLastUpdated(lastUpdated);
    }

    public String stripIdPrefix(String itemId, String key) {
        return StringUtils.stripStart(key, itemId + SEPARATOR);
    }
}
