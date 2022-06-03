package com.mercator.environmentalmechanics.climateeffects;

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

    private Map<String, String> chunkSeaLevels;

    private World world;
    private JavaPlugin javaPlugin;

    private MetadataValue flooded = new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("EnvironmentalMechanics"), true);
    private MetadataValue dry = new FixedMetadataValue(Bukkit.getServer().getPluginManager().getPlugin("EnvironmentalMechanics"), false);

    private static final int baseSeaLevel = 41;

    public static boolean rising;

    public RaisedSeaLevel(JavaPlugin plugin, int initialDelay, int repeatDelay) {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, initialDelay, repeatDelay);

        replaceables = new ArrayList<>();
        javaPlugin = plugin;

        rising = false;

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

        File chunkSeaLevelsF = new File("plugins/EnvironmentalMechanics/globalwarming/chunksealevels.json");

        if (!chunkSeaLevelsF.exists()) {
            try {
                chunkSeaLevelsF.createNewFile();
                PluginDataInterpreter.write(chunkSeaLevelsF, "{}", "globalwarming");
            }
            catch (IOException e) {
                getServer().getPluginManager().getPlugin("EnvironmentalMechanics").getLogger().warning("Failed to create chunk sea levels!");
            }
        }

        chunkSeaLevels = (Map<String, String>) PluginDataInterpreter.genMapFromExternalJson("plugins/EnvironmentalMechanics/globalwarming/chunksealevels.json");

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

    public int getSeaLevel() {
        return currentSeaLevel;
    }

    public int getSeaLevelInChunk(Chunk chunk) {
        List<Integer> chunkCoordinates = new ArrayList<>();

        chunkCoordinates.add(chunk.getX());
        chunkCoordinates.add(chunk.getZ());

        int chunkSeaLevelInt = baseSeaLevel;

        if (chunkSeaLevels.containsKey(chunk.getX() + "_" + chunk.getZ())) {
            chunkSeaLevelInt = Integer.parseInt(chunkSeaLevels.get(chunk.getX() + "_" + chunk.getZ()));
        }

        return chunkSeaLevelInt;
    }

    public void writeSeaLevelInChunk(int amount, Chunk chunk) {
        List<Integer> chunkCoordinates = new ArrayList<>();

        chunkCoordinates.add(chunk.getX());
        chunkCoordinates.add(chunk.getZ());

        try {
            chunkSeaLevels.put(chunk.getX() + "_" + chunk.getZ(), String.valueOf(amount));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adjustSeaLevelInChunk(Chunk chunk) {
        int chunkCurrentSeaLevel = getSeaLevelInChunk(chunk);

        if (chunkCurrentSeaLevel < currentSeaLevel) {
            chunk.setForceLoaded(true);
            for (int level = chunkCurrentSeaLevel; level < currentSeaLevel + 1; level++) {
                Map<List<Integer>, String> blocks = new HashMap<>();
                Map<String, String> blocksExisting = new HashMap<>();

                if (new File("plugins/EnvironmentalMechanics/globalwarming/seablocks/seablocks" + level + "_" + chunk.getX() + "_" + chunk.getZ() + ".json").exists()) {
                    blocksExisting = (Map<String, String>) PluginDataInterpreter.genMapFromExternalJson("plugins/EnvironmentalMechanics/globalwarming/seablocks/seablocks" + level + "_" + chunk.getX() + "_" + chunk.getZ() + ".json");

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

                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        Block reference = chunk.getBlock(x, level, z);
                        if (replaceables.contains(reference.getType())) {
                            List<Integer> coordinates = new ArrayList<>();

                            coordinates.add(reference.getLocation().getBlockX());
                            coordinates.add(reference.getLocation().getBlockY());
                            coordinates.add(reference.getLocation().getBlockZ());

                            if (!(new File("plugins/EnvironmentalMechanics/globalwarming/seablocks/seablocks" + level + "_" + chunk.getX() + "_" + chunk.getZ() + ".json").exists() && blocksExisting.containsKey(coordinates.toString()))) {
                                blocks.put(coordinates, reference.getType().toString());
                            }

                            reference.setType(Material.WATER);
                            reference.setMetadata("flooded", flooded);
                        }
                    }
                }
                Gson gson = new Gson();
                String storedBlocks = gson.toJson(blocks);

                try {
                    File seaBlocksF = new File("plugins/EnvironmentalMechanics/globalwarming/seablocks/seablocks" + level + "_" + chunk.getX() + "_" + chunk.getZ() + ".json");
                    PluginDataInterpreter.write(seaBlocksF, storedBlocks, "globalwarming/seablocks");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writeSeaLevelInChunk(currentSeaLevel, chunk);
            chunk.setForceLoaded(false);
        }
        else if (chunkCurrentSeaLevel > currentSeaLevel) {
            chunk.setForceLoaded(true);
            Map<String, String> blocks;

            for (int level = currentSeaLevel; level < chunkCurrentSeaLevel + 1; level++) {
                try {
                    blocks = (Map<String, String>) PluginDataInterpreter.genMapFromExternalJson("plugins/EnvironmentalMechanics/globalwarming/seablocks/seablocks" + level + "_" + chunk.getX() + "_" + chunk.getZ() + ".json");

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

                        reference.setType(Material.valueOf(blocks.get(coordinates)));
                        reference.removeMetadata("flooded", javaPlugin);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writeSeaLevelInChunk(currentSeaLevel, chunk);
            chunk.setForceLoaded(false);
        }
    }

    public void setSeaLevel(int amount) {
        newSeaLevel = baseSeaLevel + amount;

        try {
            currentSeaLevel = newSeaLevel;
            File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
            PluginDataInterpreter.write(seaLevelF, currentSeaLevel, "globalwarming");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void raiseByTemperature(double temperature) {
        Chunk[] chunks = world.getLoadedChunks();

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

        setSeaLevel(finalAmount);
        rising = true;

        for (int i = 0; i < chunks.length; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(javaPlugin, () -> adjustSeaLevelInChunk(chunks[finalI]), (finalI + 1) * 1L);
        }

        rising = false;

        try {
            File seaLevelF = new File("plugins/EnvironmentalMechanics/globalwarming/sealevel.txt");
            PluginDataInterpreter.write(seaLevelF, currentSeaLevel, "globalwarming");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        File chunkSeaLevelsF = new File("plugins/EnvironmentalMechanics/globalwarming/chunksealevels.json");

        String chunkSeaLevelsR = gson.toJson(chunkSeaLevels);
        PluginDataInterpreter.write(chunkSeaLevelsF, chunkSeaLevelsR, "globalwarming");
    }

    public static boolean isRising() {
        return rising;
    }

    public void run() {
        ClimateEngine climateEngine = new ClimateEngine();
        raiseByTemperature(climateEngine.getTemperatureAt(new Location(world, 6825, 42, 12416)));
    }
}
