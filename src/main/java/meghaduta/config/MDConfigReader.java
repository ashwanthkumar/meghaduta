package meghaduta.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class MDConfigReader {
    public static MDConfig load() {
        return new MDConfigReader().read();
    }

    public MDConfig read() {
        Config config = ConfigFactory.load();
        return toMDConfig(config.getConfig("meghaduta"));
    }

    public MDConfig toMDConfig(Config config) {
        return new MDConfig()
                .setStoreImpl(config.getString("store-type"))
                .setDBLocation(config.getString("db-location"))
                .setSharedFolder(config.getString("shared-folder"));
    }


}
