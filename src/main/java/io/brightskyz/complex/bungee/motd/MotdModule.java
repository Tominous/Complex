package io.brightskyz.complex.bungee.motd;

import io.brightskyz.complex.Bungee;
import io.brightskyz.complex.bungee.BungeeModule;

public class MotdModule extends BungeeModule {

    public MotdModule(Bungee bungee) {
        super(bungee);
    }

    @Override
    public void onEnable() {
        getBungee().getCache().getShard("settings").set("motd", "&f         &9&m---&8&m&l[-&f  &6&lMineplex &f&lGames &8[&7US&8] &8&m&l-]&9&m---&r\n                     &c&lCLANS SEASON 6");
        getBungee().getProxy().getPluginManager().registerListener(getBungee(), new ProxyPingListener(getBungee()));
    }
}
