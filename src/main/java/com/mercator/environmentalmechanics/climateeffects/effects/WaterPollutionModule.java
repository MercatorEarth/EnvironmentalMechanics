package com.mercator.environmentalmechanics.climateeffects.effects;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class WaterPollutionModule implements Runnable {

    private int taskID;

    private JavaPlugin javaPlugin;
    private World world;

    private List<EntityType> entityTypes;

    private MetadataValue polluted;
    private MetadataValue softPolluted;

    private int delay;

    public WaterPollutionModule(JavaPlugin plugin, int initialDelay, int repeatDelay) {
        this.taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, initialDelay, repeatDelay);

        javaPlugin = plugin;
        world = Bukkit.getWorld("world");

        entityTypes = new ArrayList<>();
        entityTypes.add(EntityType.CHICKEN);
        entityTypes.add(EntityType.COW);
        entityTypes.add(EntityType.SHEEP);
        entityTypes.add(EntityType.MUSHROOM_COW);
        entityTypes.add(EntityType.RABBIT);
        entityTypes.add(EntityType.PIG);

        polluted = new FixedMetadataValue(javaPlugin, true);
        softPolluted = new FixedMetadataValue(javaPlugin, true);

        delay = repeatDelay;
    }

    public void cancelLoop() {
        Bukkit.getScheduler().cancelTask(this.taskID);
    }

    public void setChunkPolluted(Chunk chunk, int mobLevel) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = mobLevel - 10; y < mobLevel + 5; y++) {
                    Block reference = chunk.getBlock(x, y, z);

                    if (reference.getType().equals(Material.WATER)) {
                        reference.setMetadata("polluted", polluted);
                    }
                }
            }
        }
    }

    public void setChunkSoftPolluted(Chunk chunk, int mobLevel) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = mobLevel - 10; y < mobLevel + 5; y++) {
                    Block reference = chunk.getBlock(x, y, z);

                    if (reference.getType().equals(Material.WATER)) {
                        reference.setMetadata("softpolluted", softPolluted);
                    }
                }
            }
        }
    }

    public void setChunkUnpolluted(Chunk chunk, int mobLevel) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = mobLevel - 10; y < mobLevel + 5; y++) {
                    Block reference = chunk.getBlock(x, y, z);

                    if (reference.getType().equals(Material.WATER)) {
                        if (reference.hasMetadata("polluted")) {
                            reference.removeMetadata("polluted", javaPlugin);
                        }
                        else if (reference.hasMetadata("softpolluted")) {
                            reference.removeMetadata("softpolluted", javaPlugin);
                        }
                    }
                }
            }
        }
    }

    public List<LivingEntity> getLivingEntities(Chunk chunk) {
        Entity[] entityList = chunk.getEntities();
        List<LivingEntity> livingEntityList = new ArrayList<>();

        for (Entity entity : entityList) {
            if (entity instanceof LivingEntity && entityTypes.contains(entity.getType())) {
                livingEntityList.add((LivingEntity) entity);
            }
        }

        return livingEntityList;
    }

    public int getAverageEntityLevel(List<LivingEntity> livingEntityList) {
        double average = 0.0;
        for (LivingEntity entity : livingEntityList) {
            average += (entity.getLocation().getBlockY());
        }

        int averageInt = (int) Math.round(average / livingEntityList.size());

        return averageInt;
    }

    public void entityAmountCheck(Chunk chunk) {
        int maxSafeEntitiesPerChunk = 96;

        List<LivingEntity> livingEntityList = getLivingEntities(chunk);
        int averageInt = getAverageEntityLevel(livingEntityList);

        if (livingEntityList.size() > maxSafeEntitiesPerChunk) {
            setChunkPolluted(chunk, averageInt);
        }
        else {
            setChunkUnpolluted(chunk, averageInt);
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    Chunk referenceChunk = world.getChunkAt(chunk.getX() + x, chunk.getZ() + z);

                    List<LivingEntity> livingEntityListAdj = getLivingEntities(referenceChunk);
                    int averageIntAdj = getAverageEntityLevel(livingEntityListAdj);

                    Block reference = referenceChunk.getBlock(0, averageIntAdj, 0);

                    if (reference.hasMetadata("polluted")) {
                        setChunkSoftPolluted(chunk, averageIntAdj);
                        break;
                    }
                }
            }
        }
    }

    public void waterPollutionCheck(LivingEntity entity) {
        Location location = entity.getLocation();
        Chunk referenceChunk = location.getChunk();

        entityAmountCheck(referenceChunk);

        Block reference = location.getBlock();
        Material referenceMaterial = reference.getType();

        if (referenceMaterial.equals(Material.WATER) && (reference.hasMetadata("polluted") || reference.hasMetadata("softpolluted"))) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, delay, 0, true, false));
        }
    }

    public void run() {
        for (LivingEntity entity : world.getLivingEntities()) {
            waterPollutionCheck(entity);
        }
    }
}
