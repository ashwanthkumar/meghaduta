package meghaduta.service;

import com.yammer.metrics.annotation.Timed;
import meghaduta.config.MDConfig;
import meghaduta.models.Item;
import meghaduta.store.Store;
import meghaduta.store.StoreFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class QueryResource {
    private String dbLocation;
    protected Store store;

    public QueryResource(MDConfig appConfig) {
        this.store = StoreFactory.get(appConfig.getStoreImpl());
        try {
            this.store.init(appConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Timed
    public Map<String, String> query(@PathParam("id") String id) {
        try {
            Item item = store.get(id);
            if(item != null) {
                return item.getAttributes();
            } else {
                return new HashMap<String, String>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
