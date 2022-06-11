package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climateeffects.EffectsEngine;
import com.mercator.environmentalmechanics.commands.admin.CommandRawTemperature;
import com.mercator.environmentalmechanics.commands.admin.CommandSetCarbonDioxide;
import com.mercator.environmentalmechanics.commands.admin.CommandSetMethane;
import com.mercator.environmentalmechanics.commands.admin.CommandSetNitrousOxide;
import com.mercator.environmentalmechanics.commands.general.*;
import com.mercator.environmentalmechanics.greenhouseengine.GreenhouseEngine;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        GreenhouseEngine greenhouseEngine =  new GreenhouseEngine();
        EffectsEngine effectsEngine = new EffectsEngine();

        getServer().getPluginManager().registerEvents(greenhouseEngine, this);
        getServer().getPluginManager().registerEvents(effectsEngine, this);

        this.getCommand("getco2").setExecutor(new CommandGetCarbonDioxide());
        this.getCommand("getch4").setExecutor(new CommandGetMethane());
        this.getCommand("getn2o").setExecutor(new CommandGetNitrousOxide());

        this.getCommand("temperature").setExecutor(new CommandTemperature());
        this.getCommand("rawtemperature").setExecutor(new CommandRawTemperature());

        this.getCommand("setco2").setExecutor(new CommandSetCarbonDioxide());
        this.getCommand("setch4").setExecutor(new CommandSetMethane());
        this.getCommand("setn2o").setExecutor(new CommandSetNitrousOxide());

        this.getCommand("latitude").setExecutor(new CommandLatitude());
        this.getCommand("sealevel").setExecutor(new CommandGetSeaLevel());

        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics enabled successfully!");
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics disabled successfully!");
    }
}
