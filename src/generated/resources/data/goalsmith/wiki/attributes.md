# attributes

### Introduction to Attributes

Attributes in Minecraft are used to define various properties and behaviors of entities, such as health, speed, and attack damage.

 Because some goals use attributes that an entity might not have you will need to add it.

### Example Usage

Let's consider an example where we add a custom attribute to an entity:

```
{
  "attribute_definitions": [
    {
      "attribute": "minecraft:generic.max_health",
      "value": 40.0
    }
  ]
}
```

In this example, the `AttributesAdditions` class is used to add a custom attribute (`MAX_HEALTH`) to an entity, setting its base value to 40.0. 

[Code](https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/AttributesAdditions.java)

## Available attributes

<details>
<summary>Attributes</summary>

The following attributes can be added to an entity

```
minecraft:generic.max_health
```

```
minecraft:generic.follow_range
```

```
minecraft:generic.knockback_resistance
```

```
minecraft:generic.movement_speed
```

```
minecraft:generic.flying_speed
```

```
minecraft:generic.attack_damage
```

```
minecraft:generic.attack_knockback
```

```
minecraft:generic.attack_speed
```

```
minecraft:generic.armor
```

```
minecraft:generic.armor_toughness
```

```
minecraft:generic.luck
```

```
minecraft:zombie.spawn_reinforcements
```

```
minecraft:horse.jump_strength
```

```
forge:swim_speed
```

```
forge:nametag_distance
```

```
forge:entity_gravity
```

```
forge:block_reach
```

```
forge:entity_reach
```

```
forge:step_height_addition
```

