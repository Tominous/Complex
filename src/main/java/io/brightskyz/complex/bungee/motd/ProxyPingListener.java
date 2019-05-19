package io.brightskyz.complex.bungee.motd;

import io.brightskyz.complex.Bungee;
import io.brightskyz.complex.bungee.BungeeListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.event.EventHandler;

import java.util.Random;

public class ProxyPingListener extends BungeeListener {

    public ProxyPingListener(Bungee bungee) {
        super(bungee);
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();

        int online = getBungee().getProxy().getOnlineCount();
        response.setPlayers(new ServerPing.Players(online + 1, online, new ServerPing.PlayerInfo[0]));

        String motd = getBungee().getCache().getShard("settings").getString("motd");
        motd = motd.replace("\\n", "\n");
        response.setDescriptionComponent(new TextComponent(ChatColor.translateAlternateColorCodes('&', motd)));

        event.setResponse(response);
    }
}
