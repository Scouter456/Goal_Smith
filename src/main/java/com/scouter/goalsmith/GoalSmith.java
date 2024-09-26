package com.scouter.goalsmith;

import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.events.FabricEvents;
import com.scouter.goalsmith.setup.Registration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class Goalsmith implements ModInitializer {
	public static final String MODID = "goalsmith";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and MODID.
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registration.init();
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new EntityGoalJsonManager());

		FabricEvents.onServerStart();
		FabricEvents.onEntityLoad();
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
	}
}