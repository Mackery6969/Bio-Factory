package com.github.elenterius.biofactory.datagen.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;

public final class ModRecipeProviders {

	private static final List<RecipeProvider> RECIPE_PROVIDERS = new ArrayList<>();

	public static void addProviders(boolean run, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		RECIPE_PROVIDERS.add(new CompactingRecipeProvider(output, registries));
		RECIPE_PROVIDERS.add(new MixingRecipeProvider(output, registries));
		RECIPE_PROVIDERS.add(new FillingRecipeProvider(output, registries));
		RECIPE_PROVIDERS.add(new EmptyingRecipeProvider(output, registries));

		RECIPE_PROVIDERS.add(new DecomposingRecipeProvider(output, registries));
		RECIPE_PROVIDERS.add(new BioForgingRecipeProvider(output, registries));
		RECIPE_PROVIDERS.add(new VanillaRecipeProvider(output, registries));

		generator.addProvider(run, new DataProvider() {

			@Override
			public String getName() {
				return "Bio-Factory's Recipes";
			}

			@Override
			public CompletableFuture<?> run(CachedOutput cache) {
				return CompletableFuture.allOf(RECIPE_PROVIDERS.stream()
					.map(gen -> gen.run(cache))
					.toArray(CompletableFuture[]::new));
			}
		});
	}

}