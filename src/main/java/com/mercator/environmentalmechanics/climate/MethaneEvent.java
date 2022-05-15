package com.mercator.environmentalmechanics.climate;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MethaneEvent {

    public double methaneConcentration;
    private Map<String, Double> methaneGenValues;

    private File methaneGenValuesFR;
    private YamlConfiguration methaneGenValuesF;

    public MethaneEvent() {
        methaneGenValues = new HashMap<>();

        methaneGenValuesFR = new File("plugins/EnvironmentalMechanics/globalwarming/methanegenvalues.yml");
        methaneGenValuesF = YamlConfiguration.loadConfiguration(methaneGenValuesFR);

        if (!methaneGenValuesFR.exists()) {
            methaneGenValuesF.createSection("CHICKEN");
            methaneGenValuesF.createSection("COW");
            methaneGenValuesF.createSection("SHEEP");
            methaneGenValuesF.createSection("MOOSHROOM");
            methaneGenValuesF.createSection("RABBIT");
            methaneGenValuesF.createSection("PIG");
            methaneGenValuesF.createSection("minecraft:stone");
            methaneGenValuesF.createSection("minecraft:coal_ore");
            methaneGenValuesF.set("CHICKEN", 1.0);
            methaneGenValuesF.set("COW", 3.0);
            methaneGenValuesF.set("SHEEP", 2.0);
            methaneGenValuesF.set("MOOSHROOM", 3.5);
            methaneGenValuesF.set("RABBIT", 1.0);
            methaneGenValuesF.set("PIG", 2.5);
            methaneGenValuesF.set("minecraft:coal_ore", 1.0);
            methaneGenValuesF.set("minecraft:stone", 0.02);

            try {
                methaneGenValuesF.save(methaneGenValuesFR);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");

        if (!methaneValueF.exists()) {
            methaneConcentration = 0;

            PluginDataInterpreter.write(methaneValueF, methaneConcentration);
        }
        else {
            methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        }
    }

    @EventHandler
    public void onDeathOfEntity(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        String entityName = entity.getName();

        if (methaneGenValues.containsKey(entityName)) {
            methaneConcentration += methaneGenValues.get(entityName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration);
        }
    }

    @EventHandler
    public void onPlayerMineBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();
        String blockName = blockBroken.toString();

        if (methaneGenValues.containsKey(blockName)) {
            methaneConcentration += methaneGenValues.get(blockName);

            File methaneConcentrationF = new File("plugins/EnvironmentalMechanics/methane.txt");
            PluginDataInterpreter.write(methaneConcentrationF, methaneConcentration);
        }
    }
}
