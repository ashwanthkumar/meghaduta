package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFilter extends AbstractFilter<Date> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");
    ;

    public DateFilter(String attribute, Operator operator, Date filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        if (item.getAttributes().containsKey(attribute)) {
            try {
                Date eventValue = DATE_FORMAT.parse(item.getAttributes().get(attribute));
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

    public static Date parse(String dateAsString) {
        try {
            return DATE_FORMAT.parse(dateAsString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
