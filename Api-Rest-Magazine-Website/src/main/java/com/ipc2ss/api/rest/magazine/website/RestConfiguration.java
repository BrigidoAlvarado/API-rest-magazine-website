package com.ipc2ss.api.rest.magazine.website;
/*
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
*/
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api/v1")
public class RestConfiguration extends ResourceConfig {
    
    public RestConfiguration()  {
        packages("com.ipc2ss.api.rest.magazine.website").register(MultiPartFeature.class);
    }
    
}