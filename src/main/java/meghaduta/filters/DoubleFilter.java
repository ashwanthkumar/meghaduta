package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import meghaduta.models.Subscription;

public class DoubleFilter extends AbstractFilter<Double> {

    protected DoubleFilter(String attribute, Operator operator, Double filterValue, Subscription subscription) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
      return false;
    }

    private boolean evaluate(Double eventValue) {
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
