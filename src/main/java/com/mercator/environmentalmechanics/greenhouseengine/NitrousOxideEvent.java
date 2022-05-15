package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.util.Map;

public class NitrousOxideEvent implements Listener {

    public double nitrousOxideConcentration;
    private Map<String, Double> nitrousOxideGenValues;

    public NitrousOxideEvent() {
        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");

        if (!nitrousOxideValueF.exists()) {
            nitrousOxideConcentration = 0;

            PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration);
        }
        else {
            nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));
        }
    }

    @EventHandler
    public void onPlayerFertilizeBlock(BlockFertilizeEvent event) {
        nitrousOxideConcentration += 0.25;

        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");
        PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration);
    }

    @EventHandler
    public void onPlayerFinishComposting (PlayerInteractEvent event) {
        Block blockInteracted = event.getClickedBlock();

        if (!blockInteracted.equals(null)) {
            String blockName = blockInteracted.getType().getKey().toString();

            if (blockName.equals("minecraft:composter")) {
                Levelled levelled = (Levelled) blockInteracted.getBlockData();
                Integer level = levelled.getLevel();

                if (level == 8) {
                    nitrousOxideConcentration += 1.0;

                    File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");
                    PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration);
                }
            }
        }
    }
}