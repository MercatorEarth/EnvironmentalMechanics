package com.mercator.environmentalmechanics.climateengine;

import com.mercator.environmentalmechanics.datamanagement.LinearEquation;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.List;
import java.util.Map;

public class WarmingEffects implements Listener {

    private ClimateEngine climateEngine;
    private Map<String, List<Double>> cropTemperatures;

    public WarmingEffects() {
        climateEngine = new ClimateEngine();
        cropTemperatures = (Map<String, List<Double>>) PluginDataInterpreter.genMapFromJson("models/crop_models/idealCropTemperatures.json");
    }

    public static void raisedSeaLevel(int amount) {
        int seaLevel = 63 + amount;
    }

    @EventHandler
    public void cropGrowthDelay(BlockGrowEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        String blockName = block.getType().getKey().toString();

        List<Double> temperatureRange = cropTemperatures.get(blockName);

        double temperature = climateEngine.getTemperatureAt(location);

        double minimumTemperature = temperatureRange.get(0);
        double idealTemperature = temperatureRange.get(1);
        double maximumTemperature = temperatureRange.get(2);

        double maximumFailChance = 1.0;
        double minimumFailChance = 0.0;

        double failChance = 0.0;

        LinearEquation lowEquation = new LinearEquation();
        LinearEquation highEquation = new LinearEquation();

        lowEquation.generate(idealTemperature, minimumFailChance, minimumTemperature, maximumFailChance);
        highEquation.generate(idealTemperature, minimumFailChance, maximumTemperature, maximumFailChance);

        if (temperature < idealTemperature) {
            failChance = (lowEquation.slope * temperature) + lowEquation.yIntercept;
        }
        else if (temperature >= idealTemperature) {
            failChance = (highEquation.slope * temperature) + highEquation.yIntercept;
        }

        if (Math.random() <= failChance) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void forestFire(EntitySpawnEvent event) {
        Location location = event.getLocation();

        double failChance = 0.4 * (climateEngine.getTemperatureAt(location) - 14);
    }
}
