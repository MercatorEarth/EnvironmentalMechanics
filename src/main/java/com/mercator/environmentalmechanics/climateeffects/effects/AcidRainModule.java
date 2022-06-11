package com.mercator.environmentalmechanics.climateeffects.effects;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import com.mercator.environmentalmechanics.greenhouseengine.GreenhouseEngine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class AcidRainModule implements Runnable {

    private int taskID;
    private double sensitivityFactor;

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

        File sensitivityFactorF = new File("plugins/EnvironmentalMechanics/globalwarming/sensitivity.txt");
        if (!sensitivityFactorF.exists()) {
            sensitivityFactor = 10000.0;
            try {
                sensitivityFactorF.createNewFile();
                PluginDataInterpreter.write(sensitivityFactorF, sensitivityFactor, "globalwarming");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            sensitivityFactor = Double.parseDouble(PluginDataInterpreter.read(sensitivityFactorF));
        }

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
