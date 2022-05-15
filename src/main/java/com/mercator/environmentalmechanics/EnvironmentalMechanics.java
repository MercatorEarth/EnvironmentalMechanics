package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climate.CarbonDioxideEvent;
import com.mercator.environmentalmechanics.climate.MethaneEvent;
import com.mercator.environmentalmechanics.climate.commands.CommandGetCarbonDioxide;
import com.mercator.environmentalmechanics.climate.commands.CommandGetMethane;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        CarbonDioxideEvent carbonDioxideEvent = new CarbonDioxideEvent();
        MethaneEvent methaneEvent = new MethaneEvent();

        getServer().getPluginManager().registerEvents(carbonDioxideEvent, this);
        getServer().getPluginManager().registerEvents(methaneEvent, this);

        this.getCommand("getco2").setExecutor(new CommandGetCarbonDioxide());
        this.getCommand("getch4").setExecutor(new CommandGetMethane());

        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics enabled successfully!");
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics disabled successfully!");
    }
}
