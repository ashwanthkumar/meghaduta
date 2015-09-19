package meghaduta.models.filters;

import meghaduta.models.Item;
import meghaduta.models.storm.notifier.Notifier;

public class Subscription {

    private Filter filter;
    private Notifier notifier;

    private Option<Notifier> matches(Item item){
        boolean result = filter.matches(item);

        if (result)
            return new Option<Notifier>( notifier);

        return new Option<Notifier>();
    }
}
