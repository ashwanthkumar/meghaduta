package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFilter extends AbstractFilter <Date>{

    protected DateFilter(String attribute, Operator operator, Date filterValue) {
        super(attribute, operator, filterValue);
    }

    @Override
    public Boolean matches(Item item) {
        Date eventValue = null;
        if (item.getAttributes().containsKey(attribute)) {
            DateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
            try {
               eventValue = dateFormat.parse(item.getAttributes().get(attribute));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return evaluate(eventValue);
        }
        return false;
    }

    private boolean evaluate(Date eventValue) {
        int result = eventValue.compareTo(filterValue);
        switch (operator) {
            case EQ:
                if (result == 0)
                    return true;
            case GT:
                if (result > 0)
                    return true;
            case LT:
               if (result < 0)
                   return true;
        }
        return false;
    }
}
