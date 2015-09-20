package meghaduta.filters;

import meghaduta.models.Filter;
import meghaduta.models.Item;

public class AndFilter extends AbstractFilter<Void> {

    private Filter left;
    private Filter right;

    public AndFilter(Filter left, Filter right) {
        super(null, null,null);
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean matches(Item item) {
        return left.matches(item) && right.matches(item);
    }
}
