package io.brightskyz.complex;

import io.brightskyz.complex.bungee.motd.MotdModule;
import io.brightskyz.complex.bungee.socketServer.SocketServerModule;
import io.brightskyz.complex.helpers.BungeeConfig;
import io.brightskyz.complex.helpers.Cache;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bungee extends Plugin {

    private BungeeConfig bungeeConfig;

    private Connection connection = null;
    private Cache cache = new Cache();
    private MotdModule motdModule = new MotdModule(this);
    private SocketServerModule socketServerModule = new SocketServerModule(this);

    @Override
    public void onEnable() {
        bungeeConfig = new BungeeConfig(this, "bungee-config.yml", true);
        getLogger().info("Enabled the Complex Bungeecord plugin.");
        socketServerModule.onEnable();
        motdModule.onEnable();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled the Complex Bungeecord plugin.");
    }

    public Cache getCache() {
        return cache;
    }

    public Connection getDatabaseConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + getBungeeConfig().get().getString("mysql-host")+ ":" + getBungeeConfig().get().getInt("mysql-port") + "/" + getBungeeConfig().get().getString("mysql-database"), getBungeeConfig().get().getString("mysql-username"), getBungeeConfig().get().getString("mysql-password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else try {
            if (connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://" + getBungeeConfig().get().getString("mysql-host")+ ":" + getBungeeConfig().get().getInt("mysql-port") + "/" + getBungeeConfig().get().getString("mysql-database"), getBungeeConfig().get().getString("mysql-username"), getBungeeConfig().get().getString("mysql-password"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public SocketServerModule getSocketServerModule() {
        return socketServerModule;
    }

    public BungeeConfig getBungeeConfig() {
        return bungeeConfig;
    }
}
