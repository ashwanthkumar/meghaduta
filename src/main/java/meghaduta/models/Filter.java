package meghaduta.models;

public interface Filter {
    Boolean matches(Item item);
    Filter and(Filter another);
    Filter or(Filter another);
}
