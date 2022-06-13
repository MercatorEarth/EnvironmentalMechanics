package com.mercator.environmentalmechanics.climateeffects;

import com.mercator.environmentalmechanics.climateeffects.effects.*;
import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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
    public WaterPollutionModule waterPollutionModule;

    private JavaPlugin javaPlugin;

    public EffectsEngine() {
        javaPlugin = (JavaPlugin) getServer().getPluginManager().getPlugin("EnvironmentalMechanics");

        climateEngine = new ClimateEngine();
        cropTemperatures = (Map<String, List<Double>>) PluginDataInterpreter.genMapFromJson("models/crop_models/idealCropTemperatures.json");

        raisedSeaLevelModule = new RaisedSeaLevelModule(javaPlugin, 400, 400);
        acidRainModule = new AcidRainModule(javaPlugin, 40, 40);
        waterPollutionModule = new WaterPollutionModule(javaPlugin, 100, 100);
        cropGrowthModule = new CropGrowthModule();
        forestFireModule = new ForestFireModule();
    }

    public static double getSensitivityFactor() {
        double sensitivityFactor;

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

        return sensitivityFactor;
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
