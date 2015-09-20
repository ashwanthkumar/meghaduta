package meghaduta.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import meghaduta.config.MDConfig;
import meghaduta.config.MDConfigReader;

public class MeghaDutaService extends Service<ServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MeghaDutaService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("meghaduta-query");
    }

    @Override
    public void run(ServiceConfiguration configuration,
                    Environment environment) {
        MDConfig appConfig = MDConfigReader.load();
        environment.addResource(new QueryResource(appConfig));
    }

}
