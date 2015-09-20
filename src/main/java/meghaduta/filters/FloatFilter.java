package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

public class FloatFilter extends AbstractFilter<Float> {

    public FloatFilter(String attribute, Operator operator, Float filterValue) {
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
