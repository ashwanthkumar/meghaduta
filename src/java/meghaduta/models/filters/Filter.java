package meghaduta.models.filters;

import meghaduta.models.Item;

public interface Filter {
    FilterResult matches(Item item);

    Filter and(Filter another);

    Filter or(Filter another);
}
