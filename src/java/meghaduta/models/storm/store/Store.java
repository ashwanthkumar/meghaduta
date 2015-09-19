package meghaduta.models.storm.store;

import meghaduta.models.Event;
import meghaduta.models.Item;

public interface Store {
    String getName();

    void put(Event event) throws Exception;

    Item get(String key) throws Exception;
}
