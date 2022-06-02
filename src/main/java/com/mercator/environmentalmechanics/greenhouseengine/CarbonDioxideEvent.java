package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.TreeType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class CarbonDioxideEvent implements Listener {

    public double carbonDioxideConcentration;
    private Map<String, Double> carbonDioxideGenValues;
    private Map<String, Double> carbonDioxideRemoveValues;

    public CarbonDioxideEvent() {
        carbonDioxideGenValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/gas_models/carbonDioxideGenValues.json");
        carbonDioxideRemoveValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/environment_models/treeCarbonDioxideRemoveValues.json");

        readCarbonDioxideValue();
    }

    public void readCarbonDioxideValue() {
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
    }

    @EventHandler
    public void onPlayerBurnItemInFurnace(FurnaceBurnEvent event) {
        readCarbonDioxideValue();

        ItemStack itemBurned = event.getFuel();
        String itemName = itemBurned.getType().getKey().toString();

        carbonDioxideConcentration += carbonDioxideGenValues.get(itemName);

        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration, "globalwarming");
    }

    @EventHandler
    public void treeGrowth(StructureGrowEvent event) {
        readCarbonDioxideValue();

        TreeType treeType = event.getSpecies();
        String species = treeType.toString();

        carbonDioxideConcentration -= carbonDioxideRemoveValues.get(species);

        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration, "globalwarming");
    }
}
