package toma400.baedoorlib.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.BaedoorLib;

import java.util.function.Supplier;

public class LibBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BaedoorLib.MOD_ID);

    public static final RegistryObject<Block> BLOCK_OF_NETHER_SOUL_ESSENCE = registerBlock("block_of_nether_soul_essence",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5F).destroyTime(0.6F)
                    .mapColor(MapColor.COLOR_BROWN)
                    .sound(SoundType.SOUL_SOIL)),
                  new Item.Properties());

    private static <T extends Block> RegistryObject<T> registerBlock (String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, properties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem (String name, RegistryObject<T> block, Item.Properties properties) {
        return LibItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }

    public static void register (IEventBus event) {
        BLOCKS.register(event);
    }
}
