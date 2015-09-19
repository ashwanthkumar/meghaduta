package meghaduta.filters;

import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.option.Some;
import meghaduta.models.Item;
import meghaduta.notifier.Notifier;

public class Subscription {

    private Filter filter;
    private Notifier notifier;

    private Option<Notifier> matches(Item item) {
        boolean result = filter.matches(item);
        if (result) return new Some<Notifier>(notifier);
        else return new None<Notifier>();
    }

    public Filter getFilter() {
        return filter;
    }

    public Subscription setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public Subscription setNotifier(Notifier notifier) {
        this.notifier = notifier;
        return this;
    }
}
