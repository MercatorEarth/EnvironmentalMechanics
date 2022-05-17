package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class MethaneEvent implements Listener {

    public double methaneConcentration;
    private Map<String, Double> methaneGenValues;

    public MethaneEvent() {
        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");

        if (!methaneValueF.exists()) {
            methaneConcentration = 0;

            PluginDataInterpreter.write(methaneValueF, methaneConcentration);
        }
        else {
            methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        }

        methaneGenValues = (Map<String, Double>) PluginDataInterpreter.genMapFromJson(Paths.get("methaneGenValues.json"));
    }

    @EventHandler
    public void onDeathOfEntity(EntityDeathEvent event) {
        String entityName = event.getEntityType().toString();

        if (methaneGenValues.containsKey(entityName)) {
            methaneConcentration += methaneGenValues.get(entityName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration);
        }
    }

    @EventHandler
    public void onPlayerMineBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();
        String blockName = blockBroken.getType().getKey().toString();

        if (methaneGenValues.containsKey(blockName)) {
            methaneConcentration += methaneGenValues.get(blockName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration);
        }
    }
}
