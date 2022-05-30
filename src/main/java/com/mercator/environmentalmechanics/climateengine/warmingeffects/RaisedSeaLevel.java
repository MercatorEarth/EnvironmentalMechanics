package com.mercator.environmentalmechanics.climateengine.warmingeffects;

import com.google.gson.Gson;
import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import com.mercator.environmentalmechanics.datamanagement.LinearEquation;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class RaisedSeaLevel implements Runnable {

    private int taskID;

    private List<Material> replaceables;

    private int newSeaLevel;
    private int currentSeaLevel;

    private World world;
    private JavaPlugin javaPlugin;

    private MetadataValue flooded = new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("EnvironmentalMechanics"), true);
    private MetadataValue dry = new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("EnvironmentalMechanics"), false);

    private static final int baseSeaLevel = 63;

    public RaisedSeaLevel(JavaPlugin plugin, int initialDelay, int repeatDelay) {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, initialDelay, repeatDelay);

        replaceables = new ArrayList<>();
        javaPlugin = plugin;

        File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
        world = Bukkit.getWorld("world");

        if (!seaLevelF.exists()) {
            currentSeaLevel = baseSeaLevel;

            try {
                seaLevelF.createNewFile();
            }
            catch (IOException e) {
                getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().warning("Failed to create data file for Sea Level!");
            }

            PluginDataInterpreter.write(seaLevelF, currentSeaLevel, "globalwarming");
        }
        else {
            currentSeaLevel = Integer.parseInt(PluginDataInterpreter.read(seaLevelF));
        }

        replaceables.add(Material.AIR);
        replaceables.add(Material.TALL_GRASS);
        replaceables.add(Material.GRASS);
        replaceables.add(Material.LILY_PAD);
        replaceables.add(Material.LILAC);
        replaceables.add(Material.SUGAR_CANE);
        replaceables.add(Material.FERN);
        replaceables.add(Material.ALLIUM);
        replaceables.add(Material.VINE);
        replaceables.add(Material.DEAD_BUSH);
        replaceables.add(Material.WATER);
        replaceables.add(Material.SEAGRASS);
        replaceables.add(Material.TALL_SEAGRASS);
        replaceables.add(Material.KELP_PLANT);
        replaceables.add(Material.KELP);

        for (Material material : Material.values()) {
            String mat = material.name().toLowerCase();
            if (mat.contains("flower") || mat.contains("sapling") || mat.contains("seed")) {
                replaceables.add(material);
            }
        }
    }

    public void cancelLoop() {
        Bukkit.getScheduler().cancelTask(this.taskID);
    }

    public void raisedSeaLevel(int amount) {
        newSeaLevel = baseSeaLevel + amount;

        try {
            File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
            PluginDataInterpreter.write(seaLevelF, newSeaLevel, "globalwarming");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (newSeaLevel >= currentSeaLevel) {
            Chunk[] chunks = world.getLoadedChunks();

            for (int level = currentSeaLevel; level < newSeaLevel + 1; level++) {
                Map<List<Integer>, String> blocks = new HashMap<>();
                Map<String, String> blocksExisting = new HashMap<>();

                if (new File("plugins/EnvironmentalMechanics/globalwarming/seablocks"+level+".json").exists()) {
                    blocksExisting = (Map<String, String>) PluginDataInterpreter.genMapFromExternalJson("plugins/EnvironmentalMechanics/globalwarming/seablocks"+level+".json");

                    for (String coordinates : blocksExisting.keySet()) {
                        String coordinatesRaw1 = coordinates.replace("[", "");
                        String coordinatesRaw = coordinatesRaw1.replace("]", "");
                        List<String> separated = new ArrayList<>(Arrays.asList(coordinatesRaw.split(", ")));
                        List<Integer> convertedCoordinates = new ArrayList<>();

                        for (String string : separated) {
                            convertedCoordinates.add(Integer.parseInt(string));
                        }

                        blocks.put(convertedCoordinates, blocksExisting.get(coordinates));
                    }
                }

                for (Chunk chunk : chunks) {
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            Block reference = chunk.getBlock(x, level, z);
                            if (replaceables.contains(reference.getType())) {
                                List<Integer> coordinates = new ArrayList<>();

                                coordinates.add(reference.getLocation().getBlockX());
                                coordinates.add(reference.getLocation().getBlockY());
                                coordinates.add(reference.getLocation().getBlockZ());

                                if (new File("plugins/EnvironmentalMechanics/globalwarming/seablocks"+level+".json").exists() && blocksExisting.containsKey(coordinates.toString())) {
                                    assert true;
                                }
                                else {
                                    blocks.put(coordinates, reference.getType().toString());
                                }

                                reference.setType(Material.WATER);
                                reference.setMetadata("flooded", flooded);
                            }
                        }
                    }
                }

                Gson gson = new Gson();
                String storedBlocks = gson.toJson(blocks);

                try {
                    File seaBlocksF = new File("plugins/EnvironmentalMechanics/globalwarming/seablocks" + level + ".json");
                    PluginDataInterpreter.write(seaBlocksF, storedBlocks, "globalwarming");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                currentSeaLevel = newSeaLevel;
                File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
                PluginDataInterpreter.write(seaLevelF, currentSeaLevel, "globalwarming");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (newSeaLevel < currentSeaLevel) {
            Chunk[] chunks = world.getLoadedChunks();
            String blocksJson;
            Map<String, String> blocks;

            Gson gson = new Gson();

            for (int level = newSeaLevel; level < currentSeaLevel + 1; level++) {
                try {
                    blocks = (Map<String, String>) PluginDataInterpreter.genMapFromExternalJson("plugins/EnvironmentalMechanics/globalwarming/seablocks"+level+".json");

                    for (String coordinates : blocks.keySet()) {
                        String coordinatesRaw1 = coordinates.replace("[", "");
                        String coordinatesRaw = coordinatesRaw1.replace("]", "");
                        List<String> separated = new ArrayList<>(Arrays.asList(coordinatesRaw.split(", ")));
                        List<Integer> convertedCoordinates = new ArrayList<>();

                        for (String string : separated) {
                            convertedCoordinates.add(Integer.parseInt(string));
                        }

                        Location location = new Location(world, convertedCoordinates.get(0), convertedCoordinates.get(1), convertedCoordinates.get(2));
                        Block reference = world.getBlockAt(location);

                        if (reference.getType().equals(Material.WATER) && reference.hasMetadata("flooded")) {
                            reference.setType(Material.valueOf(blocks.get(coordinates)));
                            reference.removeMetadata("flooded", javaPlugin);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                currentSeaLevel = newSeaLevel;
                File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
                PluginDataInterpreter.write(seaLevelF, currentSeaLevel, "globalwarming");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void temperatureBased(double temperature) {
        double minimumAmount = 0.0;
        double maximumAmount = 10.0;

        double minimumTemperature = 3.0;
        double maximumTemperature = 15.0;

        LinearEquation temperatureEquation = new LinearEquation();
        temperatureEquation.generate(minimumTemperature, minimumAmount, maximumTemperature, maximumAmount);

        int amount = (int) Math.round((temperatureEquation.slope * temperature) + temperatureEquation.yIntercept);

        if (amount < 0) {
            amount = 0;
        }
        else if (amount > 10) {
            amount = 10;
        }

        int finalAmount = amount;
        Bukkit.getScheduler().runTask(javaPlugin, () -> raisedSeaLevel(finalAmount));
    }

    public void run() {
        ClimateEngine climateEngine = new ClimateEngine();
        temperatureBased(climateEngine.getTemperatureAt(new Location(world, 6825, 42, 12416)));
    }
}
