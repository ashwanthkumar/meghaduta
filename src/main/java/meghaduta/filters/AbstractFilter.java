package meghaduta.filters;

import meghaduta.models.Filter;
import meghaduta.models.Operator;

abstract public class AbstractFilter<T> implements Filter {
    protected String attribute;
    protected Operator operator;
    protected T filterValue;

    protected AbstractFilter(String attribute, Operator operator, T filterValue) {
        this.attribute = attribute;
        this.operator = operator;
        this.filterValue = filterValue;
    }

    @Override
    public Filter and(Filter another) {
        return new AndFiter(this, another);
    }

    @Override
    public Filter or(Filter another) {
        return new OrFiter(this, another);
    }
}
