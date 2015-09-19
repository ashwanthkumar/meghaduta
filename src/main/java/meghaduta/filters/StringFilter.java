package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import meghaduta.models.Subscription;

public class StringFilter extends AbstractFilter<String>{

    protected StringFilter(String attribute, Operator operator, String filterValue, Subscription subscription) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        return false;
    }

    private boolean evaluate(String eventValue) {
        return filterValue.equals(eventValue);
    }
}
