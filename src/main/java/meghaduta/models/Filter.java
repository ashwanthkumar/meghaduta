package meghaduta.models;

import java.io.Serializable;
import java.util.List;

public interface Filter extends Serializable {
    Boolean matches(Item item);
    Filter and(Filter another);
    Filter or(Filter another);

    List<String> getAttributes();
}
