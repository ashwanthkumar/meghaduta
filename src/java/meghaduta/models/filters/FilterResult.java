package meghaduta.models.filters;

import meghaduta.models.Subscription;

public class FilterResult {
    private boolean isSucceed;
    private Subscription subscription;

    public boolean isSucceed() {
        return isSucceed;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
