package toma400.baedoorlib.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import toma400.baedoorlib.BaedoorLib;

public class BlockStatesGen extends BlockStateProvider {

    public BlockStatesGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator.getPackOutput(), BaedoorLib.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        iterator(Library.BLOCKS);
    }

    public void iterator(Library.DataGenEntry.ofBlock[] blocks) {
        for (Library.DataGenEntry.ofBlock block : blocks) {
            String modelName = block.base.getId().getPath();

            if (block.blockstate == Library.BLOCKSTATE.DEFAULT) {
                simpleBlock(block.base.get(), modelProvider(modelName, ""));
            }
        }
    }

    // Helper
    public ModelFile modelProvider (String resourceGiven, String modelVariant) {
        //----------------------------------------------------------------------------------------------------------------------
        // modelVariant should be default as "", as it directs us precisely to file named after block
        // though if we want to add some variation (for example "_horizontal" suffix), it will be used under that string
        //----------------------------------------------------------------------------------------------------------------------
        ResourceLocation locationWorkedOn = new ResourceLocation(BaedoorLib.MOD_ID + ":block/" + resourceGiven + modelVariant);
        ModelFile modelWorkedOn = models().withExistingParent(resourceGiven + modelVariant, locationWorkedOn);
        return modelWorkedOn;
    }
}
