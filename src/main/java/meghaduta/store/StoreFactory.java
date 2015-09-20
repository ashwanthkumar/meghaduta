package meghaduta.store;

public class StoreFactory {
    public static Store get(String impl) {
        if(impl.equals(RocksDBStore.NAME)) {
            return new RocksDBStore();
        } else if(impl.equals(RedisStore.REDIS)) {
            return new RedisStore();
        }

        throw new RuntimeException("Invalid Store typle " + impl + " specified");
    }
}
