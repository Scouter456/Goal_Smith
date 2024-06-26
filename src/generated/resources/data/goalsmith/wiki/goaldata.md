# goal

<details>
<summary>Goal Data</summary>

The Goal Data record defines the entity to target and specifies the operations for adding or modifying the entity's goals and attributes.

**Parameters Required:**

-  `target_entity`: The entity type to which the goals and attributes will be applied, identified by its resource location.
-  `goal_operations`:(optional value) A list of operations that specify the goals to add or modify.
-  `target_goal_operations`:(optional value) A list of operations that specify the target goals to add or modify.
-  `attributes_additions`:(optional value) A list of attribute additions to apply to the target entity.

**Example Usage of Goal Data:**

```
{
  "target_entity": "minecraft:cow",
  "goal_operations": [
    {
      "goal": [
        {
          "goal_priority": 1,
          "type": "goalsmith:water_flee_goal"
        }
      ],
      "type": "goalsmith:add_goal_operation"
    }
  ],
  "target_goal_operations": [
    {
      "target_goal": [
        {
          "must_see": true,
          "must_reach": false,
          "target_predicate": {
            "type": "goalsmith:true"
          },
          "target_goal_priority": 1,
          "target_type": "goalsmith:player",
          "random_interval": 10,
          "type": "goalsmith:nearest_attackable_target_goal"
        }
      ],
      "type": "goalsmith:add_target_goal_operation"
    }
  ],
  "attributes_additions": [
    {
      "attribute_definitions": [
        {
          "attribute": "minecraft:generic.attack_damage",
          "value": 10.0
        }
      ]
    }
  ]
}
```

This example demonstrates how to configure Goal Data with specific parameters for targeting an entity and modifying its goals and attributes.

**Applied to:** Entities that require custom goal and attribute configurations based on the specified operations and additions.

The Json file should be in the following location: `data/<ID>/goalsmith/goaldata`

[Source Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/GoalData.java)

</details>

