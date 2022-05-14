package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climate.GlobalWarming;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        GlobalWarming globalWarming = new GlobalWarming();
        getServer().getPluginManager().registerEvents(globalWarming, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
