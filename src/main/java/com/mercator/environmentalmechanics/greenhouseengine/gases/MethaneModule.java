package com.mercator.environmentalmechanics.greenhouseengine.gases;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class MethaneModule {

    public double methaneConcentration;
    private Map<String, Double> methaneGenValues;

    public MethaneModule() {
        methaneGenValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/gas_models/methaneGenValues.json");

        readMethaneValue();
    }

    public void readMethaneValue() {
        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");

        if (!methaneValueF.exists()) {
            methaneConcentration = 0;

            try {
                methaneValueF.createNewFile();
            }
            catch (IOException e) {
                getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().warning("Failed to create data file for Methane!");
            }

            PluginDataInterpreter.write(methaneValueF, methaneConcentration, "globalwarming");
        }
        else {
            methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        }
    }

    public void onDeathOfEntity(EntityDeathEvent event) {
        readMethaneValue();

        String entityName = event.getEntityType().toString();

        if (methaneGenValues.containsKey(entityName)) {
            methaneConcentration += methaneGenValues.get(entityName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration, "globalwarming");
        }
    }

    public void onPlayerMineBlock(BlockBreakEvent event) {
        readMethaneValue();

        Block blockBroken = event.getBlock();
        String blockName = blockBroken.getType().getKey().toString();

        if (methaneGenValues.containsKey(blockName)) {
            methaneConcentration += methaneGenValues.get(blockName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration, "globalwarming");
        }
    }
}
