package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AndFilterTest {

    @Test
    public void shouldTestAndFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10");
        attributes.put("orderId" , "20");
        attributes.put("orderFrom" , "Salaikumar");
        attributes.put("order date" , "22-03-2010");
        item.setAttributes(attributes);

        IntFilter intFilter = new IntFilter("list price", Operator.EQ, 10);
        IntFilter intFilter2 = new IntFilter("orderId", Operator.EQ, 30);

        assertThat(intFilter.and(intFilter2).matches(item),is(false));
    }

}