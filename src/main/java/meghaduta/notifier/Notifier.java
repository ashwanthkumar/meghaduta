package meghaduta.notifier;

import meghaduta.models.Event;
import meghaduta.models.Item;

public interface Notifier {
    String getName();

    void notify(Event event, Item item, String notifier) throws Exception;
}
