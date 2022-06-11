package com.mercator.environmentalmechanics.greenhouseengine;

import com.mercator.environmentalmechanics.greenhouseengine.gases.CarbonDioxideModule;
import com.mercator.environmentalmechanics.greenhouseengine.gases.MethaneModule;
import com.mercator.environmentalmechanics.greenhouseengine.gases.NitrousOxideModule;
import com.mercator.environmentalmechanics.greenhouseengine.gases.WaterVaporModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class GreenhouseEngine implements Listener {

    public static CarbonDioxideModule carbonDioxideModule;
    public static MethaneModule methaneModule;
    public static NitrousOxideModule nitrousOxideModule;
    public static WaterVaporModule waterVaporModule;

    public GreenhouseEngine() {
        carbonDioxideModule = new CarbonDioxideModule();
        methaneModule = new MethaneModule();
        nitrousOxideModule = new NitrousOxideModule();
        waterVaporModule = new WaterVaporModule();
    }

    @EventHandler
    public void furnaceBurnEvent(FurnaceBurnEvent event) {
        carbonDioxideModule.onPlayerBurnItemInFurnace(event);
    }

    @EventHandler
    public void structureGrowEvent(StructureGrowEvent event) {
        carbonDioxideModule.treeGrowth(event);
    }

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent event) {
        methaneModule.onDeathOfEntity(event);
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        methaneModule.onPlayerMineBlock(event);
    }

    @EventHandler
    public void blockFertilizeEvent(BlockFertilizeEvent event) {
        nitrousOxideModule.onPlayerFertilizeBlock(event);
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        nitrousOxideModule.onPlayerFinishComposting(event);
    }
}
