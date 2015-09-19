package meghaduta.store;

import meghaduta.models.Event;
import meghaduta.models.Item;
import meghaduta.models.config.MDConfig;

/***
 * Defines access to a Store
 */
public interface Store {
    /**
     * Called only once during application startup
     *
     * @param config
     * @throws Exception
     */
    void init(MDConfig config) throws Exception;

    /**
     * Used to identify the implementation name
     *
     * @return
     */
    String getName();

    /**
     * Puts the value inside the store for an Event
     *
     * @param event
     * @throws Exception
     */
    void put(Event event) throws Exception;

    /**
     * For a given Item Id return the Item that it represents
     *
     * @param itemId
     * @return
     * @throws Exception
     */
    Item get(String itemId) throws Exception;

    /***
     * Called once during application shutdown
     *
     * @throws Exception
     */
    void close() throws Exception;
}
