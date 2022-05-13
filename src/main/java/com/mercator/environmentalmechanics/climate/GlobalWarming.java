package com.mercator.environmentalmechanics.climate;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
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
            carbonDioxideGenValuesF.set("minecraft:bamboo", 10);

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
            carbonDioxideGenValuesF.set("minecraft:white_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:orange_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:magenta_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:light_blue_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:yellow_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:lime_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:pink_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:gray_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:light_gray_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:cyan_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:purple_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:blue_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:brown_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:green_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:red_carpet", 10);
            carbonDioxideGenValuesF.set("minecraft:black_carpet", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_button");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_button");
            carbonDioxideGenValuesF.createSection("minecraft:birch_button");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_button");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_button");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_button");
            carbonDioxideGenValuesF.set("minecraft:oak_button", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_button", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_button", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_button", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_button", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_button", 10);

            carbonDioxideGenValuesF.createSection("minecraft:bowl");
            carbonDioxideGenValuesF.set("minecraft:bowl", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:birch_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_sapling");
            carbonDioxideGenValuesF.set("minecraft:oak_sapling", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_sapling", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_sapling", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_sapling", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_sapling", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_sapling", 10);

            carbonDioxideGenValuesF.createSection("minecraft:stick");
            carbonDioxideGenValuesF.set("minecraft:stick", 10);

            carbonDioxideGenValuesF.createSection("minecraft:azalea");
            carbonDioxideGenValuesF.createSection("minecraft:flowering_azalea");
            carbonDioxideGenValuesF.set("minecraft:azalea", 10);
            carbonDioxideGenValuesF.set("minecraft:flowering_azalea", 10);

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
            carbonDioxideGenValuesF.set("minecraft:white_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:orange_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:magenta_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:light_blue_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:yellow_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:lime_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:pink_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:gray_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:light_gray_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:cyan_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:purple_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:blue_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:brown_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:green_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:red_wool", 10);
            carbonDioxideGenValuesF.set("minecraft:black_wool", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_slab");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_slab");
            carbonDioxideGenValuesF.createSection("minecraft:birch_slab");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_slab");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_slab");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_slab");
            carbonDioxideGenValuesF.set("minecraft:oak_slab", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_slab", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_slab", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_slab", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_slab", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_slab", 10);

            carbonDioxideGenValuesF.createSection("minecraft:crossbow");
            carbonDioxideGenValuesF.set("minecraft:crossbow", 10);

            carbonDioxideGenValuesF.createSection("minecraft:bow");
            carbonDioxideGenValuesF.set("minecraft:bow", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_door");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_door");
            carbonDioxideGenValuesF.createSection("minecraft:birch_door");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_door");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_door");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_door");
            carbonDioxideGenValuesF.set("minecraft:oak_door", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_door", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_door", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_door", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_door", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_door", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_sign");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_sign");
            carbonDioxideGenValuesF.createSection("minecraft:birch_sign");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_sign");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_sign");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_sign");
            carbonDioxideGenValuesF.set("minecraft:oak_sign", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_sign", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_sign", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_sign", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_sign", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_sign", 10);

            carbonDioxideGenValuesF.createSection("minecraft:wooden_pickaxe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_shovel");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_hoe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_axe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_sword");
            carbonDioxideGenValuesF.set("minecraft:wooden_pickaxe", 10);
            carbonDioxideGenValuesF.set("minecraft:wooden_shovel", 10);
            carbonDioxideGenValuesF.set("minecraft:wooden_hoe", 10);
            carbonDioxideGenValuesF.set("minecraft:wooden_axe", 10);
            carbonDioxideGenValuesF.set("minecraft:wooden_sword", 10);

            carbonDioxideGenValuesF.createSection("minecraft:bee_nest");
            carbonDioxideGenValuesF.createSection("minecraft:beehive");
            carbonDioxideGenValuesF.set("minecraft:bee_nest", 10);
            carbonDioxideGenValuesF.set("minecraft:beehive", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_log");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_log");
            carbonDioxideGenValuesF.createSection("minecraft:birch_log");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_log");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_log");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_oak_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_spruce_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_birch_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_jungle_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_acacia_log");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_dark_oak_log");
            carbonDioxideGenValuesF.createSection("minecraft:oak_wood");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_wood");
            carbonDioxideGenValuesF.createSection("minecraft:birch_wood");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_wood");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_wood");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_oak_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_spruce_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_birch_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_jungle_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_acacia_wood");
            carbonDioxideGenValuesF.createSection("minecraft:stripped_dark_oak_wood");
            carbonDioxideGenValuesF.set("minecraft:oak_log", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_log", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_log", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_log", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_log", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_oak_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_spruce_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_birch_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_jungle_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_acacia_log", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_dark_oak_log", 10);
            carbonDioxideGenValuesF.set("minecraft:oak_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_oak_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_spruce_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_birch_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_jungle_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_acacia_wood", 10);
            carbonDioxideGenValuesF.set("minecraft:stripped_dark_oak_wood", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_planks");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_planks");
            carbonDioxideGenValuesF.createSection("minecraft:birch_planks");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_planks");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_planks");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_planks");
            carbonDioxideGenValuesF.set("minecraft:oak_planks", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_planks", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_planks", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_planks", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_planks", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_planks", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:birch_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_stairs");
            carbonDioxideGenValuesF.set("minecraft:oak_stairs", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_stairs", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_stairs", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_stairs", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_stairs", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_stairs", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:birch_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_pressure_plate");
            carbonDioxideGenValuesF.set("minecraft:oak_pressure_plate", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_pressure_plate", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_pressure_plate", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_pressure_plate", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_pressure_plate", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_pressure_plate", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:birch_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_trapdoor");
            carbonDioxideGenValuesF.set("minecraft:oak_trapdoor", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_trapdoor", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_trapdoor", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_trapdoor", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_trapdoor", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_trapdoor", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:birch_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_fence_gate");
            carbonDioxideGenValuesF.set("minecraft:oak_fence_gate", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_fence_gate", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_fence_gate", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_fence_gate", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_fence_gate", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_fence_gate", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_fence");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_fence");
            carbonDioxideGenValuesF.createSection("minecraft:birch_fence");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_fence");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_fence");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_fence");
            carbonDioxideGenValuesF.set("minecraft:oak_fence", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_fence", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_fence", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_fence", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_fence", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_fence", 10);

            carbonDioxideGenValuesF.createSection("minecraft:ladder");
            carbonDioxideGenValuesF.set("minecraft:ladder", 10);

            carbonDioxideGenValuesF.createSection("minecraft:crafting_table");
            carbonDioxideGenValuesF.set("minecraft:crafting_table", 10);

            carbonDioxideGenValuesF.createSection("minecraft:cartography_table");
            carbonDioxideGenValuesF.set("minecraft:cartography_table", 10);

            carbonDioxideGenValuesF.createSection("minecraft:fletching_table");
            carbonDioxideGenValuesF.set("minecraft:fletching_table", 10);

            carbonDioxideGenValuesF.createSection("minecraft:smithing_table");
            carbonDioxideGenValuesF.set("minecraft:smithing_table", 10);

            carbonDioxideGenValuesF.createSection("minecraft:loom");
            carbonDioxideGenValuesF.set("minecraft:loom", 10);

            carbonDioxideGenValuesF.createSection("minecraft:bookshelf");
            carbonDioxideGenValuesF.set("minecraft:bookshelf", 10);

            carbonDioxideGenValuesF.createSection("minecraft:lectern");
            carbonDioxideGenValuesF.set("minecraft:lectern", 10);

            carbonDioxideGenValuesF.createSection("minecraft:composter");
            carbonDioxideGenValuesF.set("minecraft:composter", 10);

            carbonDioxideGenValuesF.createSection("minecraft:chest");
            carbonDioxideGenValuesF.set("minecraft:chest", 10);

            carbonDioxideGenValuesF.createSection("minecraft:trapped_chest");
            carbonDioxideGenValuesF.set("minecraft:trapped_chest", 10);

            carbonDioxideGenValuesF.createSection("minecraft:barrel");
            carbonDioxideGenValuesF.set("minecraft:barrel", 10);

            carbonDioxideGenValuesF.createSection("minecraft:daylight_detector");
            carbonDioxideGenValuesF.set("minecraft:daylight_detector", 10);

            carbonDioxideGenValuesF.createSection("minecraft:jukebox");
            carbonDioxideGenValuesF.set("minecraft:jukebox", 10);

            carbonDioxideGenValuesF.createSection("minecraft:note_block");
            carbonDioxideGenValuesF.set("minecraft:note_block", 10);

            carbonDioxideGenValuesF.createSection("minecraft:white_banner");
            carbonDioxideGenValuesF.createSection("minecraft:orange_banner");
            carbonDioxideGenValuesF.createSection("minecraft:magenta_banner");
            carbonDioxideGenValuesF.createSection("minecraft:light_blue_banner");
            carbonDioxideGenValuesF.createSection("minecraft:yellow_banner");
            carbonDioxideGenValuesF.createSection("minecraft:lime_banner");
            carbonDioxideGenValuesF.createSection("minecraft:pink_banner");
            carbonDioxideGenValuesF.createSection("minecraft:gray_banner");
            carbonDioxideGenValuesF.createSection("minecraft:light_gray_banner");
            carbonDioxideGenValuesF.createSection("minecraft:cyan_banner");
            carbonDioxideGenValuesF.createSection("minecraft:purple_banner");
            carbonDioxideGenValuesF.createSection("minecraft:blue_banner");
            carbonDioxideGenValuesF.createSection("minecraft:brown_banner");
            carbonDioxideGenValuesF.createSection("minecraft:green_banner");
            carbonDioxideGenValuesF.createSection("minecraft:red_banner");
            carbonDioxideGenValuesF.createSection("minecraft:black_banner");
            carbonDioxideGenValuesF.set("minecraft:white_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:orange_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:magenta_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:light_blue_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:yellow_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:lime_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:pink_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:gray_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:light_gray_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:cyan_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:purple_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:blue_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:brown_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:green_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:red_banner", 10);
            carbonDioxideGenValuesF.set("minecraft:black_banner", 10);

            carbonDioxideGenValuesF.createSection("minecraft:fishing_rod");
            carbonDioxideGenValuesF.set("minecraft:fishing_rod", 10);

            carbonDioxideGenValuesF.createSection("minecraft:scaffolding");
            carbonDioxideGenValuesF.set("minecraft:scaffolding", 10);

            carbonDioxideGenValuesF.createSection("minecraft:oak_boat");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_boat");
            carbonDioxideGenValuesF.createSection("minecraft:birch_boat");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_boat");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_boat");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_boat");
            carbonDioxideGenValuesF.set("minecraft:oak_boat", 10);
            carbonDioxideGenValuesF.set("minecraft:spruce_boat", 10);
            carbonDioxideGenValuesF.set("minecraft:birch_boat", 10);
            carbonDioxideGenValuesF.set("minecraft:jungle_boat", 10);
            carbonDioxideGenValuesF.set("minecraft:acacia_boat", 10);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_boat", 10);

            carbonDioxideGenValuesF.createSection("minecraft:charcoal");
            carbonDioxideGenValuesF.set("minecraft:charcoal", 10);

            carbonDioxideGenValuesF.createSection("minecraft:coal");
            carbonDioxideGenValuesF.set("minecraft:coal", 10);

            carbonDioxideGenValuesF.createSection("minecraft:blaze_rod");
            carbonDioxideGenValuesF.set("minecraft:blaze_rod", 10);

            carbonDioxideGenValuesF.createSection("minecraft:dried_kelp_block");
            carbonDioxideGenValuesF.set("minecraft:dried_kelp_block", 10);

            carbonDioxideGenValuesF.createSection("minecraft:coal_block");
            carbonDioxideGenValuesF.set("minecraft:coal_block", 10);

            carbonDioxideGenValuesF.createSection("minecraft:lava_bucket");
            carbonDioxideGenValuesF.set("minecraft:lava_bucket", 10);

            try {
                carbonDioxideGenValuesF.save(carbonDioxideGenValuesFR);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        carbonDioxideConcentration = 0;
        methaneConcentration = 0;
        nitrousOxideConcentration = 0;
        waterVaporConcentration = 0;

        for (String key : carbonDioxideGenValuesF.getKeys(false)) {
            carbonDioxideGenValues.put(key, (Integer) carbonDioxideGenValuesF.get(key));
        }
    }

    @EventHandler
    public void onPlayerBurnItemInFurnace(FurnaceBurnEvent event) {
        ItemStack itemBurned = event.getFuel();
        String itemName = itemBurned.getType().getKey().toString();

        carbonDioxideConcentration += carbonDioxideGenValues.get(itemName);
        System.out.println(carbonDioxideConcentration);
    }
}
