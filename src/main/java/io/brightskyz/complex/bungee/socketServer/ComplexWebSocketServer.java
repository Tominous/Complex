package io.brightskyz.complex.bungee.socketServer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.brightskyz.complex.Bungee;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class ComplexWebSocketServer extends WebSocketServer {

    private Bungee bungee;

    public ComplexWebSocketServer(Bungee bungee, Integer listenPort) {
        super(new InetSocketAddress(listenPort));
        this.bungee = bungee;
        bungee.getLogger().info("Websocket server started.");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        bungee.getLogger().info("Websocket connection received.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        bungee.getLogger().info("Websocket connection closed.");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        JsonObject json;
        try {
            json = new JsonParser().parse(message).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            bungee.getLogger().warning("Failed parsing the json received.");
            return;
        }
        if (!json.has("name") || !json.has("data")) {
            bungee.getLogger().warning("Json does not contain the name and data.");
        }
        for (SocketServerListener serverSocketListener : bungee.getSocketServerModule().getListenerMap().values()) {
            if (serverSocketListener.getEventName().equalsIgnoreCase(json.get("name").getAsString())) {
                serverSocketListener.onEvent(json.get("data").getAsString());
                return;
            }
        }
        bungee.getLogger().warning("Received request for unregistered event: " + json.get("name").getAsString());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        bungee.getLogger().info("Websocket server started.");
    }
}

