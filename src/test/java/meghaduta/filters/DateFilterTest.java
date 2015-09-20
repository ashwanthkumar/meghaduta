package meghaduta.filters;

import meghaduta.models.Item;
import meghaduta.models.Operator;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DateFilterTest {

    @Test
    public void shouldTestDateFilter() {
        Item item =  new Item();
        item.setItemId("13579");
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("list price", "10");
        attributes.put("orderId" , "20");
        attributes.put("orderFrom" , "Salaikumar");
        attributes.put("order date" , "22-03-2010");
        item.setAttributes(attributes);

        DateFilter dateFilter = new DateFilter("order date", Operator.EQ,new Date(2000,3,22));
        assertThat(dateFilter.matches(item),is(false));
    }

}