package meghaduta.models;

import java.util.Map;

public class Item {
    private String itemId;
    private long lastUpdated;
    private Map<String, String> attributes;

    public Item setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Item setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public Item setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", attributes=" + attributes +
                '}';
    }
}
