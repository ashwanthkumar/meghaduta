package meghaduta.filters;

import meghaduta.models.Filter;
import meghaduta.models.Item;

public class IdentityFilter extends AbstractFilter<Void> {
    private static final Filter INSTANCE = new IdentityFilter();
    public static Filter get() {
        return INSTANCE;
    }

    protected IdentityFilter() {
        super(null, null, null);
    }

    @Override
    public Boolean matches(Item item) {
        return true;
    }
}
