package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.util.Map;

public class DoubleFilter extends AbstractFilter<Double> {

    public DoubleFilter(String attribute, Operator operator, Double filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        Map<String, String> attributes = item.getAttributes();
        if (attributes.containsKey(attribute)){
            Double value = Double.valueOf(attributes.get(attribute));
            return evaluate(value);
        }
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
