# Operations

### Introduction to Operations

Operations in this context refer to the goals performed by entities within the Minecraft.These operations are able to change the goals of entities by adding, removing or replacing the goals an entity currently has.

#### Example Usage

Let's consider an example using the AddOperation:

```
{
  "goal": [
    {
      "goal_priority": 1,
      "type": "goalsmith:sit_when_ordered_goal"
    }
  ],
  "type": "goalsmith:add_goal_operation"
}
```

In this example, the AddOperation adds a new goal to the entity with the specified priority.

A total of 5 Goal Operations and 5 Target Goal Operations are defined.

## Goal Operations

<details>
<summary>Add Operation</summary>

The Add Operation adds a new goal to the entity with the specified priority.

**Example of Add Operation:**

```
{
  "goal": [
    {
      "goal_priority": 1,
      "type": "goalsmith:sit_when_ordered_goal"
    }
  ],
  "type": "goalsmith:add_goal_operation"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/AddOperation.java)

</details>

<details>
<summary>Remove All Operation</summary>

The Remove All Operation removes all goals from the entity.

**Example of Remove All Operation:**

```
{
  "type": "goalsmith:remove_all_goal_operation"
}
```

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveAllOperation.java)

</details>

<details>
<summary>Remove Specific Operation</summary>

The Remove Specific Operation removes goals from the entity that match a specific replacement goal.

It checks for goals with the specified priority and removes those whose goal class matches the specified type.

**Example of Remove Specific Operation:**

```
{
  "goal_to_remove": {
    "goal_priority": 1,
    "goal": "minecraft:sit_when_ordered_to_goal"
  },
  "type": "goalsmith:remove_specific_goal_operation"
}
```

This Json checks for a priority for 1 in a specified entity and then checks if the 'SitWhenOrderedToGoal' is in there and then removes it

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveSpecificOperation.java)

</details>

<details>
<summary>Remove Specific Priority Operation</summary>

The Remove Specific Priority Operation removes goals from the entity that match a specific priority.

It removes goals from the entity's goal selector that have the specified priority value.

**Example of Remove Specific Priority Operation:**

```
{
  "goal_priority_to_remove": 1,
  "type": "goalsmith:remove_specific_priority_operation"
}
```

This Json demonstrates removing goals with priority 1 from the entity's goal selector.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveSpecificPriorityOperation.java)

</details>

<details>
<summary>Replace Operation</summary>

The Replace Operation replaces a goal in the entity's goal selector with a new goal.

It first removes goals that match a specific replacement goal, then adds a new goal using a specified goal codec.

**Example of Replace Operation:**

```
{
  "goal_to_replace": {
    "goal_priority": 1,
    "goal": "minecraft:sit_when_ordered_to_goal"
  },
  "replacement_goal": {
    "goal_priority": 1,
    "type": "goalsmith:sit_when_ordered_goal"
  },
  "type": "goalsmith:replace_goal_operation"
}
```

This Json demonstrates replacing goals with priority 1 and matching `SitWhenOrderedToGoal` with a new goal, in this case a new SitWhenOrderedGoal.

Note: one is in the `minecraft` namespace and one in the `goalsmith` namespace, all minecraft goals to be removed should always be in the minecraft namespace.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/ReplaceOperation.java)

</details>

## Target Goal Operations

<details>
<summary>Add Target Operation</summary>

The Add Target Operation adds target goals to the entity.

It applies target goal to add specific target goals to the entity.

**Example of Add Target Operation:**

```
{
  "target_goal": [
    {
      "goal_priority": 1,
      "type": "goalsmith:defend_village_target_goal"
    }
  ],
  "type": "goalsmith:add_target_goal_operation"
}
```

This Json demonstrates adding a target goal using a specified target goal codec. This one being the 'DefendVillageTargetGoalCodec' with a priority of 1

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/AddTargetOperation.java)

</details>

<details>
<summary>Remove All Target Operation</summary>

The Remove All Target Operation removes all target goals from the entity.

It clears the entity's target selector of all existing target goals.

**Example of Remove All Target Operation:**

```
{
  "type": "goalsmith:remove_all_target_goal_operation"
}
```

This Json demonstrates removing all target goals from the entity's target selector.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveAllTargetOperation.java)

</details>

<details>
<summary>Remove Specific Target Operation</summary>

The Remove Specific Target Operation removes specific target goals from the entity.

It checks for target goals with the specified priority and removes those whose goal class matches the specified type.

**Example of Remove Specific Target Operation:**

```
{
  "target_goal_to_remove": {
    "target_goal_priority": 1,
    "target_goal": "minecraft:defend_village_target_goal"
  },
  "type": "goalsmith:remove_specific_target_goal_operation"
}
```

This Json demonstrates removing target goals with priority 1 and matching``DefendVillageTargetGoal` from the entity.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveSpecificTargetOperation.java)

</details>

<details>
<summary>Remove Specific Target Priority Operation</summary>

The Remove Specific Target Priority Operation removes target goals from the entity based on a specific priority.

It filters and removes target goals that match the specified priority.

**Example of Remove Specific Target Priority Operation:**

```
{
  "target_goal_priority_to_remove": 1,
  "type": "goalsmith:remove_specific_target_priority_operation"
}
```

This JSON-encoded data demonstrates removing target goals with priority 1 from the entity.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveSpecificTargetPriorityOperation.java)

</details>

<details>
<summary>Replace Target Operation</summary>

The Replace Target Operation replaces existing target goals with a new target goal.

It removes target goals that match a specific criteria and adds a new target goal as replacement.

**Example of Replace Target Operation:**

```
{
  "target_goal_to_replace": {
    "goal_priority": 1,
    "target_goal": "minecraft:defend_village_target_goal"
  },
  "replacement_target_goal": {
    "goal_priority": 1,
    "type": "goalsmith:defend_village_target_goal"
  },
  "type": "goalsmith:replace_target_goal_operation"
}
```

This Json demonstrates replacing target goals with priority 1 and matching `DefendVillageTargetGoal` with a new target goal. This case it being a `DefendVillageTargetGoal`

Note: one is in the `minecraft` namespace and one in the `goalsmith` namespace, all minecraft goals to be removed should always be in the minecraft namespace.

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/ReplaceTargetOperation.java)

</details>

## Replaceable and Removable Classes

<details>
<summary>Replaceable and Removable Goals</summary>

```
minecraft:avoid_entity_goal
```

```
minecraft:beg_goal
```

```
minecraft:break_door_goal
```

```
minecraft:breath_air_goal
```

```
minecraft:breed_goal
```

```
minecraft:cat_lie_on_bed_goal
```

```
minecraft:cat_sit_on_block_goal
```

```
minecraft:climb_on_top_of_powder_snow_goal
```

```
minecraft:dolphin_jump_goal
```

```
minecraft:door_interact_goal
```

```
minecraft:eat_block_goal
```

```
minecraft:flee_sun_goal
```

```
minecraft:float_goal
```

```
minecraft:follow_boat_goal
```

```
minecraft:follow_flock_leader_goal
```

```
minecraft:follow_mob_goal
```

```
minecraft:follow_owner_goal
```

```
minecraft:follow_parent_goal
```

```
minecraft:goal_goal
```

```
minecraft:golem_random_stroll_in_village_goal
```

```
minecraft:interact_goal
```

```
minecraft:jump_goal
```

```
minecraft:land_on_owners_shoulder_goal
```

```
minecraft:leap_at_target_goal
```

```
minecraft:llama_follow_caravan_goal
```

```
minecraft:look_at_player_goal
```

```
minecraft:look_at_trading_player_goal
```

```
minecraft:melee_attack_goal
```

```
minecraft:move_back_to_village_goal
```

```
minecraft:move_through_village_goal
```

```
minecraft:move_to_block_goal
```

```
minecraft:move_towards_restriction_goal
```

```
minecraft:move_towards_target_goal
```

```
minecraft:ocelot_attack_goal
```

```
minecraft:offer_flower_goal
```

```
minecraft:open_door_goal
```

```
minecraft:panic_goal
```

```
minecraft:pathfind_to_raid_goal
```

```
minecraft:random_look_around_goal
```

```
minecraft:random_stand_goal
```

```
minecraft:random_stroll_goal
```

```
minecraft:random_swimming_goal
```

```
minecraft:ranged_attack_goal
```

```
minecraft:ranged_bow_attack_goal
```

```
minecraft:ranged_crossbow_attack_goal
```

```
minecraft:remove_block_goal
```

```
minecraft:restrict_sun_goal
```

```
minecraft:run_around_like_crazy_goal
```

```
minecraft:sit_when_ordered_to_goal
```

```
minecraft:stroll_through_village_goal
```

```
minecraft:swell_goal
```

```
minecraft:tempt_goal
```

```
minecraft:trade_with_player_goal
```

```
minecraft:try_find_water_goal
```

```
minecraft:use_item_goal
```

```
minecraft:water_avoiding_random_flying_goal
```

```
minecraft:water_avoiding_random_stroll_goal
```

```
minecraft:zombie_attack_goal
```

</details>

<details>
<summary>Replaceable and Removable Target Goals</summary>

```
minecraft:defend_village_target_goal
```

```
minecraft:hurt_by_target_goal
```

```
minecraft:nearest_attackable_target_goal
```

```
minecraft:nearest_attackable_witch_target_goal
```

```
minecraft:nearest_attackable_healable_raider_target_goal
```

```
minecraft:non_tame_random_target_goal
```

```
minecraft:owner_hurt_by_target_goal
```

```
minecraft:owner_hurt_target_goal
```

</details>

