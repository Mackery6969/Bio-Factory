package com.github.elenterius.biofactory.init;

import com.github.elenterius.biofactory.BioFactoryMod;
import com.github.elenterius.biofactory.init.biomancy.BiomancyIntegration;
import com.github.elenterius.biofactory.init.create.CreateIntegration;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = BioFactoryMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CommonSetupHandler {

	private CommonSetupHandler() {}

	@SubscribeEvent
	public static void onSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BiomancyIntegration.onPostSetup();
			CreateIntegration.onPostSetup();
		});

		ModFluids.registerInteractions();
	}

}
