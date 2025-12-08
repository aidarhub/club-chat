package my.important.job.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("my.important.job.controller");
        register(JacksonFeature.class);
    }
}