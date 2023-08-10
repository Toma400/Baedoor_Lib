package toma400.baedoorlib.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import toma400.baedoorlib.BaedoorLib;

public class BlocksGen extends BlockModelProvider {

    public BlocksGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator.getPackOutput(), BaedoorLib.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        iterator(Library.BLOCKS);
    }

    public void iterator(Library.DataGenEntry.ofBlock[] blocks) {
        for (Library.DataGenEntry.ofBlock block : blocks) {
            String modelName = block.base.getId().getPath();

            if (block.model == Library.MODEL.CUBE_ALL) {
                cubeAll(modelName, BlockPathRef("", modelName));
                sign(modelName, BlockPathRef("", modelName));
            }
        }
    }

    // Helpers
    public static final ResourceLocation BlockPathRef(String namespace, String item) {
        ResourceLocation Block = new ResourceLocation(BaedoorLib.MOD_ID + ":block/" + item);
        if (namespace != "" && namespace != "mod") {
            Block = new ResourceLocation(namespace + ":block/" + item);
        }
        return Block;
    }
}
