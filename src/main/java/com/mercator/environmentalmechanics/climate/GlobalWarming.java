package com.mercator.environmentalmechanics.climate;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GlobalWarming implements Listener {

    public int carbonDioxideConcentration;
    public int methaneConcentration;
    public int nitrousOxideConcentration;
    public int waterVaporConcentration;

    private Map<String, Integer> carbonDioxideGenValues;
    private Map<String, Integer> methaneGenValues;
    private Map<String, Integer> nitrousOxideGenValues;
    private Map<String, Integer> waterVaporGenValues;

    private File carbonDioxideGenValuesFR;
    private YamlConfiguration carbonDioxideGenValuesF;

    public GlobalWarming() {
        carbonDioxideGenValues = new HashMap<>();
        methaneGenValues = new HashMap<>();
        nitrousOxideGenValues = new HashMap<>();
        waterVaporGenValues = new HashMap<>();

        carbonDioxideGenValuesFR = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.yml");
        carbonDioxideGenValuesF = YamlConfiguration.loadConfiguration(carbonDioxideGenValuesFR);

        if (!carbonDioxideGenValuesFR.exists()) {
            carbonDioxideGenValuesF.createSection("minecraft:bamboo");
            carbonDioxideGenValuesF.set("minecraft:bamboo", null);

            carbonDioxideGenValuesF.createSection("minecraft:white_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:orange_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:magenta_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:light_blue_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:yellow_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:lime_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:pink_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:gray_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:light_gray_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:cyan_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:purple_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:blue_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:brown_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:green_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:red_carpet");
            carbonDioxideGenValuesF.createSection("minecraft:black_carpet");
            carbonDioxideGenValuesF.set("minecraft:white_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:orange_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:magenta_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:light_blue_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:yellow_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:lime_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:pink_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:gray_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:light_gray_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:cyan_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:purple_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:blue_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:brown_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:green_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:red_carpet", null);
            carbonDioxideGenValuesF.set("minecraft:black_carpet", null);

            carbonDioxideGenValuesF.createSection("minecraft:oak_button");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_button");
            carbonDioxideGenValuesF.createSection("minecraft:birch_button");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_button");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_button");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_button");
            carbonDioxideGenValuesF.set("minecraft:oak_button", null);
            carbonDioxideGenValuesF.set("minecraft:spruce_button", null);
            carbonDioxideGenValuesF.set("minecraft:birch_button", null);
            carbonDioxideGenValuesF.set("minecraft:jungle_button", null);
            carbonDioxideGenValuesF.set("minecraft:acacia_button", null);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_button", null);

            carbonDioxideGenValuesF.createSection("minecraft:bowl");
            carbonDioxideGenValuesF.set("minecraft:bowl", null);

            carbonDioxideGenValuesF.createSection("minecraft:oak_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:birch_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_sapling");
            carbonDioxideGenValuesF.set("minecraft:oak_sapling", null);
            carbonDioxideGenValuesF.set("minecraft:spruce_sapling", null);
            carbonDioxideGenValuesF.set("minecraft:birch_sapling", null);
            carbonDioxideGenValuesF.set("minecraft:jungle_sapling", null);
            carbonDioxideGenValuesF.set("minecraft:acacia_sapling", null);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_sapling", null);

            carbonDioxideGenValuesF.createSection("minecraft:stick");
            carbonDioxideGenValuesF.set("minecraft:stick", null);

            carbonDioxideGenValuesF.createSection("minecraft:azalea");
            carbonDioxideGenValuesF.createSection("minecraft:flowering_azalea");
            carbonDioxideGenValuesF.set("minecraft:azalea", null);
            carbonDioxideGenValuesF.set("minecraft:flowering_azalea", null);

            carbonDioxideGenValuesF.createSection("minecraft:white_wool");
            carbonDioxideGenValuesF.createSection("minecraft:orange_wool");
            carbonDioxideGenValuesF.createSection("minecraft:magenta_wool");
            carbonDioxideGenValuesF.createSection("minecraft:light_blue_wool");
            carbonDioxideGenValuesF.createSection("minecraft:yellow_wool");
            carbonDioxideGenValuesF.createSection("minecraft:lime_wool");
            carbonDioxideGenValuesF.createSection("minecraft:pink_wool");
            carbonDioxideGenValuesF.createSection("minecraft:gray_wool");
            carbonDioxideGenValuesF.createSection("minecraft:light_gray_wool");
            carbonDioxideGenValuesF.createSection("minecraft:cyan_wool");
            carbonDioxideGenValuesF.createSection("minecraft:purple_wool");
            carbonDioxideGenValuesF.createSection("minecraft:blue_wool");
            carbonDioxideGenValuesF.createSection("minecraft:brown_wool");
            carbonDioxideGenValuesF.createSection("minecraft:green_wool");
            carbonDioxideGenValuesF.createSection("minecraft:red_wool");
            carbonDioxideGenValuesF.createSection("minecraft:black_wool");
            carbonDioxideGenValuesF.set("minecraft:white_wool", null);
            carbonDioxideGenValuesF.set("minecraft:orange_wool", null);
            carbonDioxideGenValuesF.set("minecraft:magenta_wool", null);
            carbonDioxideGenValuesF.set("minecraft:light_blue_wool", null);
            carbonDioxideGenValuesF.set("minecraft:yellow_wool", null);
            carbonDioxideGenValuesF.set("minecraft:lime_wool", null);
            carbonDioxideGenValuesF.set("minecraft:pink_wool", null);
            carbonDioxideGenValuesF.set("minecraft:gray_wool", null);
            carbonDioxideGenValuesF.set("minecraft:light_gray_wool", null);
            carbonDioxideGenValuesF.set("minecraft:cyan_wool", null);
            carbonDioxideGenValuesF.set("minecraft:purple_wool", null);
            carbonDioxideGenValuesF.set("minecraft:blue_wool", null);
            carbonDioxideGenValuesF.set("minecraft:brown_wool", null);
            carbonDioxideGenValuesF.set("minecraft:green_wool", null);
            carbonDioxideGenValuesF.set("minecraft:red_wool", null);
            carbonDioxideGenValuesF.set("minecraft:black_wool", null);

            carbonDioxideGenValuesF.createSection("minecraft:oak_slab");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_slab");
            carbonDioxideGenValuesF.createSection("minecraft:birch_slab");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_slab");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_slab");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_slab");
            carbonDioxideGenValuesF.set("minecraft:oak_slab", null);
            carbonDioxideGenValuesF.set("minecraft:spruce_slab", null);
            carbonDioxideGenValuesF.set("minecraft:birch_slab", null);
            carbonDioxideGenValuesF.set("minecraft:jungle_slab", null);
            carbonDioxideGenValuesF.set("minecraft:acacia_slab", null);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_slab", null);

            carbonDioxideGenValuesF.createSection("minecraft:crossbow");
            carbonDioxideGenValuesF.set("minecraft:crossbow", null);

            carbonDioxideGenValuesF.createSection("minecraft:bow");
            carbonDioxideGenValuesF.set("minecraft:bow", null);

            carbonDioxideGenValuesF.createSection("minecraft:oak_door");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_door");
            carbonDioxideGenValuesF.createSection("minecraft:birch_door");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_door");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_door");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_door");
            carbonDioxideGenValuesF.set("minecraft:oak_door", null);
            carbonDioxideGenValuesF.set("minecraft:spruce_door", null);
            carbonDioxideGenValuesF.set("minecraft:birch_door", null);
            carbonDioxideGenValuesF.set("minecraft:jungle_door", null);
            carbonDioxideGenValuesF.set("minecraft:acacia_door", null);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_door", null);
        }

        carbonDioxideConcentration = 0;
        methaneConcentration = 0;
        nitrousOxideConcentration = 0;
        waterVaporConcentration = 0;
    }

    @EventHandler
    public void onPlayerBurnItemInFurnace(FurnaceBurnEvent event) {
        ItemStack itemBurned = event.getFuel();
        String itemName = itemBurned.getType().getKey().toString();

        carbonDioxideConcentration += carbonDioxideGenValues.get(itemName);
        System.out.println(carbonDioxideConcentration);
    }
}
