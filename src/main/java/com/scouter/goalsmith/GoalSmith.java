package com.scouter.goalsmith;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.setup.ModSetup;
import com.scouter.goalsmith.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import org.slf4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GoalSmith.MODID)
public class GoalSmith
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "goalsmith";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public GoalSmith()
    {
        Registration.init();
        ModSetup.setup();
        IEventBus forgeBus = NeoForge.EVENT_BUS;
        IEventBus modbus = ModLoadingContext.get().getActiveContainer().getEventBus();
        NeoForge.EVENT_BUS.addListener(this::commands);
        modbus.addListener(ModSetup::init);
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            // static method with no client-only classes in method signature
        }        modbus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
            event.dataPackRegistry(PMRegistries.Keys.GOAL_TYPE, GoalCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.TARGET_GOAL_TYPE, TargetGoalCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.GOAL_OPERATION, GoalOperation.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.PREDICATE_TYPE, PredicateCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.TARGET_GOAL_OPERATION, TargetGoalOperation.DIRECT_CODEC);

        });
    }

    public static ResourceLocation prefix(String name) {
        return  ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }

    public void commands(RegisterCommandsEvent e) {
        //    PuppetCommand.register(e.getDispatcher());
    }
}
