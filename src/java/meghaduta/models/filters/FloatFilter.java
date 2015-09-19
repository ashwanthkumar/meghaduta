package meghaduta.models.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

/**
 * Created by salaikumar on 19/9/15.
 */
public class FloatFilter extends AbstractFilter<Float> {

    protected FloatFilter(String attribute, Operator operator, Float filterValue, Subscription subscription) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        return false;
    }

    private boolean evaluate(Float eventValue) {
        switch (operator) {
            case GE: return eventValue >= filterValue;
            case LE: return eventValue <= filterValue;
            case EQ: return eventValue.equals(filterValue);
            case GT: return eventValue > filterValue;
            case LT: return eventValue < filterValue;
        }

        return false;
    }
}
