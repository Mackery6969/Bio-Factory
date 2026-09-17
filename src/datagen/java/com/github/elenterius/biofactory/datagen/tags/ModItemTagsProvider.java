package com.github.elenterius.biofactory.datagen.tags;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biomancy.init.ModItems;
import com.simibubi.create.Create;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTags, BioFactoryMod.MOD_ID, existingFileHelper);
	}

	protected IntrinsicTagAppender<Item> tag(String modId, String path) {
		return super.tag(createTagKey(modId, path));
	}

	protected TagKey<Item> createTagKey(String modId, String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
	}

	@Override
	protected void addTags(Provider provider) {
		//Items which do not have "burn time" for furnaces, but should act as food for the blaze burner (same value as coal)
		tag(Create.ID, "blaze_burner_fuel/regular").add(
			ModItems.NUTRIENT_BAR.get()
		);
	}

}
