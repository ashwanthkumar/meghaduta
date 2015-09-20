package meghaduta.store;

import in.ashwanthkumar.utils.lang.StringUtils;
import meghaduta.config.MDConfig;
import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.utils.JsonUtil;
import redis.clients.jedis.Jedis;

public class RedisStore implements Store {

    public static final String REDIS = "redis";
    private Jedis db;

    @Override
    public void init(MDConfig config) throws Exception {
        db = new Jedis(config.getDBLocation());
    }

    @Override
    public String getName() {
        return REDIS;
    }

    @Override
    public void put(Event event) throws Exception {
        String itemAsJson = db.get(event.getItemId());
        Item item;
        if(StringUtils.isNotEmpty(itemAsJson)) {
            item = JsonUtil.fromJson(itemAsJson, Item.class);
        } else {
            item = new Item();
        }
        item.update(event);
        item.setLastUpdated(event.getTimestamp());
        db.set(event.getItemId(), JsonUtil.toJson(item));
    }

    @Override
    public Item get(String itemId) throws Exception {
        String itemAsJson = db.get(itemId);
        // FIXME - This is really really ugly! Yuck >.<
        if(StringUtils.isNotEmpty(itemAsJson)) return JsonUtil.fromJson(itemAsJson, Item.class);
        else return null;
    }

    @Override
    public void close() throws Exception {
        db.close();
    }
}
