package meghaduta.models;

import in.ashwanthkumar.utils.lang.option.None;
import in.ashwanthkumar.utils.lang.option.Option;
import in.ashwanthkumar.utils.lang.option.Some;

public class Subscription {

    private Filter filter;
    private String notifier;

    private Option<String> matches(Item item) {
        boolean result = filter.matches(item);
        if (result) return new Some<String>(notifier);
        else return new None<String>();
    }

    public Filter getFilter() {
        return filter;
    }

    public Subscription setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public String getNotifier() {
        return notifier;
    }
}
