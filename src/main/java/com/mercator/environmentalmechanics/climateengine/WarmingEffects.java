package com.mercator.environmentalmechanics.climateengine;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WarmingEffects implements Listener {

    public static void raisedSeaLevel(int amount) {
        int seaLevel = 63 + amount;
    }

    @EventHandler
    public void cropGrowthDelay(BlockGrowEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();

        double failChance = 0.2 * (ClimateEngine.getTemperatureAt(location) - 13);

        if (Math.random() <= failChance) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void forestFire(EntitySpawnEvent event) {
        Location location = event.getLocation();

        double failChance = 0.4 * (ClimateEngine.getTemperatureAt(location) - 14);
    }
}
