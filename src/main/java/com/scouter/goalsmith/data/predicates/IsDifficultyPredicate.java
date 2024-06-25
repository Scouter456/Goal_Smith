package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.Difficulty;

import java.util.function.Predicate;

public class IsDifficultyPredicate implements PredicateCodec<Difficulty> {
    private final Difficulty difficulty;
    public static final Codec<IsDifficultyPredicate> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Difficulty.CODEC.fieldOf("difficulty").forGetter(e -> e.difficulty)
            ).apply(instance, IsDifficultyPredicate::new)
    );

    public IsDifficultyPredicate(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public Predicate<Difficulty> getPredicate() {
        return e -> e == difficulty;
    }

    @Override
    public Codec<? extends PredicateCodec<Difficulty>> codec() {
        return PredicateRegistry.IS_DIFFICULTY.get();
    }
}
