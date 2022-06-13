package com.mercator.environmentalmechanics.climateeffects.effects;

import com.mercator.environmentalmechanics.climateeffects.EffectsEngine;
import com.mercator.environmentalmechanics.greenhouseengine.GreenhouseEngine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class AcidRainModule implements Runnable {

    private int taskID;

    private JavaPlugin javaPlugin;
    private World world;

    public AcidRainModule(JavaPlugin plugin, int initialDelay, int repeatDelay) {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, initialDelay, repeatDelay);

        javaPlugin = plugin;
        world = Bukkit.getWorld("world");
    }

    public void cancelLoop() {
        Bukkit.getScheduler().cancelTask(this.taskID);
    }

    public void acidRainCheck(LivingEntity entity) {
        Location location = entity.getLocation();
        Location highestLocation = world.getHighestBlockAt(location).getLocation();

        double nitrousOxideConcentration = GreenhouseEngine.nitrousOxideModule.nitrousOxideConcentration;
        double sensitivityFactor = EffectsEngine.getSensitivityFactor();

        if (location.getBlockY() >= highestLocation.getBlockY() && !world.isClearWeather() && ((300 * nitrousOxideConcentration) / sensitivityFactor) > 3.0) {
            entity.damage(1.0);
        }
    }

    public void run() {
        for (LivingEntity entity : world.getLivingEntities()) {
            acidRainCheck(entity);
        }
    }
}
