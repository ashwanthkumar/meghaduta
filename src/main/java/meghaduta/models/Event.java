package meghaduta.models;

public class Event {
    private String itemId;
    private String name;
    private String value;
    private long timestamp;

    public Event setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public Event setValue(String value) {
        this.value = value;
        return this;
    }

    public Event setTimestamp(String ts) {
        this.timestamp = Long.parseLong(ts);
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
