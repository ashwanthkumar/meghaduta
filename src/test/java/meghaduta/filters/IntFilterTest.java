package meghaduta.filters;

import meghaduta.filters.IntFilter;
import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntFilterTest {

    @Test
    public void shouldTestIntFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10");
        attributes.put("orderId" , "20");
        item.setAttributes(attributes);

        IntFilter intFilter = new IntFilter("orderId", Operator.EQ, 20);
        assertThat(intFilter.matches(item), is(true));

        IntFilter intFilter2 = new IntFilter("orderId", Operator.EQ, 30);
        assertThat(intFilter2.matches(item), is(false));
    }
}