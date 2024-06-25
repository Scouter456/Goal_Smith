package com.scouter.goalsmith.datagen;

import com.scouter.goalsmith.data.AttributesAdditions;
import com.scouter.goalsmith.data.GoalData;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.TargetGoalOperation;
import com.scouter.goalsmith.data.goalcodec.MeleeAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.NearestAttackableTargetGoalCodec;
import com.scouter.goalsmith.data.operation.goal.ReplaceOperation;
import com.scouter.goalsmith.data.operation.target.AddTargetOperation;

import com.scouter.goalsmith.data.predicates.TruePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class EntityDataDatagenerator extends EntityDataProvider{
    public EntityDataDatagenerator(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildPuppets(Consumer<EntityDataConsumer> pWriter) {


        List<GoalOperation> goalOperations = new ArrayList<>();
        List<TargetGoalOperation> targetGoalOperations = new ArrayList<>();
        List<AttributesAdditions> attributeAdditions =new ArrayList<>();
        ReplaceOperation replaceOperation = new ReplaceOperation(new ReplaceOperation.ReplacementGoal(1, PanicGoal.class), new MeleeAttackGoalCodec(1,4,true));
        goalOperations.add(replaceOperation);

        List<AttributesAdditions.AttributesMap> attributesMaps = new ArrayList<>();
        AttributesAdditions.AttributesMap attm = new AttributesAdditions.AttributesMap(Attributes.ATTACK_DAMAGE, 10, UUID.randomUUID());
       //AttributesAdditions.AttributesMap attm2 = new AttributesAdditions.AttributesMap(Attributes.MAX_HEALTH, 50, UUID.randomUUID());
       //AttributesAdditions.AttributesMap attm3 = new AttributesAdditions.AttributesMap(Attributes.FOLLOW_RANGE, 10, UUID.randomUUID());
       //AttributesAdditions.AttributesMap attm5 = new AttributesAdditions.AttributesMap(Attributes.JUMP_STRENGTH, 10, UUID.randomUUID());
       //AttributesAdditions.AttributesMap attm6 = new AttributesAdditions.AttributesMap(Attributes.MOVEMENT_SPEED, 5, UUID.randomUUID());
        NearestAttackableTargetGoalCodec nearestAttackableTargetGoal = new NearestAttackableTargetGoalCodec(1, TagKey.create(Registries.ENTITY_TYPE, prefix("player")),10, true, false, new TruePredicate<>());
        AddTargetOperation addTargetOperation = new AddTargetOperation(List.of(nearestAttackableTargetGoal));

        attributesMaps.add(attm);
        targetGoalOperations.add(addTargetOperation);
        //attributesMaps.add(attm2);
        //attributesMaps.add(attm3);
        //attributesMaps.add(attm5);
        //attributesMaps.add(attm6);

        AttributesAdditions attributesAdditions = new AttributesAdditions(attributesMaps);
        attributeAdditions.add(attributesAdditions);

        GoalData entityData = new GoalData(new ResourceLocation("cow"), goalOperations, targetGoalOperations, attributeAdditions);
        pWriter.accept(new EntityDataConsumer(prefix("test"), entityData));
    }
}
