package io.brightskyz.complex.bungee.socketServer;

public abstract class SocketServerListener {

    private SocketServerModule socketServerModule;
    private String eventName;

    public SocketServerListener(SocketServerModule socketServerModule, String eventName) {
        this.socketServerModule = socketServerModule;
        this.eventName = eventName;
    }

    public abstract void onEvent(String data);

    public SocketServerModule getSocketServerModule() {
        return socketServerModule;
    }

    public String getEventName() {
        return eventName;
    }
}
