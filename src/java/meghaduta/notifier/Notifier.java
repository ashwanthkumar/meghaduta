package meghaduta.notifier;

import meghaduta.models.Event;

public interface Notifier {
    void notify(Event event) throws Exception;
}
