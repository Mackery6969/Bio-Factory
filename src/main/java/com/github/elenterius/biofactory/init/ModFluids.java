package com.github.elenterius.biofactory.init;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.simibubi.create.content.fluids.VirtualFluid;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModFluids {

	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, BioFactoryMod.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, BioFactoryMod.MOD_ID);

	public static final DeferredHolder<FluidType, FluidType> NUTRIENTS_TYPE = registerType("nutrients_fluid", properties -> properties);
	public static final Supplier<BaseFlowingFluid.Properties> NUTRIENTS_FLUID_PROPERTIES = () -> new BaseFlowingFluid.Properties(NUTRIENTS_TYPE, ModFluids.NUTRIENTS_FLUID,
		ModFluids.NUTRIENTS_FLUID);
	public static final DeferredHolder<Fluid, BaseFlowingFluid> NUTRIENTS_FLUID = register("nutrients_fluid", () -> new VirtualFluid(NUTRIENTS_FLUID_PROPERTIES.get(), true));

	private ModFluids() {}

	static void registerInteractions() {}

	private static <T extends Fluid> DeferredHolder<Fluid, T> register(String name, Supplier<T> factory) {
		return FLUIDS.register(name, factory);
	}

	private static DeferredHolder<FluidType, FluidType> registerType(String name, UnaryOperator<FluidType.Properties> operator) {
		return FLUID_TYPES.register(name, () -> new FluidType(operator.apply(createFluidTypeProperties())) {

			private final ResourceLocation stillTexture = BioFactoryMod.createRL("block/%s_still".formatted(name));
			private final ResourceLocation flowingTexture = BioFactoryMod.createRL("block/%s_flowing".formatted(name));
			private final ResourceLocation overlayTexture = BioFactoryMod.createRL("block/%s_overlay".formatted(name));
			private final ResourceLocation renderOverlayTexture = overlayTexture.withPrefix("textures/");

			@Override
			public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
				consumer.accept(new IClientFluidTypeExtensions() {
					@Override
					public ResourceLocation getStillTexture() {
						return stillTexture;
					}

					@Override
					public ResourceLocation getFlowingTexture() {
						return flowingTexture;
					}

					@Override
					public ResourceLocation getOverlayTexture() {
						return overlayTexture;
					}

					@Override
					public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
						return renderOverlayTexture;
					}
				});
			}
		});
	}

	private static FluidType.Properties createFluidTypeProperties() {
		return FluidType.Properties.create()
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
	}

}
