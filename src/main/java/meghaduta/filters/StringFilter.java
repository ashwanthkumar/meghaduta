package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.util.Map;

public class StringFilter extends AbstractFilter<String>{

    public StringFilter(String attribute, Operator operator, String filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        Map<String, String> attributes = item.getAttributes();
        if ( attributes.containsKey(attribute) ){
            String value = attributes.get(attribute);
            return evaluate(value);
        }
        return false;
    }

    private boolean evaluate(String eventValue) {
        return filterValue.equals(eventValue);
    }
}
