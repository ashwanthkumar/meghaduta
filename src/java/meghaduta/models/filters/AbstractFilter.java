package meghaduta.models.filters;

abstract public class AbstractFilter implements Filter {
    @Override
    public Filter and(Filter another) {
        return new AndFiter(this, another);
    }

    @Override
    public Filter or(Filter another) {
        return new OrFiter(this, another);
    }
}
