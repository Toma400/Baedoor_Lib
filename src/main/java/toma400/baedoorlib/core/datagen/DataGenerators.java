package toma400.baedoorlib.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import toma400.baedoorlib.BaedoorLib;

@Mod.EventBusSubscriber(modid = BaedoorLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    //-----------------------------------------------------------------------------------
    // BlocksGen is doubled because for some reason, BlockStates overwrites data made
    // by BlocksGen, but it also -requires- BlocksGen to be run correctly.
    // Therefore, we run BlocksGen again to overwrite badly written files with correct
    // references.
    //-----------------------------------------------------------------------------------

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()) {
            generator.addProvider(true, new LootTablesGen.LootTablesRedirector(generator));
            generator.addProvider(true, new BlocksGen(generator, event.getExistingFileHelper()));
            generator.addProvider(true, new BlockStatesGen(generator, event.getExistingFileHelper()));
            generator.addProvider(true, new ItemsGen(generator, event.getExistingFileHelper()));
        }

        //language + textures + recipes + tags alone
    }

}
