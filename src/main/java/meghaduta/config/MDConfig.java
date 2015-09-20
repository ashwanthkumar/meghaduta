package meghaduta.config;

public class MDConfig {
    // Store related Config
    private String storeImpl;
    private String dbLocation;

    private String sharedFolder;

    public MDConfig setStoreImpl(String storeImpl) {
        this.storeImpl = storeImpl;
        return this;
    }

    public MDConfig setDBLocation(String dbLocation) {
        this.dbLocation = dbLocation;
        return this;
    }

    public MDConfig setSharedFolder(String sharedFolder) {
        this.sharedFolder = sharedFolder;
        return this;
    }

    public String getStoreImpl() {
        return storeImpl;
    }

    public String getDBLocation() {
        return dbLocation;
    }

    public String getSharedFolder() {
        return sharedFolder;
    }
}
