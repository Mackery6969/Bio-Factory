package com.github.elenterius.biofactory.datagen.recipes;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biofactory.init.biomancy.BiomancyIntegration;
import com.github.elenterius.biomancy.init.ModItems;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

public class MixingRecipeProvider extends MixingRecipeGen {

	protected MixingRecipeProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> registries) {
		super(generator, registries, BioFactoryMod.MOD_ID);
		init();
	}

	private void init() {
		create("nutrients_fluid_from_paste", recipeBuilder -> {
				int fluidAmount = BiomancyIntegration.convertToFluidAmount(ModItems.NUTRIENT_PASTE.get().getDefaultInstance());
				if (fluidAmount <= 0) {throw new IllegalArgumentException("Cannot create nutrients_fluid_gel_from_paste with amount of 0");}
				return recipeBuilder
					.require(Fluids.WATER, fluidAmount * 4)
					.require(ModItems.NUTRIENT_PASTE.get())
					.require(ModItems.NUTRIENT_PASTE.get())
					.require(ModItems.NUTRIENT_PASTE.get())
					.require(ModItems.NUTRIENT_PASTE.get())
					.output(ModFluids.NUTRIENTS_FLUID.get(), fluidAmount * 4);
			}
		);
	}

}
