package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringFilterTest {

    @Test
    public void shouldTestStringFilter() {

        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10");
        attributes.put("orderId" , "20");
        attributes.put("orderFrom" , "Salaikumar");
        item.setAttributes(attributes);

        StringFilter stringFilter = new StringFilter("orderFrom", Operator.EQ,"Salaikumar");
        assertThat(stringFilter.matches(item), is(true));

        StringFilter stringFilter2 = new StringFilter("orderFrom", Operator.EQ,"Salaikar");
        assertThat(stringFilter2.matches(item), is(false));


    }

}