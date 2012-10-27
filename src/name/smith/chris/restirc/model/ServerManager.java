package name.smith.chris.restirc.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of servers.
 */
public class ServerManager {

    private final Map<String, Server> servers
            = Collections.synchronizedMap(new HashMap<String, Server>());

    public boolean hasServer(String id) {
        return servers.containsKey(id);
    }

    public Server getServer(String id) {
        return servers.get(id);
    }

    public void addServer(String id, Server server) {
        servers.put(id, server);
        server.connect();
    }

    public Collection<String> getAllIds() {
        return Collections.unmodifiableCollection(servers.keySet());
    }

}
