package meghaduta.filters.serde;

import meghaduta.filters.IdentityFilter;
import meghaduta.filters.IntFilter;
import meghaduta.filters.StringFilter;
import meghaduta.models.Filter;
import meghaduta.models.Operator;
import meghaduta.models.Subscription;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HoconSubscriptionReaderTest {

    @Test
    public void shouldReadSubscriptionsFromHocon() {
        HoconSubscriptionReader reader = new HoconSubscriptionReader();
        List<Subscription> subscriptions = reader.read();

        assertThat(subscriptions.size(), is(1));

        Subscription subscription = subscriptions.get(0);
        assertThat(subscription.getNotifier(), is("subscriber1"));

        Filter filter = IdentityFilter.get()
                .and(new StringFilter("foo", Operator.EQ, "bar"))
                .and(new IntFilter("f2", Operator.LT, 4));
        assertThat(subscription.getFilter(), is(filter));
    }

}