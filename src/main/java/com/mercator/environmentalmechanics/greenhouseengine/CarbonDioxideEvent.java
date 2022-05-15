package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarbonDioxideEvent implements Listener {

    public double carbonDioxideConcentration;
    private Map<String, Double> carbonDioxideGenValues;

    private File carbonDioxideGenValuesFR;
    private YamlConfiguration carbonDioxideGenValuesF;

    public CarbonDioxideEvent() {
        carbonDioxideGenValues = new HashMap<>();

        carbonDioxideGenValuesFR = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxidegenvalues.yml");
        carbonDioxideGenValuesF = YamlConfiguration.loadConfiguration(carbonDioxideGenValuesFR);

        if (!carbonDioxideGenValuesFR.exists()) {
            carbonDioxideGenValuesF.createSection("minecraft:bamboo");
            carbonDioxideGenValuesF.set("minecraft:bamboo", 0.25 * 1.5);

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
            carbonDioxideGenValuesF.set("minecraft:white_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:orange_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:magenta_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_blue_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:yellow_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:lime_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:pink_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:gray_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_gray_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:cyan_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:purple_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:blue_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:brown_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:green_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:red_carpet", 0.335 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:black_carpet", 0.335 * 2.4);

            carbonDioxideGenValuesF.createSection("minecraft:oak_button");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_button");
            carbonDioxideGenValuesF.createSection("minecraft:birch_button");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_button");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_button");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_button");
            carbonDioxideGenValuesF.set("minecraft:oak_button", 0.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_button", 0.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_button", 0.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_button", 0.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_button", 0.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_button", 0.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:bowl");
            carbonDioxideGenValuesF.set("minecraft:bowl", 0.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:birch_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_sapling");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_sapling");
            carbonDioxideGenValuesF.set("minecraft:oak_sapling", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:spruce_sapling", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:birch_sapling", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:jungle_sapling", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:acacia_sapling", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_sapling", 0.5 * 3.0);

            carbonDioxideGenValuesF.createSection("minecraft:stick");
            carbonDioxideGenValuesF.set("minecraft:stick", 0.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:azalea");
            carbonDioxideGenValuesF.createSection("minecraft:flowering_azalea");
            carbonDioxideGenValuesF.set("minecraft:azalea", 0.5 * 3.0);
            carbonDioxideGenValuesF.set("minecraft:flowering_azalea", 0.5 * 3.0);

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
            carbonDioxideGenValuesF.set("minecraft:white_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:orange_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:magenta_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_blue_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:yellow_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:lime_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:pink_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:gray_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_gray_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:cyan_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:purple_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:blue_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:brown_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:green_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:red_wool", 0.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:black_wool", 0.5 * 2.4);

            carbonDioxideGenValuesF.createSection("minecraft:oak_slab");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_slab");
            carbonDioxideGenValuesF.createSection("minecraft:birch_slab");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_slab");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_slab");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_slab");
            carbonDioxideGenValuesF.set("minecraft:oak_slab", 0.75 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_slab", 0.75 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_slab", 0.75 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_slab", 0.75 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_slab", 0.75 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_slab", 0.75 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:crossbow");
            carbonDioxideGenValuesF.set("minecraft:crossbow", 1.5 * 2.0);

            carbonDioxideGenValuesF.createSection("minecraft:bow");
            carbonDioxideGenValuesF.set("minecraft:bow", 1.5 * 2.0);

            carbonDioxideGenValuesF.createSection("minecraft:oak_door");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_door");
            carbonDioxideGenValuesF.createSection("minecraft:birch_door");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_door");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_door");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_door");
            carbonDioxideGenValuesF.set("minecraft:oak_door", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_door", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_door", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_door", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_door", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_door", 1.0 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_sign");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_sign");
            carbonDioxideGenValuesF.createSection("minecraft:birch_sign");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_sign");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_sign");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_sign");
            carbonDioxideGenValuesF.set("minecraft:oak_sign", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_sign", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_sign", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_sign", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_sign", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_sign", 1.0 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:wooden_pickaxe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_shovel");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_hoe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_axe");
            carbonDioxideGenValuesF.createSection("minecraft:wooden_sword");
            carbonDioxideGenValuesF.set("minecraft:wooden_pickaxe", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:wooden_shovel", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:wooden_hoe", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:wooden_axe", 1.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:wooden_sword", 1.0 * 1.8);

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
            carbonDioxideGenValuesF.set("minecraft:oak_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:spruce_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:birch_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:jungle_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:acacia_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_log", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:stripped_oak_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_spruce_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_birch_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_jungle_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_acacia_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_dark_oak_log", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:oak_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:spruce_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:birch_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:jungle_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:acacia_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_wood", 1.5 * 2.0);
            carbonDioxideGenValuesF.set("minecraft:stripped_oak_wood", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_spruce_wood", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_birch_wood", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_jungle_wood", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_acacia_wood", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:stripped_dark_oak_wood", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_planks");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_planks");
            carbonDioxideGenValuesF.createSection("minecraft:birch_planks");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_planks");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_planks");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_planks");
            carbonDioxideGenValuesF.set("minecraft:oak_planks", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_planks", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_planks", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_planks", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_planks", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_planks", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:birch_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_stairs");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_stairs");
            carbonDioxideGenValuesF.set("minecraft:oak_stairs", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_stairs", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_stairs", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_stairs", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_stairs", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_stairs", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:birch_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_pressure_plate");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_pressure_plate");
            carbonDioxideGenValuesF.set("minecraft:oak_pressure_plate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_pressure_plate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_pressure_plate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_pressure_plate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_pressure_plate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_pressure_plate", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:birch_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_trapdoor");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_trapdoor");
            carbonDioxideGenValuesF.set("minecraft:oak_trapdoor", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_trapdoor", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_trapdoor", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_trapdoor", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_trapdoor", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_trapdoor", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:birch_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_fence_gate");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_fence_gate");
            carbonDioxideGenValuesF.set("minecraft:oak_fence_gate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_fence_gate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_fence_gate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_fence_gate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_fence_gate", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_fence_gate", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:oak_fence");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_fence");
            carbonDioxideGenValuesF.createSection("minecraft:birch_fence");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_fence");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_fence");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_fence");
            carbonDioxideGenValuesF.set("minecraft:oak_fence", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_fence", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_fence", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_fence", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_fence", 1.5 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_fence", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:ladder");
            carbonDioxideGenValuesF.set("minecraft:ladder", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:crafting_table");
            carbonDioxideGenValuesF.set("minecraft:crafting_table", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:cartography_table");
            carbonDioxideGenValuesF.set("minecraft:cartography_table", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:fletching_table");
            carbonDioxideGenValuesF.set("minecraft:fletching_table", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:smithing_table");
            carbonDioxideGenValuesF.set("minecraft:smithing_table", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:loom");
            carbonDioxideGenValuesF.set("minecraft:loom", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:bookshelf");
            carbonDioxideGenValuesF.set("minecraft:bookshelf", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:lectern");
            carbonDioxideGenValuesF.set("minecraft:lectern", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:composter");
            carbonDioxideGenValuesF.set("minecraft:composter", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:chest");
            carbonDioxideGenValuesF.set("minecraft:chest", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:trapped_chest");
            carbonDioxideGenValuesF.set("minecraft:trapped_chest", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:barrel");
            carbonDioxideGenValuesF.set("minecraft:barrel", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:daylight_detector");
            carbonDioxideGenValuesF.set("minecraft:daylight_detector", 1.5 * 4.0);

            carbonDioxideGenValuesF.createSection("minecraft:jukebox");
            carbonDioxideGenValuesF.set("minecraft:jukebox", 1.5 * 4.0);

            carbonDioxideGenValuesF.createSection("minecraft:note_block");
            carbonDioxideGenValuesF.set("minecraft:note_block", 1.5 * 4.0);

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
            carbonDioxideGenValuesF.set("minecraft:white_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:orange_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:magenta_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_blue_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:yellow_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:lime_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:pink_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:gray_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:light_gray_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:cyan_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:purple_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:blue_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:brown_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:green_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:red_banner", 1.5 * 2.4);
            carbonDioxideGenValuesF.set("minecraft:black_banner", 1.5 * 2.4);

            carbonDioxideGenValuesF.createSection("minecraft:fishing_rod");
            carbonDioxideGenValuesF.set("minecraft:fishing_rod", 1.5 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:scaffolding");
            carbonDioxideGenValuesF.set("minecraft:scaffolding", 2.0 * 1.5);

            carbonDioxideGenValuesF.createSection("minecraft:oak_boat");
            carbonDioxideGenValuesF.createSection("minecraft:spruce_boat");
            carbonDioxideGenValuesF.createSection("minecraft:birch_boat");
            carbonDioxideGenValuesF.createSection("minecraft:jungle_boat");
            carbonDioxideGenValuesF.createSection("minecraft:acacia_boat");
            carbonDioxideGenValuesF.createSection("minecraft:dark_oak_boat");
            carbonDioxideGenValuesF.set("minecraft:oak_boat", 6.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:spruce_boat", 6.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:birch_boat", 6.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:jungle_boat", 6.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:acacia_boat", 6.0 * 1.8);
            carbonDioxideGenValuesF.set("minecraft:dark_oak_boat", 6.0 * 1.8);

            carbonDioxideGenValuesF.createSection("minecraft:charcoal");
            carbonDioxideGenValuesF.set("minecraft:charcoal", 8.0 * 1.2);

            carbonDioxideGenValuesF.createSection("minecraft:coal");
            carbonDioxideGenValuesF.set("minecraft:coal", 8.0 * 1.0);

            carbonDioxideGenValuesF.createSection("minecraft:blaze_rod");
            carbonDioxideGenValuesF.set("minecraft:blaze_rod", 12.0 * 0.4);

            carbonDioxideGenValuesF.createSection("minecraft:dried_kelp_block");
            carbonDioxideGenValuesF.set("minecraft:dried_kelp_block", 20.0 * 0.75);

            carbonDioxideGenValuesF.createSection("minecraft:coal_block");
            carbonDioxideGenValuesF.set("minecraft:coal_block", 80.0 * 0.9);

            carbonDioxideGenValuesF.createSection("minecraft:lava_bucket");
            carbonDioxideGenValuesF.set("minecraft:lava_bucket", 100.0 * 0.4);

            try {
                carbonDioxideGenValuesF.save(carbonDioxideGenValuesFR);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");

        if (!carbonDioxideValueF.exists()) {
            carbonDioxideConcentration = 0;

            PluginDataInterpreter.write(carbonDioxideValueF, carbonDioxideConcentration);
        }
        else {
            carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        }

        PluginDataInterpreter.genDoubleMapFromConfig(carbonDioxideGenValuesF, carbonDioxideGenValues);
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
