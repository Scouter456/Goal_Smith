package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;

public class MoveThroughVillageGoalCodec implements GoalCodec {


    public static final Codec<MoveThroughVillageGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MoveThroughVillageGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MoveThroughVillageGoalCodec::getSpeedModifier),
            Codec.BOOL.fieldOf("only_at_night").forGetter(MoveThroughVillageGoalCodec::isOnlyAtNight),
            Codec.INT.fieldOf("distance_to_poi").forGetter(MoveThroughVillageGoalCodec::getDistanceToPoi),
            Codec.BOOL.fieldOf("can_deal_with_doors").forGetter(MoveThroughVillageGoalCodec::isCanDealWithDoors)
    ).apply(instance, MoveThroughVillageGoalCodec::new));
    private final int goalPriority;
    private final double speedModifier;
    private final boolean onlyAtNight;
    private final int distanceToPoi;
    private final boolean canDealWithDoors;
    public MoveThroughVillageGoalCodec(int goalPriority, double speedModifier, boolean onlyAtNight, int distanceToPoi, boolean canDealWithDoors) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.onlyAtNight = onlyAtNight;
        this.distanceToPoi = distanceToPoi;
        this.canDealWithDoors = canDealWithDoors;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public boolean isOnlyAtNight() {
        return onlyAtNight;
    }

    public int getDistanceToPoi() {
        return distanceToPoi;
    }

    public boolean isCanDealWithDoors() {
        return canDealWithDoors;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        MoveThroughVillageGoal goal = new MoveThroughVillageGoal(mob, speedModifier, onlyAtNight, distanceToPoi, () -> canDealWithDoors);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.MOVE_THROUGH_VILLAGE_GOAL.get();
    }

    @Override
    public String toString() {
        return "MoveThroughVillageGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", onlyAtNight=" + onlyAtNight +
                ", distanceToPoi=" + distanceToPoi +
                ", canDealWithDoors=" + canDealWithDoors +
                '}';
    }
}
