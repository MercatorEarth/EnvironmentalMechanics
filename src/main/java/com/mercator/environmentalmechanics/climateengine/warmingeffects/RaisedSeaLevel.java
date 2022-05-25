package com.mercator.environmentalmechanics.climateengine.warmingeffects;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class RaisedSeaLevel implements Runnable {

    private int taskID;

    public RaisedSeaLevel(JavaPlugin plugin, int initialDelay, int repeatDelay) {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, initialDelay, repeatDelay);
    }

    public void cancelLoop() {
        Bukkit.getScheduler().cancelTask(this.taskID);
    }

    public void raisedSeaLevel(int amount) {
        int seaLevel = 63 + amount;
    }
}
