package com.scouter.goalsmith.data.goalcodec.targetgoalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.TargetGoalCodec;
import com.scouter.goalsmith.data.goals.target.HurtByTargetGoalImproved;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class HurtByTargetGoalCodec implements TargetGoalCodec {

    public static final Codec<HurtByTargetGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("target_goal_priority").forGetter(codec -> codec.goalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("to_ignore").forGetter(c -> c.ignoreTags)
    ).apply(instance, HurtByTargetGoalCodec::new));

    private final int goalPriority;
    private final TagKey<EntityType<?>> ignoreTags;

    public HurtByTargetGoalCodec(int goalPriority, TagKey<EntityType<?>> ignoreTags) {
        this.goalPriority = goalPriority;
        this.ignoreTags = ignoreTags;
    }


    @Override
    public Goal addTargetGoal(PathfinderMob mob) {
        HurtByTargetGoalImproved goal = new HurtByTargetGoalImproved(mob, ignoreTags);
        mob.targetSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends TargetGoalCodec> codec() {
        return GoalRegistry.HURT_BY_TARGET_GOAL.get();
    }

    @Override
    public String toString() {
        return "HurtByTargetGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", toIgnore=" + +
                '}';
    }
}
