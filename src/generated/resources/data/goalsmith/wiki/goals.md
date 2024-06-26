# goals

## Introduction to Goals

In this section, we introduce the concept of goals in the context of PathfinderMob behavior.

Goals define specific behaviors that entities (PathfinderMob instances) can perform. Each goal is assigned a priority, where lower numbers indicate higher priority.

Adding or replacing goals is achieved using goal operations, allowing entities to dynamically adjust their behavior.

## Goal Operations

Goal operations such as AddOperation and ReplaceOperation are used to modify the goals of PathfinderMob instances.

These operations accept various parameters depending on the specific goal type being added or replaced.

## Example: Water Flee Goal

The Water Flee Goal is an example of a specific behavior that entities can exhibit when encountering water.

It requires a goal priority to determine its precedence among other goals.

### WaterFleeGoalCodec

The WaterFleeGoalCodec implements the GoalCodec interface, which facilitates the integration of the WaterFleeGoal with PathfinderMob instances.

Parameters:

- `goal_priority`: Determines the priority of the WaterFleeGoal.

Usage:

The WaterFleeGoalCodec can be used with goal operations to add or replace this specific behavior in entities.

```
{
  "goal_priority": 1,
  "type": "goalsmith:water_flee_goal"
}
```

This Json demonstrates a water flee goal with priority 1

Currently a total of 59 Goals and a total of 6 Target Goals have been defined.

## Goals

<details>
<summary>Avoid Entity Goal</summary>

The Avoid Entity Goal causes the entity to avoid specific types of entities within a certain distance.

It provides options to define predicates for avoiding and interacting with the avoided entity.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `entity_class_to_avoid`: Tag key specifying the class of entities to avoid.
- `avoid_predicate`: Predicate determining which entities to avoid.
- `max_distance`: Maximum distance at which the entity will avoid the specified entities.
- `walk_speed_modifier`: Modifier applied to walking speed while avoiding.
- `sprint_speed_modifier`: Modifier applied to sprinting speed while avoiding.
- `predicate_on_avoid_entity`: Predicate determining conditions when the entity interacts with the avoided entity.

**Example of Avoid Entity Goal:**

```
{
  "max_distance": 10.0,
  "walk_speed_modifier": 0.8,
  "sprint_speed_modifier": 0.5,
  "predicate_on_avoid_entity": {
    "type": "goalsmith:no_creative_or_spectator"
  },
  "goal_priority": 1,
  "entity_class_to_avoid": "goalsmith:creeper",
  "avoid_predicate": {
    "type": "goalsmith:true"
  },
  "type": "goalsmith:avoid_entity_goal"
}
```

This example sets up an Avoid Entity Goal with specified parameters to avoid creepers and modify movement speeds.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/AvoidEntityGoalCodec.java)

</details>

<details>
<summary>Avoid Llama Goal</summary>

The Avoid Llama Goal causes the entity to avoid llamas within a certain distance.

It provides options to modify movement speeds while avoiding llamas.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `entity_class_to_avoid`: Tag key specifying the class of entities to avoid (llamas in this case).
- `max_distance`: Maximum distance at which the entity will avoid llamas.
- `walk_speed_modifier`: Modifier applied to walking speed while avoiding llamas.
- `sprint_speed_modifier`: Modifier applied to sprinting speed while avoiding llamas.

**Example of Avoid Llama Goal:**

```
{
  "max_distance": 12.0,
  "walk_speed_modifier": 0.75,
  "sprint_speed_modifier": 0.4,
  "goal_priority": 1,
  "entity_class_to_avoid": "goalsmith:llama",
  "type": "goalsmith:avoid_llama_goal"
}
```

This example sets up an Avoid Llama Goal with specified parameters to avoid llamas and modify movement speeds.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/AvoidLlamaGoalCodec.java)

</details>

<details>
<summary>Beg Goal</summary>

The Beg  Goal causes the entity to beg for a specific type of food item, such as food.

It allows the entity to perform behaviors related to obtaining the specified food item.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `interesting_food`: Tag key specifying the type of food item to beg for (e.g., fod food tag).
- `look_distance`: Distance at which the entity will look for the specified food item.

**Example of Beg Goal:**

```
{
  "goal_priority": 1,
  "interesting_food": "minecraft:fox_food",
  "look_distance": 10.0,
  "type": "goalsmith:beg_goal"
}
```

This example sets up a Beg Goal with specified parameters to beg for fox food.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BegGoalCodec.java)

</details>

<details>
<summary>Break Door Goal</summary>

The Break Door Goal causes the entity to attempt to break doors within a specified time frame under certain conditions.

It checks the difficulty level before attempting to break the door.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `door_break_time`: Time in ticks it takes for the entity to break a door.
- `difficulty_predicate`: Predicate that defines under which difficulty levels the entity will attempt to break doors.

**Example of Break Door Goal:**

```
{
  "goal_priority": 1,
  "door_break_time": 100,
  "difficulty_predicate": {
    "type": "goalsmith:true"
  },
  "type": "goalsmith:break_door_goal"
}
```

This example sets up a Break Door Goal with specified parameters to break doors.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreakDoorGoalCodec.java)

</details>

<details>
<summary>Breath Air Goal</summary>

The Breath Air Goal allows the entity to prioritize breathing air, typically used by aquatic mobs to surface and replenish their air.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Breath Air Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:breath_air_goal"
}
```

This example sets up a Breath Air Goal with a specified priority for an aquatic mob to surface and breathe.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreathAirGoalCodec.java)

</details>

<details>
<summary>Breed Goal</summary>

The Breed Goal allows animals to prioritize finding a suitable partner for breeding.

**Entries Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `possible_partner`: Tag key representing the type of entity that can be a breeding partner.
- `speed_modifier`: Speed modifier for movement towards the breeding partner.

**Example of Breed Goal:**

```
{
  "goal_priority": 1,
  "possible_partner": "goalsmith:cow",
  "speed_modifier": 1.2,
  "type": "goalsmith:breed_goal"
}
```

This example sets up a Breed Goal with a specified priority for an animal to find a suitable partner, based on the animal in the tag.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreedGoalCodec.java)

</details>

<details>
<summary>Climb On Top Of Powder Snow Goal</summary>

The Climb On Top Of Powder Snow Goal allows a mob to prioritize climbing on top of powder snow blocks.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Climb On Top Of Powder Snow Goal:**

```
{
  "goal_priority": 2,
  "type": "goalsmith:climb_on_top_of_powder_snow_goal"
}
```

This example sets up a Climb On Top Of Powder Snow Goal with a specified priority.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/ClimbOnTopOfPowderSnowGoalCodec.java)

</details>

<details>
<summary>Eat Block Goal</summary>

The Eat Block Goal allows a mob, such as a sheep, to prioritize eating a specific block, typically grass blocks.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Eat Block Goal:**

```
{
  "goal_priority": 3,
  "type": "goalsmith:eat_block_goal"
}
```

This example sets up an Eat Block Goal with a specified priority, commonly used for sheep to eat grass blocks.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/EatBlockGoalCodec.java)

</details>

<details>
<summary>Flee Sun Goal</summary>

The Flee Sun Goal allows a mob to prioritize fleeing from sunlight, adjusting its movement speed based on the provided speed modifier.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier to adjust the movement speed of the mob when fleeing from sunlight.

**Example of Flee Sun Goal:**

```
{
  "goal_priority": 4,
  "speed_modifier": -0.5,
  "type": "goalsmith:flee_sun_goal"
}
```

This example sets up a Flee Sun Goal with a specified priority and speed modifier, enabling mobs to prioritize avoiding sunlight.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FleeSunGoalCodec.java)

</details>

<details>
<summary>Float Goal</summary>

The Float Goal allows a mob to prioritize floating in water.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Float Goal:**

```
{
  "goal_priority": 3,
  "type": "goalsmith:float_goal"
}
```

This example sets up a Float Goal with a specified priority, enabling mobs to prioritize floating in water.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FloatGoalCodec.java)

</details>

<details>
<summary>Follow Boat Goal</summary>

The Follow Boat Goal enables a mob to prioritize following a boat.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Follow Boat Goal:**

```
{
  "goal_priority": 4,
  "type": "goalsmith:follow_boat_goal"
}
```

This example sets up a Follow Boat Goal with a specified priority, enabling mobs to prioritize following a boat.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowBoatGoalCodec.java)

</details>

<details>
<summary>Follow Flock Leader Goal</summary>

The Follow Flock Leader Goal enables a mob to prioritize following the leader of a flock, specifically designed for AbstractSchoolingFish.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Follow Flock Leader Goal:**

```
{
  "goal_priority": 5,
  "type": "goalsmith:follow_flock_leader_goal"
}
```

This example sets up a Follow Flock Leader Goal with a specified priority, enabling mobs, specifically AbstractSchoolingFish, to prioritize following the flock leader.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowFlockLeaderGoalCodec.java)

</details>

<details>
<summary>Follow Mob Goal</summary>

The Follow Mob Goal allows a mob to follow another mob within a specified area.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier for following.
- `stop_distance`: Distance at which the mob stops following.
- `area_size`: Size of the area within which the mob follows.

**Example of Follow Mob Goal:**

```
{
  "goal_priority": 5,
  "speed_modifier": 1.2,
  "stop_distance": 8.0,
  "area_size": 10.0,
  "type": "goalsmith:follow_mob_goal"
}
```

This example sets up a Follow Mob Goal with a specified priority, speed modifier, stop distance, and area size, allowing mobs to follow another mob within a defined area.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowMobGoalCodec.java)

</details>

<details>
<summary>Follow Owner Goal</summary>

The Follow Owner Goal allows a tamable animal to follow its owner within specified distances and conditions.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier for following.
- `start_distance`: Distance at which the following starts.
- `stop_distance`: Distance at which the mob stops following.
- `can_fly` (optional, default: false): Whether the mob can fly to follow the owner.

**Example of Follow Owner Goal:**

```
{
  "start_distance": 5.0,
  "stop_distance": 10.0,
  "can_fly": false,
  "goal_priority": 5,
  "speed_modifier": 1.2,
  "type": "goalsmith:follow_owner_goal"
}
```

This example sets up a Follow Owner Goal with a specified priority, speed modifier, start distance, stop distance, and flying capability, allowing tamable animals to follow their owner.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowOwnerGoalCodec.java)

</details>

<details>
<summary>Follow Parent Goal</summary>

The Follow Parent Goal allows an animal to follow its parent with a specified speed modifier.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier for following.

**Example of Follow Parent Goal:**

```
{
  "goal_priority": 5,
  "speed_modifier": 1.5,
  "type": "goalsmith:follow_parent_goal"
}
```

This example sets up a Follow Parent Goal with a specified priority and speed modifier, allowing an animal to follow its parent.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowParentGoalCodec.java)

</details>

<details>
<summary>Interact Goal</summary>

The Interact Goal allows a mob to interact with entities of a specific type within a certain distance and with defined probabilities.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `look_at_type`: Type of entity to interact with.
- `look_distance`: Maximum distance at which interaction can occur.
- `probability` (Optional, default: 0.02): Probability of initiating interaction.
- `only_horizontal` (Optional, default: false): Whether to restrict interaction to horizontal directions.

**Example of Interact Goal:**

```
{
  "look_distance": 10.0,
  "probability": 0.05,
  "only_horizontal": true,
  "goal_priority": 5,
  "look_at_type": "goalsmith:player",
  "type": "goalsmith:interact_goal"
}
```

This example sets up an Interact Goal with a specified priority, entity type to look at, look distance, probability, and only horizontal flag.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/InteractGoalCodec.java)

</details>

<details>
<summary>Jump Water Goal</summary>

The Jump Water Goal enables a mob to jump out of water at defined intervals.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `interval`: Interval in ticks between each jump out of water.

**Example of Jump Water Goal:**

```
{
  "goal_priority": 5,
  "interval": 40,
  "type": "goalsmith:jump_water_goal"
}
```

This example sets up a Jump Water Goal with a specified priority and interval, allowing the mob to jump out of water periodically.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/JumpWaterGoalCodec.java)

</details>

<details>
<summary>Land On Owner's Shoulder Goal</summary>

The Land On Owner's Shoulder Goal allows a mob, specifically a ShoulderRidingEntity, to attempt to land on its owner's shoulder.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Land On Owner's Shoulder Goal:**

```
{
  "goal_priority": 5,
  "type": "goalsmith:land_on_owners_shoulders_goal"
}
```

This example sets up a Land On Owner's Shoulder Goal with a specified priority, enabling a mob that implements ShoulderRidingEntity to attempt to land on its owner's shoulder.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LandOnOwnersShoulderGoalCodec.java)

</details>

<details>
<summary>Leap At Target Goal</summary>

The Leap At Target Goal allows a mob to leap towards a specific target position with a defined vertical movement.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `yd`: Vertical movement distance (Y-axis).

**Example of Leap At Target Goal:**

```
{
  "goal_priority": 3,
  "y_movement": 1.5,
  "type": "goalsmith:leap_at_target_goal"
}
```

This example sets up a Leap At Target Goal with a specified priority and vertical movement distance, enabling a mob to leap towards a target position.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LeapAtTargetGoalCodec.java)

</details>

<details>
<summary>Lie On Bed Goal</summary>

The Lie On Bed Goal enables a tamable animal to lie on a bed with specified behavior parameters.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier applied while lying on the bed.
- `search_range`: Range within which the bed should be searched for.

**Example of Lie On Bed Goal:**

```
{
  "goal_priority": 2,
  "speed_modifier": 0.5,
  "search_range": 5,
  "type": "goalsmith:lie_on_bed_goal"
}
```

This example sets up a Lie On Bed Goal with specified priority, speed modifier, and search range, allowing a tamable animal to lie on a bed with defined behavior.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LieOnBedGoalCodec.java)

</details>

<details>
<summary>Look At Entity Goal</summary>

The Look At Entity Goal enables a mob to look at entities of a specific type within a certain distance with defined behavior parameters.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `look_at_type`: Type of entity to look at, defined using a tag key.
- `look_distance`: Maximum distance at which the entity will look at the target entity.
- `probability`: (Optional) Probability of performing this goal.
- `only_horizontal`: (Optional) Whether the entity should look only horizontally at the target.

**Example of Look At Entity Goal:**

```
{
  "look_distance": 10.0,
  "probability": 0.05,
  "only_horizontal": false,
  "goal_priority": 3,
  "look_at_type": "goalsmith:player",
  "type": "goalsmith:look_at_entity_goal"
}
```

This example sets up a Look At Entity Goal with specified priority, entity type to look at, look distance, probability, and horizontal-only behavior, enabling a mob to perform actions based on the specified parameters.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LookAtEntityGoalCodec.java)

</details>

<details>
<summary>Look At Trading Player Goal</summary>

The Look At Trading Player Goal enables a villager to look at a trading player within a specified maximum distance.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `max_distance`: Maximum distance at which the villager will look at the trading player.

**Example of Look At Trading Player Goal:**

```
{
  "goal_priority": 2,
  "max_distance": 12.0,
  "type": "goalsmith:look_at_trading_player_goal"
}
```

This example sets up a Look At Trading Player Goal with specified priority and maximum distance, allowing a villager to interact with a trading player within a defined range.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LookAtTradingPlayerGoalCodec.java)

</details>

<details>
<summary>Melee Attack Goal</summary>

The Melee Attack Goal allows a mob to perform melee attacks on a target, with options to modify speed and behavior when the target is not visible.

**Entry Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of melee attacks.
- `following_target_even_if_not_seen`: Boolean indicating whether the mob should continue following the target even when not visible.

**Example of Melee Attack Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "following_target_even_if_not_seen": true,
  "type": "goalsmith:melee_attack_goal"
}
```

This example sets up a Melee Attack Goal with specified priority, speed modifier, and behavior regarding target visibility, allowing a mob to perform melee attacks.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MeleeAttackGoalCodec.java)

</details>

<details>
<summary>Move Back to Village Goal</summary>

The Move Back to Village Goal instructs a mob to return to its village, with options to modify movement speed and check for idle time.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of movement back to the village.
- `check_no_action_time`: Boolean indicating whether to check for idle time before moving back.

**Example of Move Back to Village Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.5,
  "check_no_action_time": true,
  "type": "goalsmith:move_back_to_village_goal"
}
```

This example sets up a Move Back to Village Goal with specified priority, speed modifier, and behavior regarding checking idle time before returning.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveBackToVillageGoalCodec.java)

</details>

<details>
<summary>Move Through Village Goal</summary>

The Move Through Village Goal directs a mob to move through a village environment, with options to modify movement speed, restrict to nighttime, and interact with doors.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of movement through the village.
- `only_at_night`: Boolean indicating whether the goal should be active only during nighttime.
- `distance_to_poi`: Distance to Points of Interest (POIs) within the village.
- `can_deal_with_doors`: Boolean indicating whether the mob can interact with doors.

**Example of Move Through Village Goal:**

```
{
  "only_at_night": true,
  "distance_to_poi": 5,
  "can_deal_with_doors": true,
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:move_through_village_goal"
}
```

This example sets up a Move Through Village Goal with specified parameters, adjusting movement speed, restricting to nighttime, setting a distance to POI, and allowing interaction with doors.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveThroughVillageGoalCodec.java)

</details>

<details>
<summary>Move To Block Goal</summary>

The Move To Block Goal directs a mob to move towards a specific block type within a defined search range, both horizontally and vertically if specified.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `block`: Type of block that the mob should move towards. Specified as TagKey
- `speed_modifier`: Modifier affecting the speed of movement towards the block.
- `search_range`: Horizontal distance within which to search for the specified block.
- `vertical_search_range`: Optional vertical distance within which to search for the specified block. Defaults to 1 if not provided.

**Example of Move To Block Goal:**

```
{
  "speed_modifier": 1.2,
  "search_range": 5,
  "vertical_search_range": 2,
  "goal_priority": 1,
  "block": "minecraft:sniffer_diggable_block",
  "type": "goalsmith:move_to_block_goal"
}
```

This example sets up a Move To Block Goal with specified parameters, directing a mob to move towards stone blocks within a certain range.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveToBlockGoalCodec.java)

</details>

<details>
<summary>Move Towards Restriction Goal</summary>

The Move Towards Restriction Goal instructs a mob to move towards a restricted area, typically defined within the mob's navigation constraints.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of movement towards the restricted area.

**Example of Move Towards Restriction Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:move_towards_restriction_goal"
}
```

This example sets up a Move Towards Restriction Goal with specified parameters, directing a mob to move towards a restricted area defined by its navigation constraints.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveTowardsRestrictionGoalCodec.java)

</details>

<details>
<summary>Move Towards Target Goal</summary>

The Move Towards Target Goal instructs a mob to move to its target within a defined distance radius.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of movement towards the target.
- `within`: Maximum distance radius within which the mob will attempt to move towards the target.

**Example of Move Towards Target Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "distance_radius": 10.0,
  "type": "goalsmith:move_towards_target_goal"
}
```

This example sets up a Move Towards Target Goal with specified parameters, directing a mob to move towards a target within a defined distance radius.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveTowardsTargetGoalCodec.java)

</details>

<details>
<summary>Offer Flower Goal</summary>

The Offer Flower Goal instructs a mob to offer a flower to another entity.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Offer Flower Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:offer_flower_goal"
}
```

This example sets up an Offer Flower Goal with a specified goal priority, instructing a mob to offer a flower to another entity.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/OfferFlowerGoalCodec.java)

</details>

<details>
<summary>Open Door Goal</summary>

The Open Door Goal instructs a mob to open or close a door.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `close_door`: Boolean flag indicating whether to close the door (`true`) or open it (`false`).

**Example of Open Door Goal:**

```
{
  "goal_priority": 1,
  "close_door": false,
  "type": "goalsmith:open_door_goal"
}
```

This example sets up an Open Door Goal with a specified goal priority and determines whether to open or close the door.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/OpenDoorGoalCodec.java)

</details>

<details>
<summary>Panic Goal</summary>

The Panic Goal causes a mob to enter a panic state under specific conditions.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier for movement speed during panic.
- `panic_predicate`: Predicate determining when the mob should panic. Default value includes checks for being attacked or freezing.

**Example of Panic Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "panic_predicate": {
    "predicate_1": {
      "predicate_1": {
        "predicate": {
          "type": "goalsmith:last_hurt_by_mob_is_null"
        },
        "type": "goalsmith:negate"
      },
      "predicate_2": {
        "type": "goalsmith:is_freezing"
      },
      "type": "goalsmith:or"
    },
    "predicate_2": {
      "type": "goalsmith:is_on_fire"
    },
    "type": "goalsmith:or"
  },
  "type": "goalsmith:panic_goal"
}
```

This example sets up a Panic Goal with a specified goal priority, speed modifier, and panic predicate.

**Default Value for Panic Predicate:**

The default panic predicate includes checks for whether the mob has been attacked, is freezing, or is on fire.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/PanicGoalCodec.java)

</details>

<details>
<summary>Pathfind To Raid Goal</summary>

The Pathfind To Raid Goal directs a mob to pathfind towards a raid event.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Pathfind To Raid Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:pathfind_to_raid_goal"
}
```

This example sets up a Pathfind To Raid Goal with a specified goal priority.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/PathfindToRaidGoalCodec.java)

</details>

<details>
<summary>Raise Arm Attack Goal</summary>

The Raise Arm Attack Goal directs an entity to raise its arm and attack.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier for the speed of the attack.
- `following_target_even_if_not_seen`: Whether the zombie will continue following the target even if not seen.

**Example of Raise Arm Attack Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "following_target_even_if_not_seen": true,
  "type": "goalsmith:raise_arm_attack_goal"
}
```

This example sets up a Raise Arm Attack Goal for a zombie with specified parameters.

**Used by:** Zombie, also known as ZombieAttackGoal

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RaiseArmAttackGoalCodec.java)

</details>

<details>
<summary>Random Look Around Goal</summary>

The Random Look Around Goal causes a mob to randomly look around.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Random Look Around Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:random_look_around_goal"
}
```

This example sets up a Random Look Around Goal for a mob with the specified goal priority.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomLookAroundGoalCodec.java)

</details>

<details>
<summary>Random Stroll Goal</summary>

The Random Stroll Goal causes a mob to randomly wander around.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier` (Optional): Speed modifier for the stroll. Defaults to `1.0D` if not specified.
- `interval` (Optional): Interval in ticks between strolls. Defaults to `120` if not specified.
- `check_no_action_time` (Optional): Whether to check for no action time. Defaults to `true` if not specified.

**Example of Random Stroll Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "interval": 40,
  "check_no_action_time": true,
  "type": "goalsmith:random_stroll_goal"
}
```

This example sets up a Random Stroll Goal with specified parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomStrollGoalCodec.java)

</details>

<details>
<summary>Random Stroll In Village Goal</summary>

The Random Stroll In Village Goal causes a mob to randomly wander around within the village boundaries.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier for the stroll. Adjusts the speed at which the mob moves. Defaults to `1.0D` if not specified.

**Example of Random Stroll In Village Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:random_stroll_in_village_goal"
}
```

This example sets up a Random Stroll In Village Goal with specified parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomStrollInVillageGoalCodec.java)

</details>

<details>
<summary>Random Swimming Goal</summary>

The Random Swimming Goal causes a mob to swim randomly around its environment.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier` (Optional): Speed modifier for the swimming. Defaults to `1.0D` if not specified.
- `interval` (Optional): Interval in ticks between swimming actions. Defaults to `120` if not specified.

**Example of Random Swimming Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "interval": 40,
  "type": "goalsmith:random_swimming_goal"
}
```

This example sets up a Random Swimming Goal with specified parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomSwimmingGoalCodec.java)

</details>

<details>
<summary>Ranged Attack Goal</summary>

The Ranged Attack Goal enables a mob to perform ranged attacks with specified parameters for speed, attack intervals, and attack radius.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier during the attack.
- `attack_interval_min`: Minimum interval in ticks between attacks.
- `attack_interval_max`: Maximum interval in ticks between attacks.
- `attack_radius`: The radius within which the mob can attack.

**Example of Ranged Attack Goal:**

```
{
  "attack_interval_min": 20,
  "attack_interval_max": 40,
  "attack_radius": 15.0,
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:ranged_attack_goal"
}
```

This example sets up a Ranged Attack Goal with specified parameters for a mob.

**Used by:** Mobs that implement the `RangedAttackMob` interface

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedAttackGoalCodec.java)

</details>

<details>
<summary>Ranged Bow Attack Goal</summary>

The Ranged Bow Attack Goal enables a mob to perform ranged bow attacks with specified parameters for speed, attack interval, and attack radius.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier during the attack.
- `attack_interval_min`: Minimum interval in ticks between attacks.
- `attack_radius`: The radius within which the mob can attack.

**Example of Ranged Bow Attack Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "attack_interval_min": 20,
  "attack_radius": 15.0,
  "type": "goalsmith:ranged_bow_attack_goal"
}
```

This example sets up a Ranged Bow Attack Goal with specified parameters for a mob.

**Used by:** Mobs that implement both the `Mob` and `RangedAttackMob` interfaces

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedBowAttackGoalCodec.java)

</details>

<details>
<summary>Ranged Crossbow Attack Goal</summary>

The Ranged Crossbow Attack Goal enables a mob to perform ranged crossbow attacks with specified parameters for speed and attack radius.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier during the attack.
- `attack_radius`: The radius within which the mob can attack.

**Example of Ranged Crossbow Attack Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "attack_radius": 15.0,
  "type": "goalsmith:ranged_crossbow_attack_goal"
}
```

This example sets up a Ranged Crossbow Attack Goal with specified parameters for a mob.

**Used by:** Mobs that implement the `Monster`, `RangedAttackMob`, and `CrossbowAttackMob` interfaces

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedCrossbowAttackGoalCodec.java)

</details>

<details>
<summary>Remove Block Goal</summary>

The Remove Block Goal enables a mob to remove specific blocks in its vicinity, with parameters for speed, search range, and block type.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `block_to_remove`: The type of block that the mob will attempt to remove. No tag just a block e.g. minecraft:grass_block
- `speed_modifier`: Speed modifier for the action of removing blocks.
- `search_range`: The range within which the mob will search for the specified block to remove.

**Example of Remove Block Goal:**

```
{
  "goal_priority": 1,
  "block_to_remove": "minecraft:dirt",
  "speed_modifier": 1.2,
  "search_range": 5,
  "type": "goalsmith:remove_block_goal"
}
```

This example sets up a Remove Block Goal with specified parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RemoveBlockGoalCodec.java)

</details>

<details>
<summary>Restrict Sun Goal</summary>

The Restrict Sun Goal prevents a mob from being exposed to sunlight, often to avoid damage or discomfort caused by sunlight.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Restrict Sun Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:restrict_sun_goal"
}
```

This example sets up a Restrict Sun Goal with the specified priority for a mob.

**Used by:** Mobs that need to avoid sunlight, typically for survival reasons such as avoiding damage from sunlight exposure

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RestrictSunGoalCodec.java)

</details>

<details>
<summary>Search For Items Goal</summary>

The Search For Items Goal enables a mob to search for specific items defined by a tag key. This goal can be used to simulate behaviors where mobs actively seek out certain items in their environment.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `allowed_items`: Tag key for items that the mob is allowed to search for.

**Example of Search For Items Goal:**

```
{
  "goal_priority": 1,
  "allowed_items": "minecraft:arrows",
  "type": "goalsmith:search_for_items_goal"
}
```

This example sets up a Search For Items Goal with arrows in a tag.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SearchForItemsGoalCodec.java)

</details>

<details>
<summary>Sit On Block Goal</summary>

The Sit On Block Goal allows tamable animals to sit on specified blocks with enhanced behavior.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Speed modifier for sitting on the block.

**Example of Sit On Block Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:sit_on_block_goal"
}
```

This example sets up a Sit On Block Goal with specified parameters for a tamable animal.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SitOnBlockGoalCodec.java)

</details>

<details>
<summary>Sit When Ordered to Goal</summary>

The Sit When Ordered to Goal allows tamable animals to sit down when ordered to do so.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Sit When Ordered to Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:sit_when_ordered_goal"
}
```

This example sets up a Sit When Ordered to Goal with a specified priority for a tamable animal.

**Used by:** Tamable animals that have the ability to sit down when they receive an order or command.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SitWhenOrderedToGoalCodec.java)

</details>

<details>
<summary>Stroll Through Village Goal</summary>

The Stroll Through Village Goal causes a mob to wander through a village.

**Parameters Needed:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `interval`: Interval in ticks between strolls.

**Example of Stroll Through Village Goal:**

```
{
  "goal_priority": 1,
  "interval": 40,
  "type": "goalsmith:stroll_through_village_goal"
}
```

This example sets up a Stroll Through Village Goal with specified parameters for a mob.

**Used by:** Mobs that are intended to wander through villages in the game environment.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/StrollThroughVillageGoalCodec.java)

</details>

<details>
<summary>Tempt Goal</summary>

The Tempt Goal entices a mob to follow and interact with specified items.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier applied to the mob's movement speed while tempted.
- `items`: Tag of items that can tempt the mob.
- `can_scare`: Whether the mob can be scared away from the tempting item.

**Example of Tempt Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "items": "minecraft:fox_food",
  "can_scare": true,
  "type": "goalsmith:tempt_goal"
}
```

This example sets up a Tempt Goal with specified parameters for a mob.

**Used by:** Mobs that can be tempted by items, in the example fox food, such as animals following food or other enticing objects.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TemptGoalCodec.java)

</details>

<details>
<summary>Trade With Player Goal</summary>

The Trade With Player Goal enables an AbstractVillager to engage in trading interactions with players.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Trade With Player Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:trade_with_player_goal"
}
```

This example sets up a Trade With Player Goal with a specified priority for an AbstractVillager.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TradeWithPlayerGoalCodec.java)

</details>

<details>
<summary>Try Find Water Goal</summary>

The Try Find Water Goal allows a mob to actively seek out nearby water sources for hydration or other purposes.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.

**Example of Try Find Water Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:try_find_water_goal"
}
```

This example demonstrates how to set up a Try Find Water Goal with a specified priority for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TryFindWaterGoalCodec.java)

</details>

<details>
<summary>Use Item Goal</summary>

The Use Item Goal allows a mob to use a specific item under certain conditions.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `item`: The item that the mob will use.
- `finish_using_sound`: The sound played when the mob finishes using the item.
- `can_use_predicate`: Predicate determining when the mob can use the item.

**Example of Use Item Goal:**

```
{
  "goal_priority": 1,
  "item": {
    "id": "minecraft:cake",
    "Count": 1
  },
  "finish_using_sound": "minecraft:entity.player.burp",
  "can_use_predicate": {
    "type": "goalsmith:is_night"
  },
  "type": "goalsmith:use_item_goal"
}
```

This example demonstrates how to set up a Use Item Goal with specific parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/UseItemGoalCodec.java)

</details>

<details>
<summary>Water Avoiding Random Flying Goal</summary>

The Water Avoiding Random Flying Goal enables a mob to perform random flying movements while avoiding water.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier that affects the speed of the flying movement.

**Example of Water Avoiding Random Flying Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "type": "goalsmith:water_avoiding_random_flying_goal"
}
```

This example demonstrates how to set up a Water Avoiding Random Flying Goal with specific parameters for a mob.

**Used by:** Mobs that have the ability to fly and need to avoid water bodies in their movement.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterAvoidingRandomFlyingGoalCodec.java)

</details>

<details>
<summary>Water Avoiding Random Stroll Goal</summary>

The Water Avoiding Random Stroll Goal allows a mob to perform random walking movements while avoiding water.

**Parameters Required:**

- `goal_priority`: Priority of the goal. Lower numbers indicate higher priority.
- `speed_modifier`: Modifier affecting the speed of the walking movement.
- `probability`: Optional parameter specifying the probability of performing the random stroll.

**Example of Water Avoiding Random Stroll Goal:**

```
{
  "goal_priority": 1,
  "speed_modifier": 1.2,
  "probability": 0.05,
  "type": "goalsmith:water_avoiding_random_stroll_goal"
}
```

This example demonstrates how to set up a Water Avoiding Random Stroll Goal with specific parameters for a mob.

**Used by:** Mobs that need to perform random walking movements while avoiding water bodies in their path.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterAvoidingRandomStrollGoalCodec.java)

</details>

<details>
<summary>Water Flee Goal</summary>

The Water Flee Goal enables a mob to execute evasive actions when near water, facilitating escape from potentially dangerous situations involving water.

**Parameters Required:**

- `goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.

**Example Usage of Water Flee Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:water_flee_goal"
}
```

This example demonstrates how to configure a Water Flee Goal with specific parameters for a mob.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterFleeGoalCodec.java)

</details>

## Target Goals

<details>
<summary>Defend Village Target Goal</summary>

The Defend Village Target Goal instructs a mob to prioritize defending a village when threats are detected, ensuring the safety of the village and its inhabitants.

**Parameters Required:**

- `goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.

**Example Usage of Defend Village Target Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:defend_village_target_goal"
}
```

This example demonstrates how to configure a Defend Village Target Goal with specific parameters for a mob.

**Applied to:** Mobs that are tasked with defending villages from threats, ensuring the safety of villagers and structures within the village.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/DefendVillageTargetGoalCodec.java)

</details>

<details>
<summary>Hurt By Target Goal</summary>

The Hurt By Target Goal enables a mob to prioritize attacking entities that have inflicted harm on it, ignoring specified entity types.

**Parameters Required:**

- `target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.
- `to_ignore`: Defines the types of entities that the mob should ignore when selecting targets. Defined as TagKey

**Example Usage of Hurt By Target Goal:**

```
{
  "target_goal_priority": 1,
  "to_ignore": "goalsmith:player",
  "type": "goalsmith:hurt_by_target_goal"
}
```

This example demonstrates how to configure a Hurt By Target Goal with specific parameters for a mob.

**Applied to:** Mobs that prioritize retaliating against entities that have harmed them, while ignoring specific types of entities.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/HurtByTargetGoalCodec.java)

</details>

<details>
<summary>Nearest Attackable Target Goal</summary>

The Nearest Attackable Target Goal allows a mob to select the nearest living entity of a specified type as its target, with optional conditions.

**Parameters Required:**

- `target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.
- `target_type`: Specifies the type of entity that the mob will target.
- `must_see`: Determines if the mob must have line-of-sight to the target.

**Optional Parameters:**

- `random_interval`: Defines the interval (in ticks) at which the mob re-evaluates its target selection randomly. Default is 10 ticks.
- `must_reach`: Specifies whether the mob must be able to pathfind to the target entity.
- `target_predicate`: Allows customization of the target selection criteria using a predicate.

**Example Usage of Nearest Attackable Target Goal:**

```
{
  "must_see": true,
  "must_reach": false,
  "target_predicate": {
    "type": "goalsmith:true"
  },
  "target_goal_priority": 1,
  "target_type": "goalsmith:player",
  "random_interval": 15,
  "type": "goalsmith:nearest_attackable_target_goal"
}
```

This example demonstrates how to configure a Nearest Attackable Target Goal with specific parameters for a mob.

**Applied to:** Mobs that prioritize targeting the nearest living entity of a specified type, based on defined parameters.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/NearestAttackableTargetGoalCodec.java)

</details>

<details>
<summary>Non-Tame Random Target Goal</summary>

The Non-Tame Random Target Goal allows tamable animals to randomly select nearby entities of a specified type as their target, with optional conditions.

**Parameters Required:**

- `target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.
- `target_type`: Specifies the type of entity that the tamable animal will randomly target.
- `must_see`: Determines if the tamable animal must have line-of-sight to the target.

**Optional Parameters:**

- `random_interval`: Defines the interval (in ticks) at which the tamable animal re-evaluates its target selection randomly. Default is 10 ticks.
- `must_reach`: Specifies whether the tamable animal must be able to pathfind to the target entity.
- `target_predicate`: Allows customization of the target selection criteria using a predicate.

**Example Usage of Non-Tame Random Target Goal:**

```
{
  "must_see": true,
  "must_reach": false,
  "target_predicate": {
    "type": "goalsmith:true"
  },
  "target_goal_priority": 1,
  "target_type": "goalsmith:player",
  "random_interval": 15,
  "type": "goalsmith:non_tame_random_target_goal"
}
```

This example demonstrates how to configure a Non-Tame Random Target Goal with specific parameters for a tamable animal.

**Applied to:** Tamable animals that randomly select nearby entities of a specified type as targets, based on defined parameters.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/NonTameRandomTargetGoalCodec.java)

</details>

<details>
<summary>Owner Hurt By Target Goal</summary>

The Owner Hurt By Target Goal enables tamable animals to prioritize attacking entities that have harmed their owner.

**Parameters Required:**

- `goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.

**Example Usage of Owner Hurt By Target Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:owner_hurt_by_target_goal"
}
```

This example demonstrates how to configure an Owner Hurt By Target Goal with a specific priority for a tamable animal.

**Applied to:** Tamable animals that prioritize retaliating against entities that have harmed their owner.

[Source Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/OwnerHurtByTargetGoalCodec.java)

</details>

<details>
<summary>Owner Hurt Target Goal</summary>

The Owner Hurt Target Goal enables tamable animals to prioritize attacking entities that have hurt their owner.

**Parameters Required:**

- `goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.

**Example Usage of Owner Hurt Target Goal:**

```
{
  "goal_priority": 1,
  "type": "goalsmith:owner_hurt_target_goal"
}
```

This example demonstrates how to configure an Owner Hurt Target Goal with a specific priority for a tamable animal.

**Applied to:** Tamable animals that prioritize retaliating against entities that have hurt their owner.

[Source Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/OwnerHurtTargetGoalCodec.java)

</details>

## Currently Defined Entity Tags

<details>
<summary>Tags</summary>

```
goalsmith:allay
```

With value minecraft:allay

```
goalsmith:area_effect_cloud
```

With value minecraft:area_effect_cloud

```
goalsmith:armor_stand
```

With value minecraft:armor_stand

```
goalsmith:arrow
```

With value minecraft:arrow

```
goalsmith:axolotl
```

With value minecraft:axolotl

```
goalsmith:bat
```

With value minecraft:bat

```
goalsmith:bee
```

With value minecraft:bee

```
goalsmith:blaze
```

With value minecraft:blaze

```
goalsmith:block_display
```

With value minecraft:block_display

```
goalsmith:boat
```

With value minecraft:boat

```
goalsmith:camel
```

With value minecraft:camel

```
goalsmith:cat
```

With value minecraft:cat

```
goalsmith:cave_spider
```

With value minecraft:cave_spider

```
goalsmith:chest_boat
```

With value minecraft:chest_boat

```
goalsmith:chest_minecart
```

With value minecraft:chest_minecart

```
goalsmith:chicken
```

With value minecraft:chicken

```
goalsmith:cod
```

With value minecraft:cod

```
goalsmith:command_block_minecart
```

With value minecraft:command_block_minecart

```
goalsmith:cow
```

With value minecraft:cow

```
goalsmith:creeper
```

With value minecraft:creeper

```
goalsmith:dolphin
```

With value minecraft:dolphin

```
goalsmith:donkey
```

With value minecraft:donkey

```
goalsmith:dragon_fireball
```

With value minecraft:dragon_fireball

```
goalsmith:drowned
```

With value minecraft:drowned

```
goalsmith:egg
```

With value minecraft:egg

```
goalsmith:elder_guardian
```

With value minecraft:elder_guardian

```
goalsmith:end_crystal
```

With value minecraft:end_crystal

```
goalsmith:ender_dragon
```

With value minecraft:ender_dragon

```
goalsmith:ender_pearl
```

With value minecraft:ender_pearl

```
goalsmith:enderman
```

With value minecraft:enderman

```
goalsmith:endermite
```

With value minecraft:endermite

```
goalsmith:evoker
```

With value minecraft:evoker

```
goalsmith:evoker_fangs
```

With value minecraft:evoker_fangs

```
goalsmith:experience_bottle
```

With value minecraft:experience_bottle

```
goalsmith:experience_orb
```

With value minecraft:experience_orb

```
goalsmith:eye_of_ender
```

With value minecraft:eye_of_ender

```
goalsmith:falling_block
```

With value minecraft:falling_block

```
goalsmith:firework_rocket
```

With value minecraft:firework_rocket

```
goalsmith:fox
```

With value minecraft:fox

```
goalsmith:frog
```

With value minecraft:frog

```
goalsmith:furnace_minecart
```

With value minecraft:furnace_minecart

```
goalsmith:ghast
```

With value minecraft:ghast

```
goalsmith:giant
```

With value minecraft:giant

```
goalsmith:glow_item_frame
```

With value minecraft:glow_item_frame

```
goalsmith:glow_squid
```

With value minecraft:glow_squid

```
goalsmith:goat
```

With value minecraft:goat

```
goalsmith:guardian
```

With value minecraft:guardian

```
goalsmith:hoglin
```

With value minecraft:hoglin

```
goalsmith:hopper_minecart
```

With value minecraft:hopper_minecart

```
goalsmith:horse
```

With value minecraft:horse

```
goalsmith:husk
```

With value minecraft:husk

```
goalsmith:illusioner
```

With value minecraft:illusioner

```
goalsmith:interaction
```

With value minecraft:interaction

```
goalsmith:iron_golem
```

With value minecraft:iron_golem

```
goalsmith:item
```

With value minecraft:item

```
goalsmith:item_display
```

With value minecraft:item_display

```
goalsmith:item_frame
```

With value minecraft:item_frame

```
goalsmith:fireball
```

With value minecraft:fireball

```
goalsmith:leash_knot
```

With value minecraft:leash_knot

```
goalsmith:lightning_bolt
```

With value minecraft:lightning_bolt

```
goalsmith:llama
```

With value minecraft:llama

```
goalsmith:llama_spit
```

With value minecraft:llama_spit

```
goalsmith:magma_cube
```

With value minecraft:magma_cube

```
goalsmith:marker
```

With value minecraft:marker

```
goalsmith:minecart
```

With value minecraft:minecart

```
goalsmith:mooshroom
```

With value minecraft:mooshroom

```
goalsmith:mule
```

With value minecraft:mule

```
goalsmith:ocelot
```

With value minecraft:ocelot

```
goalsmith:painting
```

With value minecraft:painting

```
goalsmith:panda
```

With value minecraft:panda

```
goalsmith:parrot
```

With value minecraft:parrot

```
goalsmith:phantom
```

With value minecraft:phantom

```
goalsmith:pig
```

With value minecraft:pig

```
goalsmith:piglin
```

With value minecraft:piglin

```
goalsmith:piglin_brute
```

With value minecraft:piglin_brute

```
goalsmith:pillager
```

With value minecraft:pillager

```
goalsmith:polar_bear
```

With value minecraft:polar_bear

```
goalsmith:potion
```

With value minecraft:potion

```
goalsmith:pufferfish
```

With value minecraft:pufferfish

```
goalsmith:rabbit
```

With value minecraft:rabbit

```
goalsmith:ravager
```

With value minecraft:ravager

```
goalsmith:salmon
```

With value minecraft:salmon

```
goalsmith:sheep
```

With value minecraft:sheep

```
goalsmith:shulker
```

With value minecraft:shulker

```
goalsmith:shulker_bullet
```

With value minecraft:shulker_bullet

```
goalsmith:silverfish
```

With value minecraft:silverfish

```
goalsmith:skeleton
```

With value minecraft:skeleton

```
goalsmith:skeleton_horse
```

With value minecraft:skeleton_horse

```
goalsmith:slime
```

With value minecraft:slime

```
goalsmith:small_fireball
```

With value minecraft:small_fireball

```
goalsmith:sniffer
```

With value minecraft:sniffer

```
goalsmith:snow_golem
```

With value minecraft:snow_golem

```
goalsmith:snowball
```

With value minecraft:snowball

```
goalsmith:spawner_minecart
```

With value minecraft:spawner_minecart

```
goalsmith:spectral_arrow
```

With value minecraft:spectral_arrow

```
goalsmith:spider
```

With value minecraft:spider

```
goalsmith:squid
```

With value minecraft:squid

```
goalsmith:stray
```

With value minecraft:stray

```
goalsmith:strider
```

With value minecraft:strider

```
goalsmith:tadpole
```

With value minecraft:tadpole

```
goalsmith:text_display
```

With value minecraft:text_display

```
goalsmith:tnt
```

With value minecraft:tnt

```
goalsmith:tnt_minecart
```

With value minecraft:tnt_minecart

```
goalsmith:trader_llama
```

With value minecraft:trader_llama

```
goalsmith:trident
```

With value minecraft:trident

```
goalsmith:tropical_fish
```

With value minecraft:tropical_fish

```
goalsmith:turtle
```

With value minecraft:turtle

```
goalsmith:vex
```

With value minecraft:vex

```
goalsmith:villager
```

With value minecraft:villager

```
goalsmith:vindicator
```

With value minecraft:vindicator

```
goalsmith:wandering_trader
```

With value minecraft:wandering_trader

```
goalsmith:warden
```

With value minecraft:warden

```
goalsmith:witch
```

With value minecraft:witch

```
goalsmith:wither
```

With value minecraft:wither

```
goalsmith:wither_skeleton
```

With value minecraft:wither_skeleton

```
goalsmith:wither_skull
```

With value minecraft:wither_skull

```
goalsmith:wolf
```

With value minecraft:wolf

```
goalsmith:zoglin
```

With value minecraft:zoglin

```
goalsmith:zombie
```

With value minecraft:zombie

```
goalsmith:zombie_horse
```

With value minecraft:zombie_horse

```
goalsmith:zombie_villager
```

With value minecraft:zombie_villager

```
goalsmith:zombified_piglin
```

With value minecraft:zombified_piglin

```
goalsmith:player
```

With value minecraft:player

```
goalsmith:fishing_bobber
```

With value minecraft:fishing_bobber

</details>

