package meghaduta.filters.serde;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.lang.tuple.Tuple2;
import meghaduta.filters.IdentityFilter;
import meghaduta.filters.IntFilter;
import meghaduta.filters.StringFilter;
import meghaduta.models.Filter;
import meghaduta.models.Operator;
import meghaduta.models.Subscription;

import java.util.List;

public class HoconSubscriptionReader {
    public List<Subscription> read() {
        Config config = ConfigFactory.load("subscriptions");
        List<Config> subscriptions = (List<Config>) config.getConfigList("subscriptions");
        return Lists.map(subscriptions, new Function<Config, Subscription>() {
            @Override
            public Subscription apply(Config input) {
                return toSubscription(input);
            }
        });
    }

    protected Subscription toSubscription(Config config) {
        Subscription subscription = new Subscription();
        subscription.setNotifier(config.getString("notifier"));
        subscription.setFilter(toFilters((List<Config>) config.getConfigList("filters")));
        return subscription;
    }

    protected Filter toFilters(List<Config> config) {
        return Lists.foldL(config, IdentityFilter.get(), new Function<Tuple2<Filter, Config>, Filter>() {
            @Override
            public Filter apply(Tuple2<Filter, Config> input) {
                Filter sofar = input._1();
                Config config = input._2();
                return sofar.and(toFilter(config));
            }
        });
    }

    protected Filter toFilter(Config config) {
        String type = config.getString("type");
        String name = config.getString("name");
        String value = config.getString("value");
        String operator = config.getString("operator");
        if (type.equals("string")) {
            return new StringFilter(name, toOperator(operator), value);
        } else if (type.equals("int")) {
            return new IntFilter(name, toOperator(operator), Integer.valueOf(value));
        }

        throw new RuntimeException("Invalid type - " + type + " for " + name + " attribute.");
    }

    protected Operator toOperator(String operator) {
        if (operator.equals("eq")) {
            return Operator.EQ;
        } else if (operator.equals("lt")) {
            return Operator.LT;
        } else if (operator.equals("gt")) {
            return Operator.GT;
        } else if (operator.equals("le")) {
            return Operator.LE;
        } else if (operator.equals("ge")) {
            return Operator.GE;
        }

        throw new RuntimeException("Invalid operator specified - " + operator);
    }
}
