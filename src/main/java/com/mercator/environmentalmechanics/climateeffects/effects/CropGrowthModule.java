package com.mercator.environmentalmechanics.climateeffects.effects;

import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.LinearEquation;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.List;
import java.util.Map;

public class CropGrowthModule {
    public void cropGrowthDelay(BlockGrowEvent event, Map<String, List<Double>> cropTemperatures, ClimateEngine climateEngine) {
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
}
