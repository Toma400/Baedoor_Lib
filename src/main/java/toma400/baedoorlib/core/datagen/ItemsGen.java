package toma400.baedoorlib.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.BaedoorLib;

public class ItemsGen extends ItemModelProvider {

    public ItemsGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator.getPackOutput(), BaedoorLib.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        ItemRegistrar(Library.ITEMS);
        BlockRegistrar(Library.BLOCKS);
    }

    //--------------------------------------------------------------------
    // ITEM MODELS REGISTRY
    //--------------------------------------------------------------------
    //TODO: Shouldn't it be not settable? Since you can default it everytime and let handler to it by itself
    //--------------------------------------------------------------------
    public void ItemRegistrar(Library.DataGenEntry.ofItem[] items) {
        for (Library.DataGenEntry.ofItem item : items) {
            ModelFile modelType = getExistingFile(mcLoc("item/generated")); // default
            String    modelName = item.base.getId().getPath();
            String    resName   = modelName;
            // custom values
            if (item.item != Library.ITEM.DEFAULT) {
                switch(item.item) {
                    case HANDHELD:  modelType = getExistingFile(mcLoc("item/handheld")); break; // override of default
                    case SPAWN_EGG: resName = "spawn_egg"; break;
                    case GENERATED: break; // using default
                }
                this.getBuilder(modelName).parent(modelType).texture("layer0", ITEM_FOLDER + "/" + resName);
            // default values
            } else {
                if (item.base.get() instanceof SpawnEggItem) { resName = "spawn_egg"; }
                if (item.base.get() instanceof TieredItem)   { modelType = getExistingFile(mcLoc("item/handheld")); }
                this.getBuilder(modelName).parent(modelType).texture("layer0", ITEM_FOLDER + "/" + resName);
            }
        }
    }
    //--------------------------------------------------------------------
    // BLOCK ITEM MODELS REGISTRY
    //--------------------------------------------------------------------
    public void BlockRegistrar(Library.DataGenEntry.ofBlock[] blocks) {
        for (Library.DataGenEntry.ofBlock block : blocks) {
            String modelName  = block.base.getId().getPath();
            String adjustName = adjustBlockName(block.base);

            if (block.item == Library.ITEM.DEFAULT) {
                if (block.base.get() instanceof DoorBlock) {
                    this.getBuilder(modelName).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", ITEM_FOLDER + "/" + modelName);
                } else {
                    withExistingParent(modelName, new ResourceLocation(BaedoorLib.MOD_ID, "block/" + modelName + adjustName));
                }
            } else {
                System.out.println("Used non-default datagen constructor for block item: " + modelName);
            }
        }
    }

    // Helper method
    public static String adjustBlockName(RegistryObject<Block> block) {
        if (block.get() instanceof TrapDoorBlock)   { return "_bottom";    }
        else if (block.get() instanceof FenceBlock) { return "_inventory"; }
        else                                        { return "";           } // default
    }
}
