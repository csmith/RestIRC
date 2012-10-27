package name.smith.chris.restirc.resources;

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.spi.resource.Singleton;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import name.smith.chris.restirc.model.Server;
import name.smith.chris.restirc.model.ServerManager;

import lombok.AllArgsConstructor;

/**
 * Top-level resource for servers.
 */
@Singleton
@Path("/servers")
public class ServersResource {

    private final ServerManager manager = new ServerManager() {{
        addServer("test", new Server() {{
            try {
                addAddress(new URI("irc://irc.quakenet.org:6667/"));
                addChannel("#mdbot");
            } catch (URISyntaxException ex) {
                // Fail
            }
        }});
    }};

    @GET
    @Produces("application/json")
    public String getList() {
        return "Meh";
    }

    @Path("/{id}")
    public ServerResource getSubResource(@PathParam("id") String id) {
        if (manager.hasServer(id)) {
            return new ServerResource(id);
        } else {
            throw new NotFoundException();
        }
    }

    @Path("/{id}")
    @GET
    public String getServer(@PathParam("id") String id) {
        if (manager.hasServer(id)) {
            return "Boo!";
        } else {
            throw new NotFoundException();
        }
    }

    @Path("/{id}")
    @PUT
    public String getTestyList() {
        throw new UnsupportedOperationException("TODO");
    }

    @AllArgsConstructor
    public static class ServerResource {

        private final String id;

        @GET
        @Produces("text/plain")
        @Path("/test")
        public String test() {
            return "Test! My ID is " + id;
        }

    }

}
