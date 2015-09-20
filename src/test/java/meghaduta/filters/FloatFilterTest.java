package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FloatFilterTest {

    @Test
    public void shouldTestFloatFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10.0");
        attributes.put("orderId" , "20.0");
        item.setAttributes(attributes);

//        FloatFilter floatFilter = new FloatFilter("listrPrice", Operator.EQ, Float.valueOf(25));
//        assertThat(floatFilter.matches(item), is(true));

        FloatFilter floatFilter2 = new FloatFilter("list price", Operator.EQ, Float.valueOf(10));
        assertThat(floatFilter2.matches(item), is(true));

        FloatFilter floatFilter3 = new FloatFilter("orderId", Operator.LT, Float.valueOf(30));
        assertThat(floatFilter3.matches(item), is(true));
    }
}