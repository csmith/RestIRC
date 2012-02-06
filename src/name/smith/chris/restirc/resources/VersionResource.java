package name.smith.chris.restirc.resources;

import com.dmdirc.util.io.TextFile;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Version resource. Simply returns the current version of the application.
 */
@Path("/version")
public class VersionResource {

    /**
     * Gets the textual representation of the version.
     *
     * @return The textual representation of the version.
     */
    @GET
    @Produces("text/plain")
    public String getVersion() {
        try {
            final TextFile textFile = new TextFile(getClass()
                    .getResourceAsStream("/name/smithc/chris/version"));
            return textFile.getLines().get(0);
        } catch (IOException ex) {
            throw new WebApplicationException(ex,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
