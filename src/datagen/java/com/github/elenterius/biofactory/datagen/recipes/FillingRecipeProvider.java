package com.github.elenterius.biofactory.datagen.recipes;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biofactory.init.ModItems;
import com.github.elenterius.biofactory.item.NutrientsBottleItem;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class FillingRecipeProvider extends FillingRecipeGen {

	protected FillingRecipeProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> registries) {
		super(generator, registries, BioFactoryMod.MOD_ID);
		init();
	}

	private void init() {
		create("nutrients_fluid_bottle", recipeBuilder -> recipeBuilder
			.require(ModFluids.NUTRIENTS_FLUID.get(), NutrientsBottleItem.FLUID_AMOUNT)
			.require(Items.GLASS_BOTTLE)
			.output(ModItems.NUTRIENTS_BOTTLE.get())
		);

		create("rich_soil", recipeBuilder -> recipeBuilder.whenModLoaded("farmersdelight")
			.require(ModFluids.NUTRIENTS_FLUID.get(), NutrientsBottleItem.FLUID_AMOUNT * 2)
			.require(Items.DIRT)
			.output(1, ResourceLocation.fromNamespaceAndPath("farmersdelight", "rich_soil"), 1)
		);
	}

}
