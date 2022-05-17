package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class CarbonDioxideEvent implements Listener {

    public double carbonDioxideConcentration;
    private Map<String, Double> carbonDioxideGenValues;

    public CarbonDioxideEvent() {
        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");

        if (!carbonDioxideValueF.exists()) {
            carbonDioxideConcentration = 0;

            PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration);
        }
        else {
            carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        }

        carbonDioxideGenValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson(Paths.get("carbonDioxideGenValues.json"));
    }

    @EventHandler
    public void onPlayerBurnItemInFurnace(FurnaceBurnEvent event) {
        ItemStack itemBurned = event.getFuel();
        String itemName = itemBurned.getType().getKey().toString();

        carbonDioxideConcentration += carbonDioxideGenValues.get(itemName);

        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration);
    }
}
