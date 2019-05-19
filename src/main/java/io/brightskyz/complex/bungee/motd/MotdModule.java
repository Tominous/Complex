package io.brightskyz.complex.bungee.motd;

import io.brightskyz.complex.Bungee;
import io.brightskyz.complex.bungee.BungeeModule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotdModule extends BungeeModule {

    public MotdModule(Bungee bungee) {
        super(bungee);
    }

    @Override
    public void onEnable() {
        getBungee().getCache().getShard("settings").set("motd", "&f         &9&m---&8&m&l[-&f  &6&lComplex &f&lGames &8[&7US&8] &8&m&l-]&9&m---&r");
        try {
            PreparedStatement statement = getBungee().getDatabaseConnection().prepareStatement("SELECT * FROM settings WHERE name = 'motd'");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                getBungee().getCache().getShard("settings").set("motd", resultSet.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getBungee().getProxy().getPluginManager().registerListener(getBungee(), new ProxyPingListener(getBungee()));
    }
}
