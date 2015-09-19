package meghaduta.models.storm.notifier;

import meghaduta.models.Event;

public interface Notifier {
    void notify(Event event) throws Exception;
}
