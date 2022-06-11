package com.mercator.environmentalmechanics.climateeffects.effects;

import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.LinearEquation;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ForestFireModule {

    public void forestFire(EntitySpawnEvent event, ClimateEngine climateEngine) {
        Location location = event.getLocation();
        World world = location.getWorld();
        Chunk reference = location.getChunk();

        if (location.getBlockY() == 0) {
            location.setY(1);
        }

        if (world.getName().equals("world")) {

            double failChance;

            if (reference.isLoaded()) {
                double chunkTemperature = climateEngine.getTemperatureAt(location);

                double minimumFailChance = 0.0;
                double maximumFailChance = 1.0;

                double minimumTemperature = 40.0;
                double maximumTemperature = 60.0;

                LinearEquation failChanceEquation = new LinearEquation();
                failChanceEquation.generate(minimumTemperature, maximumFailChance, maximumTemperature, minimumFailChance);

                failChance = (failChanceEquation.slope * chunkTemperature) + failChanceEquation.yIntercept;

                if (Math.random() >= failChance) {
                    reference.getWorld().getBlockAt(location).setType(Material.FIRE);
                }
            }
        }
    }
}
