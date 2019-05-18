package io.brightskyz.complex.bungee;

import io.brightskyz.complex.Bungee;
import net.md_5.bungee.api.plugin.Listener;

public class BungeeListener implements Listener {

    private Bungee bungee;

    public BungeeListener(Bungee bungee) {
        this.bungee = bungee;
    }

    public Bungee getBungee() {
        return bungee;
    }
}
