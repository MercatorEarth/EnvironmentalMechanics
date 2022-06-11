package com.mercator.environmentalmechanics.greenhouseengine.gases;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class NitrousOxideModule {

    public double nitrousOxideConcentration;
    private Map<String, Double> nitrousOxideGenValues;

    public NitrousOxideModule() {
        readNitrousOxideValue();
    }

    public void readNitrousOxideValue() {
        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");

        if (!nitrousOxideValueF.exists()) {
            nitrousOxideConcentration = 0;

            try {
                nitrousOxideValueF.createNewFile();
            }
            catch (IOException e) {
                getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().warning("Failed to create data file for Nitrous Oxide!");
            }

            PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration, "globalwarming");
        }
        else {
            nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));
        }
    }

    public void onPlayerFertilizeBlock(BlockFertilizeEvent event) {
        readNitrousOxideValue();

        nitrousOxideConcentration += 0.0125;

        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");
        PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration, "globalwarming");
    }

    public void onPlayerFinishComposting(PlayerInteractEvent event) {
        readNitrousOxideValue();

        try {
            Block blockInteracted = event.getClickedBlock();
            String blockName = blockInteracted.getType().getKey().toString();

            if (blockName.equals("minecraft:composter")) {
                Levelled levelled = (Levelled) blockInteracted.getBlockData();
                Integer level = levelled.getLevel();

                if (level == 8) {
                    nitrousOxideConcentration += 0.05;

                    File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");
                    PluginDataInterpreter.write(nitrousOxideValueF, nitrousOxideConcentration, "globalwarming");
                }
            }
        }
        catch (NullPointerException e) {
            assert true;
        }
    }
}
