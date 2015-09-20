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

    @Override
    public String toString() {
        return "Event{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (timestamp != event.timestamp) return false;
        if (!itemId.equals(event.itemId)) return false;
        if (!name.equals(event.name)) return false;
        return value.equals(event.value);

    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
