package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.io.File;

public class WarmingEffects implements Listener {

    public static double getTemperature() {
        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");

        double carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        double methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        double nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));

        double temperatureValue = 13 + (((1.0 * carbonDioxideConcentration) + (25.0 * methaneConcentration) + (300 * nitrousOxideConcentration)) / 10000.0f);

        return temperatureValue;
    }

    public static void raisedSeaLevel(int amount) {
        int seaLevel = 63 + amount;
    }

    @EventHandler
    public void cropGrowthDelay(BlockGrowEvent event) {
        double failChance = 0.2 * (getTemperature() - 13);

        if (Math.random() <= failChance) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void forestFire(EntitySpawnEvent event) {
        double failChance = 0.4 * (getTemperature() - 14);
    }
}
