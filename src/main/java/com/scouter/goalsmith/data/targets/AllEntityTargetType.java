package com.scouter.goalsmith.data.targets;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.data.EntityTargetType;
import com.scouter.goalsmith.data.EntityTargetTypeRegistry;
import com.scouter.goalsmith.data.GoalData;
import net.minecraft.server.level.ServerLevel;

public class AllEntityTargetType implements EntityTargetType {

    public static AllEntityTargetType INSTANCE = new AllEntityTargetType();
    public static Codec<AllEntityTargetType> CODEC = Codec.unit(INSTANCE);

    @Override
    public void apply(ServerLevel level, GoalData data) {
        EntityGoalJsonManager.getAllEntityData().add(data);
    }

    @Override
    public Codec<? extends EntityTargetType> codec() {
        return EntityTargetTypeRegistry.ALL_TARGET;

    }
}
