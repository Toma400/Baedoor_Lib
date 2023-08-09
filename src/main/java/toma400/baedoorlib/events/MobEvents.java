package toma400.baedoorlib.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import toma400.baedoorlib.BaedoorLib;
import toma400.baedoorlib.core.LibItems;

@Mod.EventBusSubscriber(modid = BaedoorLib.MOD_ID)
public class MobEvents {

    protected static final int NETHER_SOUL_ESSENCE_CHANCE = 8;

    @SubscribeEvent
    public static void netherSoulEssenceDrop(LivingDeathEvent event) {
        Entity killedEntity = event.getEntity();
        if (!killedEntity.level().isClientSide()) {
            if (killedEntity.level().dimension() == Level.NETHER) {
                if (Math.random() * 100 <= NETHER_SOUL_ESSENCE_CHANCE) {
                    killedEntity.spawnAtLocation(LibItems.NETHER_SOUL_ESSENCE.get().getDefaultInstance(), 1F);
                }
            }
        }
    }

}
