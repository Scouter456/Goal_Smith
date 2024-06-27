package com.scouter.goalsmith.data.goalcodec.datagen;

import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.GoalData;
import com.scouter.goalsmith.data.goalcodec.MeleeAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.NearestAttackableTargetGoalCodec;
import com.scouter.goalsmith.data.predicates.TruePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;

import java.util.function.Consumer;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class EntityDataDatagenerator extends GoalDataProvider {
    public EntityDataDatagenerator(PackOutput pOutput) {
        super(pOutput, GoalSmith.MODID);
    }

    @Override
    protected void createGoalData(Consumer<GoalDataConsumer> pWriter) {




        GoalData crazyCow = new GoalDataBuilder(EntityType.COW)
                .addTargetGoal(new NearestAttackableTargetGoalCodec(1, TagKey.create(Registries.ENTITY_TYPE, prefix("player")),10, true, false, new TruePredicate<>()))
                .addReplaceOperation(1, PanicGoal.class, new MeleeAttackGoalCodec(1,4,true))
                .addAttributeAddition(Attributes.ATTACK_DAMAGE, 10)
                .build();



        pWriter.accept(new GoalDataConsumer(prefix("crazy_cow"), crazyCow));
    }
}
