package com.mercator.environmentalmechanics.climateeffects;

import com.mercator.environmentalmechanics.climateeffects.effects.AcidRainModule;
import com.mercator.environmentalmechanics.climateeffects.effects.CropGrowthModule;
import com.mercator.environmentalmechanics.climateeffects.effects.ForestFireModule;
import com.mercator.environmentalmechanics.climateeffects.effects.RaisedSeaLevelModule;
import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
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

    public RaisedSeaLevelModule raisedSeaLevelModule;
    public ForestFireModule forestFireModule;
    public CropGrowthModule cropGrowthModule;
    public AcidRainModule acidRainModule;

    private JavaPlugin javaPlugin;

    public EffectsEngine() {
        javaPlugin = (JavaPlugin) getServer().getPluginManager().getPlugin("EnvironmentalMechanics");

        climateEngine = new ClimateEngine();
        cropTemperatures = (Map<String, List<Double>>) PluginDataInterpreter.genMapFromJson("models/crop_models/idealCropTemperatures.json");

        raisedSeaLevelModule = new RaisedSeaLevelModule(javaPlugin, 400, 400);
        acidRainModule = new AcidRainModule(javaPlugin, 40, 40);
        forestFireModule = new ForestFireModule();
    }

    @EventHandler
    public void blockFromToEvent(BlockFromToEvent event) {
        raisedSeaLevelModule.flowEvent(event);
    }

    @EventHandler
    public void blockGrowEvent(BlockGrowEvent event) {
        cropGrowthModule.cropGrowthDelay(event, cropTemperatures, climateEngine);
    }

    @EventHandler
    public void entitySpawnEvent(EntitySpawnEvent event) {
        forestFireModule.forestFire(event, climateEngine);
    }
}
