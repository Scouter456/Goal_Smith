package com.scouter.goalsmith;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.setup.ClientSetup;
import com.scouter.goalsmith.setup.ModSetup;
import com.scouter.goalsmith.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataPackRegistryEvent;
import org.slf4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/mods.toml file
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
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::commands);
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        modbus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
            event.dataPackRegistry(PMRegistries.Keys.GOAL_TYPE, GoalCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.TARGET_GOAL_TYPE, TargetGoalCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.GOAL_OPERATION, GoalOperation.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.PREDICATE_TYPE, PredicateCodec.DIRECT_CODEC);
            event.dataPackRegistry(PMRegistries.Keys.TARGET_GOAL_OPERATION, TargetGoalOperation.DIRECT_CODEC);

        });
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }

    public void commands(RegisterCommandsEvent e) {
    //    PuppetCommand.register(e.getDispatcher());
    }
}
