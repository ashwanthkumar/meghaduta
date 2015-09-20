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
        return new AndFilter(this, another);
    }

    @Override
    public Filter or(Filter another) {
        return new OrFiter(this, another);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractFilter<?> that = (AbstractFilter<?>) o;

        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null) return false;
        if (operator != that.operator) return false;
        return !(filterValue != null ? !filterValue.equals(that.filterValue) : that.filterValue != null);

    }

    @Override
    public int hashCode() {
        int result = attribute != null ? attribute.hashCode() : 0;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (filterValue != null ? filterValue.hashCode() : 0);
        return result;
    }
}
