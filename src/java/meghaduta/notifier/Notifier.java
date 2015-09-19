package meghaduta.notifier;

import meghaduta.models.Event;
import meghaduta.models.Item;

public interface Notifier {
    void notify(Event event, Item item) throws Exception;
}
