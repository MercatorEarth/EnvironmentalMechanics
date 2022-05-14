package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climate.GlobalWarming;
import com.mercator.environmentalmechanics.climate.commands.CommandGetCarbonDioxide;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        GlobalWarming globalWarming = new GlobalWarming();
        getServer().getPluginManager().registerEvents(globalWarming, this);
        this.getCommand("getco2").setExecutor(new CommandGetCarbonDioxide());

        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics enabled successfully!");
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics disabled successfully!");
    }
}
