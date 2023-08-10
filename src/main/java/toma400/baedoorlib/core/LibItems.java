package toma400.baedoorlib.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.BaedoorLib;

public class LibItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BaedoorLib.MOD_ID);

    public static final RegistryObject<Item> NETHER_SOUL_ESSENCE = ITEMS.register("nether_soul_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GLISTERING_ASH = ITEMS.register("glistering_ash",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


