package com.mercator.environmentalmechanics.climateengine.warmingeffects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterFlowFix implements Listener {

    boolean rising;

    @EventHandler
    public void flowEvent(BlockFromToEvent event) {
        rising = RaisedSeaLevel.isRising();

        Block reference = event.getBlock();
        Material material = reference.getType();

        if (material.equals(Material.WATER) && reference.hasMetadata("flooded")) {
            event.setCancelled(true);
        }
    }
}
