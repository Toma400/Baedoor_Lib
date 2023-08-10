package toma400.baedoorlib.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;
import toma400.baedoorlib.core.LibBlocks;

import java.util.List;
import java.util.Set;

public class LootTablesGen {

    public static class LootTablesRedirector extends LootTableProvider {
        public LootTablesRedirector(DataGenerator generator) {
            super(generator.getPackOutput(), Set.of(), List.of(
                    new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)
            ));
        }
    }

    public static class BlockLootTables extends BlockLootSubProvider {

        public BlockLootTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (Library.DataGenEntry.ofBlock block : Library.BLOCKS) {
                if (block.loot_table == Library.LOOT_TABLE.DEFAULT_BLOCK) {
                    this.dropSelf(block.base.get());
                }
            }
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return LibBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }

}
