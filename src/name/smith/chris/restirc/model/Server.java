package name.smith.chris.restirc.model;

import com.dmdirc.parser.common.CallbackManager;
import com.dmdirc.parser.interfaces.Parser;
import com.dmdirc.parser.interfaces.callbacks.CallbackInterface;
import com.dmdirc.parser.interfaces.callbacks.DebugInfoListener;
import com.dmdirc.parser.interfaces.callbacks.ServerReadyListener;
import com.dmdirc.parser.irc.IRCParser;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * Represents a server.
 */
@Slf4j
public class Server {

    private Parser parser;

    private final List<URI> addresses = new CopyOnWriteArrayList<URI>();
    private final List<String> channels = new CopyOnWriteArrayList<String>();

    private int nextAddress = 0;

    public void connect() {
        log.info("Connecting...");

        this.parser = new IRCParser(addresses.get(nextAddress));
        final CallbackManager callback = this.parser.getCallbackManager();
        final Listener listener = new Listener();

        for (Class<?> clazz : Listener.class.getInterfaces()) {
            callback.addCallback((Class<CallbackInterface>) clazz, listener);
        }

        this.parser.connect();
    }

    public void addAddress(final URI address) {
        log.info("Adding address: {}", address);
        this.addresses.add(address);
    }

    public void addChannel(final String channel) {
        log.info("Adding channel: {}", channel);
        this.channels.add(channel);
    }

    private class Listener implements ServerReadyListener, DebugInfoListener {

        @Override
        public void onServerReady(Parser parser, Date date) {
            for (String channel : channels) {
                parser.joinChannel(channel);
            }
        }

        @Override
        public void onDebugInfo(Parser parser, Date date, int i, String string) {
            log.debug(string);
        }

    }

}
