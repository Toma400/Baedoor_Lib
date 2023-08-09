package toma400.baedoorlib.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.BaedoorLib;

public class LibUtils {
    public static final DeferredRegister<CreativeModeTab> BAEDOORLIB_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BaedoorLib.MOD_ID);

    // fills the creative tab with items (in order defined in LibItems.ITEMS registry, I assume)
    private static void fillTab(CreativeModeTab.Output pOutput) {
        for (RegistryObject<Item> it : LibItems.ITEMS.getEntries()) {
            pOutput.accept(it.get());
        }
        for (RegistryObject<Block> it : LibBlocks.BLOCKS.getEntries()) {
            pOutput.accept(it.get());
        }
    }

    public static final RegistryObject<CreativeModeTab> BAEDOORLIB_TAB = BAEDOORLIB_TABS.register("baedoorlib_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(LibItems.NETHER_SOUL_ESSENCE.get()))
                    .title(Component.translatable("creative_tab." + BaedoorLib.MOD_ID + ".baedoorlib_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        fillTab(pOutput);
                    }))
                    .build());

    public static void registerTabs(IEventBus event) {
        BAEDOORLIB_TABS.register(event);
    }
}
