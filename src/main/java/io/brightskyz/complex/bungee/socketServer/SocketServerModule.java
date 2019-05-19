package io.brightskyz.complex.bungee.socketServer;

import io.brightskyz.complex.Bungee;
import io.brightskyz.complex.bungee.BungeeModule;

import java.util.HashMap;
import java.util.Map;

public class SocketServerModule extends BungeeModule {

    private ComplexWebSocketServer complexWebSocketServer = null;
    private static Map<String, SocketServerListener> listenerMap = new HashMap<>();

    public SocketServerModule(Bungee bungee) {
        super(bungee);
    }

    @Override
    public void onEnable() {
        complexWebSocketServer = new ComplexWebSocketServer(getBungee(), 24080);

        // Register listeners

        complexWebSocketServer.start();
    }

    public void registerListener(SocketServerListener serverSocketListener) {
        listenerMap.put(serverSocketListener.getEventName(), serverSocketListener);
        getBungee().getLogger().info("Registered socket server listener: " + serverSocketListener.getEventName());
    }

    public Map<String, SocketServerListener> getListenerMap() {
        return listenerMap;
    }
}
