package toma400.baedoorlib.events;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.nbt.EndTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import toma400.baedoorlib.BaedoorLib;
import toma400.baedoorlib.core.LibBlocks;
import toma400.baedoorlib.core.LibItems;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = BaedoorLib.MOD_ID)
public class BlockEvents {

    protected static final BlockState[] SOUL_SOIL_CONVERTABLE = {
            Blocks.LAVA.defaultBlockState()
    };
    protected static final Block[] GLISTERING_ASH_DROPS = {
            Blocks.END_STONE
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

    @SubscribeEvent
    public static void glisteringAshGathering(BlockEvent.BreakEvent event) {

        if (!event.getLevel().isClientSide()) {

            // change to less awkward procs, it shouldn't add things to player, but rather spawn them
            if (event.getLevel().getBiome(event.getPos()).is(Biomes.THE_END)){
                if (Arrays.asList(GLISTERING_ASH_DROPS).contains(event.getLevel().getBlockState(event.getPos()).getBlock())) {
                    event.getPlayer().addItem(new ItemStack(LibItems.GLISTERING_ASH.get()));
                    // experimental: XP drop?
                    event.getPlayer().giveExperiencePoints(1);
                    event.getPlayer().playSound(SoundEvents.EXPERIENCE_BOTTLE_THROW);
                }
            }
        }
    }
}
