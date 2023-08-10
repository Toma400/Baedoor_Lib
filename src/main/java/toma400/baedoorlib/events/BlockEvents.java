package toma400.baedoorlib.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import toma400.baedoorlib.BaedoorLib;
import toma400.baedoorlib.core.LibBlocks;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = BaedoorLib.MOD_ID)
public class BlockEvents {

    protected static final BlockState[] SOUL_SOIL_CONVERTABLE = {
            Blocks.LAVA.defaultBlockState()
    };

    @SubscribeEvent
    public static void soulSoilGeneration(BlockEvent.NeighborNotifyEvent event) {

        if (!event.getLevel().isClientSide()) {
            BlockPos center = event.getPos();

            if (event.getLevel().getBlockState(center) == LibBlocks.BLOCK_OF_NETHER_SOUL_ESSENCE.get().defaultBlockState()) {

                // change it so it accepts also flowing lava (for now, lava source can be good limitation)
                if (Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.east())) ||
                    Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.west())) ||
                    Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.south())) ||
                    Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.north())) ||
                    Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.above())) ||
                    Arrays.asList(SOUL_SOIL_CONVERTABLE).contains(event.getLevel().getBlockState(center.below()))) {
                    event.getLevel().setBlock(center, Blocks.SOUL_SOIL.defaultBlockState(), 1);
                }
            }
        }
    }
}
