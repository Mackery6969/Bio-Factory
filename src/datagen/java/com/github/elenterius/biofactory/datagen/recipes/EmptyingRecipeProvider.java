package com.github.elenterius.biofactory.datagen.recipes;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biofactory.init.ModItems;
import com.github.elenterius.biofactory.item.NutrientsBottleItem;
import com.simibubi.create.api.data.recipe.EmptyingRecipeGen;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public class EmptyingRecipeProvider extends EmptyingRecipeGen {

	protected EmptyingRecipeProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> registries) {
		super(generator, registries, BioFactoryMod.MOD_ID);
		init();
	}

	private void init() {
		create("nutrients_fluid_bottle", recipeBuilder -> recipeBuilder
			.require(ModItems.NUTRIENTS_BOTTLE.get())
			.output(ModFluids.NUTRIENTS_FLUID.get(), NutrientsBottleItem.FLUID_AMOUNT)
			.output(Items.GLASS_BOTTLE)
		);
	}

}
