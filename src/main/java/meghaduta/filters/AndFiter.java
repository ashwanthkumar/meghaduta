package meghaduta.filters;

import meghaduta.models.Filter;
import meghaduta.models.Item;

public class AndFiter extends AbstractFilter<Void> {

    private Filter left;
    private Filter right;

    public AndFiter(Filter left, Filter right) {
        super(null, null,null);
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean matches(Item item) {
          return false;
    }
}
