package com.github.elenterius.biofactory.datagen.tags;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.simibubi.create.Create;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper) {
		super(output, lookupProvider, BioFactoryMod.MOD_ID, fileHelper);
	}

	protected IntrinsicTagAppender<Block> tag(String modId, String path) {
		return super.tag(createTagKey(modId, path));
	}

	protected TagKey<Block> createTagKey(String modId, String path) {
		return BlockTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
	}

	@Override
	protected void addTags(Provider provider) {
		// https://wiki.createmod.net/developers/tags#block-tags

		//Blocks which should be able to move on contraptions, but would otherwise be ignored due to their empty collision shape
		tag(Create.ID, "movable_empty_collider").add(
			ModBlocks.FLESH_DOOR.get(),
			ModBlocks.FLESH_IRIS_DOOR.get()
		);

		//		tag(Create.ID, "create:wrench_pickup").add(
		//			ModBlocks.MAW_HOPPER.get()
		//		);

		//Blocks which can count toward a functional windmill structure
		//Example: Wool
		tag(Create.ID, "windmill_sails")
			.addOptionalTag(createTagKey(BiomancyMod.MOD_ID, "membrane")); //future biomancy tag
	}

}
