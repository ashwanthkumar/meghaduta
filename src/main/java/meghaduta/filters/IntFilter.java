package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.util.Map;


public class IntFilter extends AbstractFilter<Integer> {

    public IntFilter(String attribute, Operator operator, Integer filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        Map<String, String> attributes = item.getAttributes();
        if (attributes.containsKey(attribute)){
           Integer value = Integer.valueOf(attributes.get(attribute));
           return evaluate(value);
        }
        return false;
    }

    private boolean evaluate(Integer eventValue) {
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
