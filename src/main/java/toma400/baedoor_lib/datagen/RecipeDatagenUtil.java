package toma400.baedoor_lib.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

/**
 * Extend custom Recipe datagen class with this class.
 */
public class RecipeDatagenUtil extends RecipeProvider {

    public RecipeDatagenUtil(DataGenerator pGenerator) {
        super(pGenerator);
    }

    public void woodset(Consumer<FinishedRecipe> consumer, TagKey<Item> logsTag,
                        ItemLike planks, ItemLike log, ItemLike strippedLog, ItemLike wood, ItemLike strippedWood,
                        ItemLike fence, ItemLike fenceGate, ItemLike pressurePlate, ItemLike door, ItemLike trapdoor,
                        ItemLike button, ItemLike stairs, ItemLike slab, ItemLike sign, ItemLike boat) {

        planksFromLogs(consumer, planks, logsTag);
        woodFromLogs(consumer, wood, log);
        woodFromLogs(consumer, strippedWood, strippedLog);

        woodenBoat(consumer, boat, planks);
        buttonBuilder(button, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        doorBuilder(door, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        fenceBuilder(fence, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        fenceGateBuilder(fenceGate, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        pressurePlateBuilder(pressurePlate, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        slabBuilder(slab, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        stairBuilder(stairs, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        trapdoorBuilder(trapdoor, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
        signBuilder(sign, Ingredient.of(planks)).unlockedBy("has_planks", has(planks)).save(consumer);
    }
}
