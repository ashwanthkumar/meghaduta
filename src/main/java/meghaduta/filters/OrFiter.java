package meghaduta.filters;

import in.ashwanthkumar.utils.collections.Lists;
import meghaduta.models.Filter;
import meghaduta.models.Item;

import java.util.List;

public class OrFiter extends AbstractFilter<Void> {
    private Filter left;
    private Filter right;

    public OrFiter(Filter left, Filter right) {
        super(null, null, null);
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean matches(Item item) {
        return left.matches(item) || right.matches(item);
    }

    @Override
    public List<String> getAttributes() {
        return Lists.concat(left.getAttributes(), right.getAttributes());
    }

}
