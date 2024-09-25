package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

public class IsBlockStatePredicate implements PredicateCodec<BlockState> {

    private final Block block;


    public static final MapCodec<IsBlockStatePredicate> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(IsBlockStatePredicate::getBlock)
            ).apply(instance, IsBlockStatePredicate::new)
    );

    public IsBlockStatePredicate(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public Predicate<BlockState> getPredicate() {
        return e -> e.is(block);
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<BlockState>> codec() {
        return PredicateRegistry.IS_BLOCK.get();
    }
}