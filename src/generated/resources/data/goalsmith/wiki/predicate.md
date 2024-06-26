# Predicates

### Introduction to Predicates

Predicates in GoalSmith serve as conditions that determine whether certain actions or goals should be applied to entities within Minecraft. They encapsulate logical checks based on entity attributes, states, or environmental conditions.

#### Example Usage

Let's consider an example using the `IsCreativePredicate`:

```
{
  "type": "goalsmith:is_creative"
}
```

In this example, the `IsCreativePredicate` checks whether an entity is in creative mode. If the entity is in creative mode, the predicate evaluates to true; otherwise, it evaluates to false. Such predicates can be used in conjunction with goal operations to selectively apply behaviors to entities based on their game mode.

A total of 21 predicates are defined for now, which are all listed below

### Currently defined Predicates

<details>
<summary>And Predicate</summary>

The And Predicate, combines multiple predicates to determine if all conditions are met simultaneously.

**Example of And Predicate usage:**

```
{
  "predicate_1": {
    "predicate": {
      "type": "goalsmith:is_creative"
    },
    "type": "goalsmith:negate"
  },
  "predicate_2": {
    "type": "goalsmith:is_day"
  },
  "type": "goalsmith:and"
}
```

The above JSON represents an And Predicate that combines the following conditions:

- 1. NegatePredicate: Negates the result of IsCreativePredicate. So if the player is in creative, it will negate the result and return false.
- 2. IsDayPredicate: Checks whether it is currently daytime.

If these checks are passed it will allow a goal or target goal to go further with the code, or it can be used as a filter.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/AndPredicate.java)

</details>

<details>
<summary>Or Predicate</summary>

The Or Predicate checks if at least one of the provided conditions is true. In this example, it evaluates whether the player is in Creative mode or if it is daytime.

**Example of Or Predicate:**

```
{
  "predicate_1": {
    "type": "goalsmith:is_creative"
  },
  "predicate_2": {
    "type": "goalsmith:is_day"
  },
  "type": "goalsmith:or"
}
```

The above JSON represents an Or Predicate that combines the following conditions:

- 1. IsCreativePredicate: Checks if the player is in Creative mode.
- 2. IsDayPredicate: Checks whether it is currently daytime.

If either of these conditions is true, the predicate evaluates to true.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/OrPredicate.java)

</details>

<details>
<summary>Negate Predicate</summary>

The Negate Predicate reverses the result of the enclosed predicate. In this example, it negates the result of a True Predicate, effectively evaluating to false in all cases.

**Example of Negate Predicate:**

```
{
  "predicate": {
    "type": "goalsmith:true"
  },
  "type": "goalsmith:negate"
}
```

The above JSON represents a Negate Predicate that reverses the result of the True Predicate causing it to return false.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/NegatePredicate.java)

</details>

<details>
<summary>True Predicate</summary>

The True Predicate always evaluates to true, regardless of the conditions or inputs.

**Example of True Predicate:**

```
{
  "type": "goalsmith:true"
}
```

The above JSON represents a True Predicate, which unconditionally returns true.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/TruePredicate.java)

</details>

<details>
<summary>No Creative Or Spectator Predicate</summary>

The No Creative Or Spectator Predicate checks whether an entity is not a player in creative mode or a spectator.

**Example of No Creative Or Spectator Predicate:**

```
{
  "type": "goalsmith:no_creative_or_spectator"
}
```

The above JSON represents a No Creative Or Spectator Predicate that evaluates the following conditions:

- 1. Check if the entity is not a player.
- 2. Check if the entity is not in spectator mode.
- 3. Check if the player is not in creative mode.

If any of these conditions are true, the predicate evaluates to true.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/TruePredicate.java)

</details>

<details>
<summary>Last Hurt By Mob Is Null Predicate</summary>

The Last Hurt By Mob Is Null Predicate checks whether the entity has not been recently hurt by any mob.

**Example of Last Hurt By Mob Is Null Predicate:**

```
{
  "type": "goalsmith:last_hurt_by_mob_is_null"
}
```

The above JSON represents a Last Hurt By Mob Is Null Predicate that evaluates whether:

- 1. The entity is an instance of LivingEntity.
- 2. The last mob that hurt the entity is null (meaning no mob has hurt the entity recently).

If these conditions are met, the predicate evaluates to true.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/LastHurtByMobIsNullPredicate.java)

</details>

<details>
<summary>Is Spectator Predicate</summary>

The Is Spectator Predicate checks whether an entity is in spectator mode.

**Example of Is Spectator Predicate:**

```
{
  "type": "goalsmith:is_spectator"
}
```

The above JSON represents an Is Spectator Predicate that evaluates whether the entity is a player and in spectator mode.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsSpectatorPredicate.java)

</details>

<details>
<summary>Is Player Predicate</summary>

The Is Player Predicate checks whether an entity is a player.

**Example of Is Player Predicate:**

```
{
  "type": "goalsmith:is_player"
}
```

The above JSON represents an Is Player Predicate that evaluates whether the entity is a player.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsPlayerPredicate.java)

</details>

<details>
<summary>Is On Fire Predicate</summary>

The Is On Fire Predicate checks whether an entity is currently on fire.

**Example of Is On Fire Predicate:**

```
{
  "type": "goalsmith:is_on_fire"
}
```

The above JSON represents an Is On Fire Predicate that evaluates whether the entity is on fire.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsOnFirePredicate.java)

</details>

<details>
<summary>Is Day Predicate</summary>

The Is Day Predicate checks whether it is currently daytime in the entity's world.

**Example of Is Day Predicate:**

```
{
  "type": "goalsmith:is_day"
}
```

The above JSON represents an Is Day Predicate that checks whether it is currently daytime in the entity's world.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsDayPredicate.java)

</details>

<details>
<summary>Is Night Predicate</summary>

The Is Night Predicate checks whether it is nighttime in the entity's current level.

**Example of Is Night Predicate:**

```
{
  "type": "goalsmith:is_night"
}
```

The above JSON represents an Is Night Predicate that evaluates whether it is nighttime in the entity's current level.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsNightPredicate.java)

</details>

<details>
<summary>Is Invisible Predicate</summary>

The Is Invisible Predicate checks whether the entity is currently invisible.

**Example of Is Invisible Predicate:**

```
{
  "type": "goalsmith:is_invisible"
}
```

The above JSON represents an Is Invisible Predicate that evaluates whether the entity is currently invisible.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsInvisiblePredicate.java)

</details>

<details>
<summary>Is Freezing Predicate</summary>

The Is Freezing Predicate checks whether the entity is currently freezing.

**Example of Is Freezing Predicate:**

```
{
  "type": "goalsmith:is_freezing"
}
```

The above JSON represents an Is Freezing Predicate that evaluates whether the entity is currently freezing.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsFreezingPredicate.java)

</details>

<details>
<summary>Is Creative Predicate</summary>

The Is Creative Predicate checks whether the entity (player) is in Creative mode.

**Example of Is Creative Predicate:**

```
{
  "type": "goalsmith:is_creative"
}
```

The above JSON represents an Is Creative Predicate that checks whether the entity (player) is in Creative mode.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsCreativePredicate.java)

</details>

<details>
<summary>Entity Type Predicate</summary>

The Entity Type Predicate checks whether the entity's type matches a specified entity type.

**Example of Entity Type Predicate:**

```
{
  "entity_type": "minecraft:cow",
  "type": "goalsmith:is_entity"
}
```

The above JSON represents an Entity Type Predicate that checks whether the entity's type is `minecraft:cow`.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityTypePredicate.java)

</details>

<details>
<summary>Entity Still Alive Predicate</summary>

The Entity Still Alive Predicate checks whether an entity is still alive.

**Example of Entity Still Alive Predicate:**

```
{
  "type": "goalsmith:is_alive"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityStillAlivePredicate.java)

</details>

<details>
<summary>Entity Not Being Ridden Predicate</summary>

The Entity Not Being Ridden Predicate checks whether an entity is not being ridden.

**Example of Entity Not Being Ridden Predicate:**

```
{
  "type": "goalsmith:entity_not_being_ridden"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityNotBeingRiddenPredicate.java)

</details>

<details>
<summary>Entity Is Vehicle Predicate</summary>

The Entity Is Vehicle Predicate checks whether an entity is currently acting as a vehicle.

**Example of Entity Is Vehicle Predicate:**

```
{
  "type": "goalsmith:is_vehicle"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityIsVehiclePredicate.java)

</details>

<details>
<summary>Entity Is Passenger Predicate</summary>

The Entity Is Passenger Predicate checks whether an entity is currently acting as a passenger.

**Example of Entity Is Passenger Predicate:**

```
{
  "type": "goalsmith:is_passenger"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityIsPassengerPredicate.java)

</details>

<details>
<summary>Can Be Collided With Predicate</summary>

The Can Be Collided With Predicate checks whether an entity can currently be collided with.

**Example of Can Be Collided With Predicate:**

```
{
  "type": "goalsmith:can_be_collided_with"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/CanBeCollidedWithPredicate.java)

</details>

<details>
<summary>Is Difficulty Predicate</summary>

The Is Difficulty Predicate checks whether the given difficulty matches the entity's difficulty.

**Example of Is Difficulty Predicate:**

```
{
  "difficulty": "easy",
  "type": "goalsmith:is_difficulty"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsDifficultyPredicate.java)

</details>

