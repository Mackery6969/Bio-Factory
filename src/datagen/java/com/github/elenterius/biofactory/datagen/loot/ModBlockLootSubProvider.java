package com.github.elenterius.biofactory.datagen.loot;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.init.ModBlocks;
import java.util.List;
import java.util.Set;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

	protected ModBlockLootSubProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {

	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		List<Block> blocks = ModBlocks.BLOCKS.getEntries().stream().map(holder -> (Block) holder.get()).toList();
		BioFactoryMod.LOGGER.info(ModLootTableProvider.LOG_MARKER, "generating loot tables for {} blocks...", blocks.size());
		return blocks;
	}

}
