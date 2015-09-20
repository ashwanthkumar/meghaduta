package meghaduta.models;

import java.io.Serializable;

public class Subscription implements Serializable {

    private Filter filter;
    private String notifier;

    public boolean matches(Item item) {
        return filter.matches(item);
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

    public void setNotifier(String notifier) {
        this.notifier = notifier;
    }
}
