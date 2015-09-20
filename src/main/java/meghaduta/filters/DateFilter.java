package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFilter extends AbstractFilter<Date> {

    public DateFilter(String attribute, Operator operator, Date filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        if (item.getAttributes().containsKey(attribute)) {
            DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
            try {
                Date eventValue = dateFormat.parse(item.getAttributes().get(attribute));
                return evaluate(eventValue);
            } catch (ParseException e) {
                // Use Logger
            }
        }
        return false;
    }

    private boolean evaluate(Date eventValue) {
        int result = eventValue.compareTo(filterValue);
        switch (operator) {
            case EQ:
                return (result == 0);
            case GT:
                return (result > 0);
            case LT:
                return (result < 0);
        }
        return false;
    }
}
