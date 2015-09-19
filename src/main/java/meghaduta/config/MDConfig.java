package meghaduta.config;

public class MDConfig {
    // Store related Config
    private String storeImpl;
    private String dbLocation;

    public MDConfig setStoreImpl(String storeImpl) {
        this.storeImpl = storeImpl;
        return this;
    }

    public MDConfig setDBLocation(String dbLocation) {
        this.dbLocation = dbLocation;
        return this;
    }

    public String getStoreImpl() {
        return storeImpl;
    }

    public String getDBLocation() {
        return dbLocation;
    }
}
