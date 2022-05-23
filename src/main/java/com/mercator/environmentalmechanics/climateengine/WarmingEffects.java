package com.mercator.environmentalmechanics.climateengine;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WarmingEffects implements Listener {

    private ClimateEngine climateEngine;

    public WarmingEffects() {
        climateEngine = new ClimateEngine();
    }

    public static void raisedSeaLevel(int amount) {
        int seaLevel = 63 + amount;
    }

    @EventHandler
    public void cropGrowthDelay(BlockGrowEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();

        double failChance = 0.2 * (climateEngine.getTemperatureAt(location) - 13);

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
