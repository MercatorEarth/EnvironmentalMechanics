package com.mercator.environmentalmechanics;

import com.mercator.environmentalmechanics.climateengine.warmingeffects.RaisedSeaLevel;
import com.mercator.environmentalmechanics.climateengine.warmingeffects.WarmingEffects;
import com.mercator.environmentalmechanics.commands.admin.CommandRawTemperature;
import com.mercator.environmentalmechanics.commands.admin.CommandSetCarbonDioxide;
import com.mercator.environmentalmechanics.commands.admin.CommandSetMethane;
import com.mercator.environmentalmechanics.commands.admin.CommandSetNitrousOxide;
import com.mercator.environmentalmechanics.commands.general.*;
import com.mercator.environmentalmechanics.greenhouseengine.CarbonDioxideEvent;
import com.mercator.environmentalmechanics.greenhouseengine.MethaneEvent;
import com.mercator.environmentalmechanics.greenhouseengine.NitrousOxideEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvironmentalMechanics extends JavaPlugin {

    RaisedSeaLevel raisedSeaLevel;

    @Override
    public void onEnable() {
        CarbonDioxideEvent carbonDioxideEvent = new CarbonDioxideEvent();
        MethaneEvent methaneEvent = new MethaneEvent();
        NitrousOxideEvent nitrousOxideEvent = new NitrousOxideEvent();
        WarmingEffects warmingEffects = new WarmingEffects();

        getServer().getPluginManager().registerEvents(carbonDioxideEvent, this);
        getServer().getPluginManager().registerEvents(methaneEvent, this);
        getServer().getPluginManager().registerEvents(nitrousOxideEvent, this);
        getServer().getPluginManager().registerEvents(warmingEffects, this);

        this.getCommand("getco2").setExecutor(new CommandGetCarbonDioxide());
        this.getCommand("getch4").setExecutor(new CommandGetMethane());
        this.getCommand("getn2o").setExecutor(new CommandGetNitrousOxide());

        this.getCommand("temperature").setExecutor(new CommandTemperature());
        this.getCommand("rawtemperature").setExecutor(new CommandRawTemperature());

        this.getCommand("setco2").setExecutor(new CommandSetCarbonDioxide());
        this.getCommand("setch4").setExecutor(new CommandSetMethane());
        this.getCommand("setn2o").setExecutor(new CommandSetNitrousOxide());

        this.getCommand("latitude").setExecutor(new CommandLatitude());

        raisedSeaLevel = new RaisedSeaLevel(this, 400, 400);

        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics enabled successfully!");
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().info("Environmental Mechanics disabled successfully!");
        raisedSeaLevel.cancelLoop();
    }
}
