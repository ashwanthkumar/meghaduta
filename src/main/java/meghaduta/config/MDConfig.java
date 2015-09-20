package meghaduta.config;

import java.io.Serializable;

public class MDConfig implements Serializable {
    // Store related Config
    private String storeImpl;
    private String dbLocation;

    private String sharedFolder;

    private String notifierImpl;
    private String notifierFileName;

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

    public MDConfig setNotifierFileName(String notifierFileName) {
        this.notifierFileName = notifierFileName;
        return this;
    }

    public MDConfig setNotifierImpl(String notifierImpl) {
        this.notifierImpl = notifierImpl;
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

    public String getNotifierFileName() {
        return notifierFileName;
    }

    public String getNotifierImpl() {
        return notifierImpl;
    }
}
