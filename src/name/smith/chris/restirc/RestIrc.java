package name.smith.chris.restirc;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Main program entry point. Handles initialisation logic.
 */
@Slf4j
public class RestIrc {

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    public static void main(final String[] args) throws Exception {
        ResourceConfig rc = new PackagesResourceConfig("name.smith.chris.restirc.resources");
        GrizzlyServerFactory.createHttpServer(getBaseURI(), rc);
        System.in.read();
    }

}
