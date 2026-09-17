package com.github.elenterius.biofactory.init;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.item.NutrientsBottleItem;
import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.init.ModRarities;
import com.github.elenterius.biomancy.item.SerumItem;
import com.github.elenterius.biomancy.item.SimpleBlockItem;
import com.github.elenterius.biomancy.item.SimpleItem;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, BioFactoryMod.MOD_ID);

	public static final DeferredHolder<Item, NutrientsBottleItem> NUTRIENTS_BOTTLE = registerItem("nutrients_fluid_bottle", properties -> new NutrientsBottleItem(properties.stacksTo(16).rarity(Rarity.COMMON)));

	private ModItems() {}

	public static <T extends Item> Stream<T> findItems(Class<T> clazz) {
		return ModItems.ITEMS.getEntries().stream()
				.map(DeferredHolder::get)
				.filter(clazz::isInstance)
				.map(clazz::cast);
	}

	public static <T extends Item> Stream<DeferredHolder<Item, T>> findEntries(Class<T> clazz) {
		//noinspection unchecked
		return ModItems.ITEMS.getEntries().stream()
				.filter(registryObject -> clazz.isInstance(registryObject.get()))
				.map(registryObject -> (DeferredHolder<Item, T>) registryObject);
	}

	private static <T extends Item> DeferredHolder<Item, T> registerItem(String name, Function<Item.Properties, T> factory) {
		return ITEMS.register(name, () -> factory.apply(createProperties()));
	}

	private static <T extends Block> DeferredHolder<Item, SimpleBlockItem> registerSimpleBlockItem(DeferredHolder<Block, T> blockHolder) {
		return ITEMS.register(blockHolder.getId().getPath(), () -> new SimpleBlockItem(blockHolder.get(), createProperties()));
	}

	private static <T extends Block> DeferredHolder<Item, SimpleBlockItem> registerSimpleBlockItem(DeferredHolder<Block, T> blockHolder, Rarity rarity) {
		return registerSimpleBlockItem(blockHolder, () -> createProperties().rarity(rarity));
	}

	private static <T extends Block> DeferredHolder<Item, SimpleBlockItem> registerSimpleBlockItem(DeferredHolder<Block, T> blockHolder, Supplier<Item.Properties> properties) {
		return ITEMS.register(blockHolder.getId().getPath(), () -> new SimpleBlockItem(blockHolder.get(), properties.get()));
	}

	private static <T extends Block, I extends BlockItem> DeferredHolder<Item, I> registerBlockItem(DeferredHolder<Block, T> blockHolder, Function<T, I> factory) {
		return ITEMS.register(blockHolder.getId().getPath(), () -> factory.apply(blockHolder.get()));
	}

	private static <T extends Block, I extends BlockItem> DeferredHolder<Item, I> registerBlockItem(DeferredHolder<Block, T> blockHolder, IBlockItemFactory<T, I> factory) {
		return ITEMS.register(blockHolder.getId().getPath(), () -> factory.create(blockHolder.get(), createProperties()));
	}

	private static <T extends Block, I extends BlockItem> DeferredHolder<Item, I> registerBlockItem(DeferredHolder<Block, T> blockHolder, IBlockItemFactory<T, I> factory, Rarity rarity) {
		return ITEMS.register(blockHolder.getId().getPath(), () -> factory.create(blockHolder.get(), createProperties().rarity(rarity)));
	}

	private static <T extends Serum> DeferredHolder<Item, SerumItem> registerSerumItem(DeferredHolder<Serum, T> registryObject) {
		return ITEMS.register(registryObject.getId().getPath(), () -> new SerumItem(createProperties().stacksTo(16).rarity(ModRarities.UNCOMMON), registryObject));
	}

	private static DeferredHolder<Item, SimpleItem> registerSimpleVialItem(String name) {
		return ITEMS.register(name, () -> new SimpleItem(createProperties()));
	}

	private static DeferredHolder<Item, SimpleItem> registerSimpleItem(String name) {
		return ITEMS.register(name, () -> new SimpleItem(createProperties()));
	}

	private static DeferredHolder<Item, SimpleItem> registerSimpleItem(String name, Rarity rarity) {
		return registerSimpleItem(name, () -> createProperties().rarity(rarity));
	}

	private static Item.Properties createProperties() {
		return new Item.Properties().rarity(ModRarities.COMMON);
	}

	private static DeferredHolder<Item, SimpleItem> registerSimpleItem(String name, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> new SimpleItem(properties.get()));
	}

	interface IBlockItemFactory<T extends Block, I extends BlockItem> {
		I create(T block, Item.Properties properties);
	}

}
