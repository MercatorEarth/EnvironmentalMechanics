package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climate.CarbonDioxideEvent;
import com.mercator.environmentalmechanics.climate.MethaneEvent;
import com.mercator.environmentalmechanics.climate.NitrousOxideEvent;
import com.mercator.environmentalmechanics.climate.commands.CommandGetCarbonDioxide;
import com.mercator.environmentalmechanics.climate.commands.CommandGetMethane;
import com.mercator.environmentalmechanics.climate.commands.CommandGetNitrousOxide;
import com.mercator.environmentalmechanics.climate.commands.CommandTemperature;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        CarbonDioxideEvent carbonDioxideEvent = new CarbonDioxideEvent();
        MethaneEvent methaneEvent = new MethaneEvent();
        NitrousOxideEvent nitrousOxideEvent = new NitrousOxideEvent();

        getServer().getPluginManager().registerEvents(carbonDioxideEvent, this);
        getServer().getPluginManager().registerEvents(methaneEvent, this);
        getServer().getPluginManager().registerEvents(nitrousOxideEvent, this);

        this.getCommand("getco2").setExecutor(new CommandGetCarbonDioxide());
        this.getCommand("getch4").setExecutor(new CommandGetMethane());
        this.getCommand("getn2o").setExecutor(new CommandGetNitrousOxide());
        this.getCommand("temperature").setExecutor(new CommandTemperature());

        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics enabled successfully!");
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics disabled successfully!");
    }
}
