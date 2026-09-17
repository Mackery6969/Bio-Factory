package com.github.elenterius.biofactory.datagen.recipes;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.crafting.AcolyteGogglesUpgradeRecipe;
import com.github.elenterius.biomancy.init.ModItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Recipe;
import java.util.function.Function;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class VanillaRecipeProvider extends RecipeProvider {

	protected VanillaRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
		super(packOutput, registries);
	}

	protected ResourceLocation getSpecialCraftingRecipeId(ItemLike itemLike) {
		return BioFactoryMod.createRL("special_crafting/" + getItemName(itemLike));
	}

	protected void special(RecipeOutput recipeOutput, ItemLike result, Function<CraftingBookCategory, Recipe<?>> factory) {
		SpecialRecipeBuilder.special(factory).save(recipeOutput, getSpecialCraftingRecipeId(result).toString());
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		special(recipeOutput, ModItems.ACOLYTE_ARMOR_HELMET.get(), AcolyteGogglesUpgradeRecipe::new);
	}

}
