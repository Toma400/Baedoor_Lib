package toma400.baedoorlib.core.datagen;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.core.LibBlocks;
import toma400.baedoorlib.core.LibItems;

public class Library {

    /* MAIN DATAGEN CLASS -------------------------------------------------------------------------------------
    Class that is used to be set for every block. Helps setting up proper datagen data.
    -------------------------------------------------------------------------------------------------------- */
    public static class DataGenEntry {
        public static class ofBlock {
            public RegistryObject<Block> base;       // registry of block you bind datagen to
            public MODEL                 model;      // model type
            public BLOCKSTATE            blockstate; // blockstate type
            public ITEM                  item;       // item model type (use only ITEM.DEFAULT!)
            public LOOT_TABLE            loot_table; // loot table

            public ofBlock(RegistryObject<Block> a, MODEL b, BLOCKSTATE t, ITEM i, LOOT_TABLE l) {
                this.base       = a;
                this.model      = b;
                this.blockstate = t;
                this.item       = i;
                this.loot_table = l;
            }
        }
        public static class ofItem {
            public RegistryObject<Item> base; // registry of item you bind datagen to
            public ITEM                 item; // item model type

            public ofItem(RegistryObject<Item> a, ITEM i) {
                this.base = a;
                this.item = i;
            }
        }
    }

    /* ENUMS --------------------------------------------------------------------------------------------------
    Enums that let you customise data generation. This way you don't need to write some crazy parsers to
    find out by name which one is desired.
    -------------------------------------------------------------------------------------------------------- */
    public enum MODEL { // cobr: BlocksGen (can overlap with TYPE)
        CUBE_ALL
    }
    public enum BLOCKSTATE {  // cobr: BlockStatesGen
        DEFAULT, TRAPDOOR
    }
    public enum ITEM { // cobr: ItemsGen
        DEFAULT, GENERATED, HANDHELD, SPAWN_EGG // default lets you use default distributor, other types are custom
    }
    public enum LOOT_TABLE { // cobr: LootTablesGen
        DEFAULT_BLOCK
    }

    /* MAPS ---------------------------------------------------------------------------------------------------
    Maps with data that will be taken into datagen functions during generation phase. The only thing you should
    care about to update manually. Look at 'DataGenEntry' for respective builders.
    -------------------------------------------------------------------------------------------------------- */
    public static DataGenEntry.ofBlock[] BLOCKS = {
            new DataGenEntry.ofBlock(LibBlocks.BLOCK_OF_NETHER_SOUL_ESSENCE, MODEL.CUBE_ALL, BLOCKSTATE.DEFAULT, ITEM.DEFAULT, LOOT_TABLE.DEFAULT_BLOCK)
    };
    public static DataGenEntry.ofItem[] ITEMS = { // only non-block items!
            new DataGenEntry.ofItem(LibItems.NETHER_SOUL_ESSENCE, ITEM.DEFAULT)
    };
}
