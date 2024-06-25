package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.BreedGoalImproved;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import org.slf4j.Logger;

public class BreedGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Codec<BreedGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(BreedGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("possible_partner").forGetter(BreedGoalCodec::getPartner),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(BreedGoalCodec::getSpeedModifier)
    ).apply(instance, BreedGoalCodec::new));

    private final int goalPriority;
    private final TagKey<EntityType<?>> partner;
    private final double speedModifier;

    public BreedGoalCodec(int goalPriority, TagKey<EntityType<?>> partner,double pSpeedModifier) {
        this.goalPriority = goalPriority;
        this.partner = partner;
        this.speedModifier = pSpeedModifier;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }
    private  TagKey<EntityType<?>> getPartner() {
        return partner;
    }
    private double getSpeedModifier() {
        return speedModifier;
    }
    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof Animal animal) {
            BreedGoalImproved goal = new BreedGoalImproved(animal, speedModifier, partner);
            animal.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding BreedGoal to non-animal!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.BREED_GOAL.get();
    }
}
