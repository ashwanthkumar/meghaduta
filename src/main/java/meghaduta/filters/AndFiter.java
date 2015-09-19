package meghaduta.filters;

import meghaduta.models.Item;

public class AndFiter extends AbstractFilter {
    private Filter left;
    private Filter right;

    public AndFiter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public FilterResult matches(Item item) {
        FilterResult leftResult = left.matches(item);
        if(leftResult.isSucceed()) {
            return right.matches(item);
        }

        return leftResult;
    }
}
