package com.scouter.goalsmith.setup;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.data.*;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){

        GSRegistries.init();
        GoalRegistry.register();
        PredicateRegistry.register();
        GoalOperationRegistry.register();
        GoalOperationRegistry.register();
        EntityTargetTypeRegistry.register();


    }
}
