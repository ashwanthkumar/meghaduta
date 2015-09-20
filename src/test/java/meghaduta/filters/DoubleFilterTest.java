package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DoubleFilterTest {

    @Test
    public void shouldTestDoubleFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10.0");
        attributes.put("orderId" , "20.0");
        item.setAttributes(attributes);

        DoubleFilter doubleFilter = new DoubleFilter("list price", Operator.LT,Double.valueOf(35));
        assertThat(doubleFilter.matches(item), is(true));

    }

}