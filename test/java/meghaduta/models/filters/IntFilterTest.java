package meghaduta.models.filters;

import meghaduta.models.Item;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IntFilterTest {

    @Test
    public void shouldTestIntFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10");
        attributes.put("orderId" , "20");
        item.setAttributes(attributes);


    }
}