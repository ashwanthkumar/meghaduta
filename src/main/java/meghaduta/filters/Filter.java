package meghaduta.filters;

import meghaduta.models.Item;

public interface Filter {
    Boolean matches(Item item);
    Filter and(Filter another);
    Filter or(Filter another);
}
