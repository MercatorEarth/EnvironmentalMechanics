package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class CarbonDioxideEvent implements Listener {

    public double carbonDioxideConcentration;
    private Map<String, Double> carbonDioxideGenValues;

    public CarbonDioxideEvent() {
        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");

        if (!carbonDioxideValueF.exists()) {
            carbonDioxideConcentration = 0;

            try {
                carbonDioxideValueF.createNewFile();
            }
            catch (IOException e) {
                getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().warning("Failed to create data file for Carbon Dioxide!");
            }

            PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration, "globalwarming");
        }
        else {
            carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        }

        carbonDioxideGenValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/gas_models/carbonDioxideGenValues.json");
    }

    @EventHandler
    public void onPlayerBurnItemInFurnace(FurnaceBurnEvent event) {
        ItemStack itemBurned = event.getFuel();
        String itemName = itemBurned.getType().getKey().toString();

        carbonDioxideConcentration += carbonDioxideGenValues.get(itemName);

        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration, "globalwarming");
    }
}
