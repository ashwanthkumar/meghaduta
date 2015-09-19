package meghaduta.models;


import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;

import java.util.List;

import static in.ashwanthkumar.utils.collections.Lists.filter;
import static in.ashwanthkumar.utils.collections.Lists.map;

public class Subscription {
    private Filter filter_;

    // contains whom to notify
    private String notifier;

    public void setFilter_(Filter filter_) {
        this.filter_ = filter_;
    }

    public static void main(String[] args) {

        List<Subscription> subscriptions = readSubscriptions();

        final Event event = new Event();
        map(
                filter(
                        subscriptions,
                        matchEvent(event)
                ),
                notifyTheOwner()
        );
    }

    private static List<Subscription> readSubscriptions() {
        Filter filter = new Filter()
                .and(new Filter())
                .or(new Filter());
        Subscription subscription = new Subscription();
        subscription.setFilter_(filter);

        return Lists.of(subscription);
    }

    private static Predicate<Subscription> matchEvent(final Event event) {
        return new Predicate<Subscription>() {
            @Override
            public Boolean apply(Subscription input) {
                return input.filter.matches(event);
            }
        };
    }

    private static Function<Subscription, Void> notifyTheOwner() {
        return new Function<Subscription, Void>() {
            @Override
            public Void apply(Subscription input) {
                return runNotifier(input);
            }
        };
    }
}

