package com.mercator.environmentalmechanics.climateeffects;

import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.LinearEquation;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class EffectsEngine implements Listener {

    private ClimateEngine climateEngine;
    private Map<String, List<Double>> cropTemperatures;

    private RaisedSeaLevel raisedSeaLevel;

    private JavaPlugin javaPlugin;

    public EffectsEngine() {
        javaPlugin = (JavaPlugin) getServer().getPluginManager().getPlugin("EnvironmentalMechanics");

        climateEngine = new ClimateEngine();
        cropTemperatures = (Map<String, List<Double>>) PluginDataInterpreter.genMapFromJson("models/crop_models/idealCropTemperatures.json");
        raisedSeaLevel = new RaisedSeaLevel(javaPlugin, 400, 400);
    }

    @EventHandler
    public void flowEvent(BlockFromToEvent event) {
        Block reference = event.getBlock();
        Material material = reference.getType();

        if (material.equals(Material.WATER) && reference.hasMetadata("flooded")) {
            event.setCancelled(true);
        }
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
        Chunk reference = location.getChunk();

        double failChance = 0.0;

        boolean loadedSuccessfully = true;

        if (!reference.isLoaded()) {
            loadedSuccessfully = reference.load();
        }

        if (loadedSuccessfully) {
            double chunkTemperature = climateEngine.getAverageTemperature(reference);

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
