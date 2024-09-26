package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.predicates.*;
import net.minecraft.core.Registry;

import static com.scouter.goalsmith.Goalsmith.prefix;


public class PredicateRegistry {

    public static final Codec<? extends OrPredicate<?>> OR_PREDICATE = registerPredicate("or", OrPredicate.CODEC);
    public static final Codec<? extends AndPredicate<?>> AND_PREDICATE = registerPredicate("and", AndPredicate.CODEC);
    public static final Codec<? extends NegatePredicate<?>> NEGATE_PREDICATE = registerPredicate("negate", NegatePredicate.CODEC);
    public static final Codec<? extends TruePredicate<?>> TRUE_PREDICATE = registerPredicate("true", TruePredicate.CODEC);


    public static final Codec<EntityTypePredicate> ENTITY_TYPE_PREDICATE = registerPredicate("is_entity", EntityTypePredicate.CODEC);
    public static final Codec<CanBeCollidedWithPredicate> CAN_BE_COLLIDED_WITH = registerPredicate("can_be_collided_with", CanBeCollidedWithPredicate.CODEC);
    public static final Codec<EntityIsPassengerPredicate> ENTITY_IS_PASSENGER = registerPredicate("is_passenger", EntityIsPassengerPredicate.CODEC);
    public static final Codec<EntityIsVehiclePredicate> ENTITY_IS_VEHICLE = registerPredicate("is_vehicle", EntityIsVehiclePredicate.CODEC);
    public static final Codec<EntityStillAlivePredicate> ENTITY_IS_ALIVE = registerPredicate("is_alive", EntityStillAlivePredicate.CODEC);
    public static final Codec<IsPlayerPredicate> IS_PLAYER = registerPredicate("is_player", IsPlayerPredicate.CODEC);
    public static final Codec<IsSpectatorPredicate> IS_SPECTATOR = registerPredicate("is_spectator", IsSpectatorPredicate.CODEC);
    public static final Codec<IsCreativePredicate> IS_CREATIVE = registerPredicate("is_creative", IsCreativePredicate.CODEC);
    public static final Codec<IsFreezingPredicate> IS_FREEZING = registerPredicate("is_freezing", IsFreezingPredicate.CODEC);
    public static final Codec<IsFreezingPredicate> IS_ON_FIRE = registerPredicate("is_on_fire", IsOnFirePredicate.CODEC);
    public static final Codec<IsDayPredicate> IS_DAY = registerPredicate("is_day", IsDayPredicate.CODEC);
    public static final Codec<IsNightPredicate> IS_NIGHT = registerPredicate("is_night", IsNightPredicate.CODEC);

    public static final Codec<IsInvisiblePredicate> IS_INVISIBLE = registerPredicate("is_invisible", IsInvisiblePredicate.CODEC);

    public static final Codec<LastHurtByMobIsNullPredicate> LAST_HURT_BY_MOB_IS_NULL = registerPredicate("last_hurt_by_mob_is_null", LastHurtByMobIsNullPredicate.CODEC);

    public static final Codec<EntityNotBeingRiddenPredicate> ENTITY_NOT_BEING_RIDDEN = registerPredicate("entity_not_being_ridden", EntityNotBeingRiddenPredicate.CODEC);
    public static final Codec<NoCreativeOrSpectatorPredicate> NO_CREATIVE_OR_SPECTATOR = registerPredicate("no_creative_or_spectator", NoCreativeOrSpectatorPredicate.CODEC);
    public static final Codec<IsDifficultyPredicate> IS_DIFFICULTY = registerPredicate("is_difficulty", IsDifficultyPredicate.CODEC);
    public static final Codec<IsBlockStatePredicate> IS_BLOCK = registerPredicate("is_block", IsBlockStatePredicate.CODEC);

    private static <T extends PredicateCodec<?>> Codec<T> registerPredicate(String name, Codec<T> type) {
        return Registry.register(GSRegistries.PREDICATE_TYPE_SERIALIZER, prefix(name), type);
    }





    public static void register()
    {
    }
}
