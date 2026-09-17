package com.github.elenterius.biofactory.init.create;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.simibubi.create.api.registry.CreateRegistries;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttributeType;
import com.simibubi.create.content.logistics.item.filter.attribute.SingletonItemAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.ItemStack;
import java.util.function.Predicate;

public final class ItemAttributes {

	public static final DeferredRegister<ItemAttributeType> ITEM_ATTRIBUTE_TYPES = DeferredRegister.create(CreateRegistries.ITEM_ATTRIBUTE_TYPE, BioFactoryMod.MOD_ID);

	public static final DeferredHolder<ItemAttributeType, ItemAttributeType> ACID_CORRODIBLE = singleton("acid_corrodible", stack -> FanProcessingTypes.ACID_SPLASHING.get().canProcess(stack));

	private static DeferredHolder<ItemAttributeType, ItemAttributeType> singleton(String id, Predicate<ItemStack> predicate) {
		return ITEM_ATTRIBUTE_TYPES.register(id, () -> new SingletonItemAttribute.Type(type -> new SingletonItemAttribute(type, (stack, level) -> predicate.test(stack), id)));
	}

}
