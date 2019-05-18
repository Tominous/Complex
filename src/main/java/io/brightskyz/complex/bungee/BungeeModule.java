package io.brightskyz.complex.bungee;

import io.brightskyz.complex.Bungee;

public class BungeeModule {

    private Bungee bungee;

    public BungeeModule(Bungee bungee) {
        this.bungee = bungee;
    }

    public void onEnable() {
        //
    }

    public void onDisable() {
        //
    }

    public Bungee getBungee() {
        return bungee;
    }
}
