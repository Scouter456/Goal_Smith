package com.scouter.goalsmith.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.data.goalcodec.*;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.*;
import com.scouter.goalsmith.data.operation.goal.*;
import com.scouter.goalsmith.data.operation.target.*;
import com.scouter.goalsmith.data.predicates.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class WikiPageGenerator extends WikiPageBuilderProvider{
    public WikiPageGenerator(PackOutput output) {
        super(output, "wiki", GoalSmith.MODID);
    }
    private final List<WikiPageBuilder> pageBuilderList = new ArrayList<>();
    private final TableOfContentsGenerator contentsGenerator = new TableOfContentsGenerator();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Override
    protected void generateWikiPages(BiConsumer<String, Supplier<String>> consumer) {
        // Define your goal operations
        List<GoalOperation> goalOperations = new ArrayList<>();
        ReplaceOperation replaceOperation = new ReplaceOperation(new ReplaceOperation.ReplacementGoal(1, PanicGoal.class),
                new MeleeAttackGoalCodec(1, 4, true));
        goalOperations.add(replaceOperation);

        // Define your target goal operations
        List<TargetGoalOperation> targetGoalOperations = new ArrayList<>();
        NearestAttackableTargetGoalCodec nearestAttackableTargetGoal = new NearestAttackableTargetGoalCodec(1,
                TagKey.create(Registries.ENTITY_TYPE, prefix("player")), 10, true, false, new TruePredicate<>());
        AddTargetOperation addTargetOperation = new AddTargetOperation(List.of(nearestAttackableTargetGoal));
        targetGoalOperations.add(addTargetOperation);

        // Define your attributes additions
        List<AttributesAdditions> attributeAdditions = new ArrayList<>();
        List<AttributesAdditions.AttributesMap> attributesMaps = new ArrayList<>();
        AttributesAdditions.AttributesMap attm = new AttributesAdditions.AttributesMap(Attributes.ATTACK_DAMAGE, 10);
        attributesMaps.add(attm);
        AttributesAdditions attributesAdditions = new AttributesAdditions(attributesMaps);
        attributeAdditions.add(attributesAdditions);

        // Create the GoalData object
        GoalData entityData = new GoalData(new ResourceLocation("minecraft:cow"), goalOperations, targetGoalOperations, attributeAdditions);
        String jsonString = encodeDataToJsonString(GoalData.CODEC, entityData);

        // Create the home page content


        // Create the wiki page and add it to the generator
        createWikiPage("home", homePage(), consumer);
        createWikiPage("predicate", predicatePage(), consumer);
        createWikiPage("operations", operationsPage(), consumer);
        createWikiPage("goals", goalPage(), consumer);
        createWikiPage("attributes", attributesPage(), consumer);
        createWikiPage("goaldata", goalDataPage(), consumer);

        // Generate the Table of Contents
        contentsGenerator.addPage(homePage());
    }


    private WikiPageBuilder homePage() {
        WikiPageBuilder builder = new WikiPageBuilder("Home");

        builder.addParagraph("Welcome to the Goal Smith Wiki!");
        builder.addParagraph("Goal Smith is a powerful Minecraft mod that allows players to customize the behavior of mobs by adding, removing, or modifying their AI goals. Whether you want to create a mob that prioritizes attacking certain entities, configure specific target goals, or adjust mob attributes, Goal Smith provides the tools to achieve this. Explore the various features and capabilities of Goal Smith through this comprehensive wiki.");

        builder.addParagraph("**Key Features:**");
        builder.addList(new String[]{
                "Add, remove, or modify mob AI goals.",
                "Customize mob behavior with target-specific goals.",
                "Adjust mob attributes to fine-tune gameplay.",
        });

        builder.addParagraph("Navigate through the following sections to learn more about how to use Goal Smith:");

        builder.addLink("Goals", "https://github.com/Scouter456/Goal_Smith/wiki/Predicates");
        builder.addLink("Operations", "https://github.com/Scouter456/Goal_Smith/wiki/Operations");
        builder.addLink("Predicates", "https://github.com/Scouter456/Goal_Smith/wiki/Predicates");
        builder.addLink("Attributes", "https://github.com/Scouter456/Goal_Smith/wiki/Attributes");

        builder.addParagraph("The following page shows you what the correct json file should look like and where it should go.");
        builder.addLink("Goal Data", "https://github.com/Scouter456/Goal_Smith/wiki/Goal-Data");


        return builder;
    }


    private WikiPageBuilder predicatePage() {
        WikiPageBuilder builder = new WikiPageBuilder("Predicates");

        builder.addHeading("Introduction to Predicates", 3);
        builder.addParagraph("Predicates in GoalSmith serve as conditions that determine whether certain actions or goals should be applied to entities within Minecraft. " +
                "They encapsulate logical checks based on entity attributes, states, or environmental conditions.");
        builder.addParagraph("#### Example Usage");
        builder.addParagraph("Let's consider an example using the `IsCreativePredicate`:");
        IsCreativePredicate isCreativePredicate = new IsCreativePredicate();
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, isCreativePredicate));
        builder.addParagraph("In this example, the `IsCreativePredicate` checks whether an entity is in creative mode. If the entity is in creative mode, the predicate evaluates to true; otherwise, it evaluates to false. Such predicates can be used in conjunction with goal operations to selectively apply behaviors to entities based on their game mode.");
        int entries = PredicateRegistry.PREDICATE_SERIALIZER.getEntries().size();


        builder.addFormattedParagraph("A total of %s predicates are defined for now, which are all listed below", entries);

        builder.addHeading("Currently defined Predicates", 3);
        builder.startCollapsibleSection("And Predicate");
        // Creating an AndPredicate example
        AndPredicate predicate = new AndPredicate<>(
                new NegatePredicate<>(new IsCreativePredicate()),   // Negating IsCreativePredicate
                new IsDayPredicate()                                // Checking if it's daytime
        );

        builder.addParagraph("The And Predicate, combines multiple predicates to determine if all conditions are met simultaneously.");
        builder.addParagraph("**Example of And Predicate usage:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, predicate));
        builder.addParagraph("The above JSON represents an And Predicate that combines the following conditions:");
        builder.addList(new String[] {
                "1. NegatePredicate: Negates the result of IsCreativePredicate. So if the player is in creative, it will negate the result and return false.",
                "2. IsDayPredicate: Checks whether it is currently daytime."
        });
        builder.addParagraph("If these checks are passed it will allow a goal or target goal to go further with the code, or it can be used as a filter.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/AndPredicate.java");
        builder.endCollapsibleSection();

        OrPredicate orPredicate = new OrPredicate(
                new IsCreativePredicate(),   // Checking if the player is in Creative mode
                new IsDayPredicate()         // Checking if it's daytime
        );

        builder.startCollapsibleSection("Or Predicate");

        builder.addParagraph("The Or Predicate checks if at least one of the provided conditions is true. " +
                "In this example, it evaluates whether the player is in Creative mode or if it is daytime.");
        builder.addParagraph("**Example of Or Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, orPredicate));
        builder.addParagraph("The above JSON represents an Or Predicate that combines the following conditions:");
        builder.addList(new String[] {
                "1. IsCreativePredicate: Checks if the player is in Creative mode.",
                "2. IsDayPredicate: Checks whether it is currently daytime."
        });
        builder.addParagraph("If either of these conditions is true, the predicate evaluates to true.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/OrPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Negate Predicate");
        NegatePredicate negatePredicate = new NegatePredicate<>(new TruePredicate<>());
        // Creating an OrPredicate example
        builder.addParagraph("The Negate Predicate reverses the result of the enclosed predicate. " +
                "In this example, it negates the result of a True Predicate, effectively evaluating to false in all cases.");
        builder.addParagraph("**Example of Negate Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, negatePredicate));
        builder.addParagraph("The above JSON represents a Negate Predicate that reverses the result of the True Predicate causing it to return false.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/NegatePredicate.java");
        builder.endCollapsibleSection();


        TruePredicate truePredicate = new TruePredicate<>();
        builder.startCollapsibleSection("True Predicate");

        builder.addParagraph("The True Predicate always evaluates to true, regardless of the conditions or inputs.");
        builder.addParagraph("**Example of True Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, truePredicate));
        builder.addParagraph("The above JSON represents a True Predicate, which unconditionally returns true.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/TruePredicate.java");

        builder.endCollapsibleSection();

        NoCreativeOrSpectatorPredicate spectatorPredicate = new NoCreativeOrSpectatorPredicate();
        builder.startCollapsibleSection("No Creative Or Spectator Predicate");

        builder.addParagraph("The No Creative Or Spectator Predicate checks whether an entity is not a player in creative mode or a spectator.");
        builder.addParagraph("**Example of No Creative Or Spectator Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, spectatorPredicate));
        builder.addParagraph("The above JSON represents a No Creative Or Spectator Predicate that evaluates the following conditions:");
        builder.addList(new String[] {
                "1. Check if the entity is not a player.",
                "2. Check if the entity is not in spectator mode.",
                "3. Check if the player is not in creative mode."
        });
        builder.addParagraph("If any of these conditions are true, the predicate evaluates to true.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/TruePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Last Hurt By Mob Is Null Predicate");

        builder.addParagraph("The Last Hurt By Mob Is Null Predicate checks whether the entity has not been recently hurt by any mob.");
        builder.addParagraph("**Example of Last Hurt By Mob Is Null Predicate:**");

        LastHurtByMobIsNullPredicate predicate2 = new LastHurtByMobIsNullPredicate();
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, predicate2));

        builder.addParagraph("The above JSON represents a Last Hurt By Mob Is Null Predicate that evaluates whether:");
        builder.addList(new String[]{
                "1. The entity is an instance of LivingEntity.",
                "2. The last mob that hurt the entity is null (meaning no mob has hurt the entity recently)."
        });
        builder.addParagraph("If these conditions are met, the predicate evaluates to true.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/LastHurtByMobIsNullPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Spectator Predicate");

        builder.addParagraph("The Is Spectator Predicate checks whether an entity is in spectator mode.");
        builder.addParagraph("**Example of Is Spectator Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsSpectatorPredicate()));

        builder.addParagraph("The above JSON represents an Is Spectator Predicate that evaluates whether the entity is a player and in spectator mode.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsSpectatorPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Player Predicate");

        builder.addParagraph("The Is Player Predicate checks whether an entity is a player.");
        builder.addParagraph("**Example of Is Player Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsPlayerPredicate()));

        builder.addParagraph("The above JSON represents an Is Player Predicate that evaluates whether the entity is a player.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsPlayerPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is On Fire Predicate");

        builder.addParagraph("The Is On Fire Predicate checks whether an entity is currently on fire.");
        builder.addParagraph("**Example of Is On Fire Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsOnFirePredicate()));

        builder.addParagraph("The above JSON represents an Is On Fire Predicate that evaluates whether the entity is on fire.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsOnFirePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Day Predicate");

        builder.addParagraph("The Is Day Predicate checks whether it is currently daytime in the entity's world.");
        builder.addParagraph("**Example of Is Day Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsDayPredicate()));

        builder.addParagraph("The above JSON represents an Is Day Predicate that checks whether it is currently daytime in the entity's world.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsDayPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Night Predicate");

        builder.addParagraph("The Is Night Predicate checks whether it is nighttime in the entity's current level.");
        builder.addParagraph("**Example of Is Night Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsNightPredicate()));

        builder.addParagraph("The above JSON represents an Is Night Predicate that evaluates whether it is nighttime in the entity's current level.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsNightPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Invisible Predicate");

        builder.addParagraph("The Is Invisible Predicate checks whether the entity is currently invisible.");
        builder.addParagraph("**Example of Is Invisible Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsInvisiblePredicate()));

        builder.addParagraph("The above JSON represents an Is Invisible Predicate that evaluates whether the entity is currently invisible.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsInvisiblePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Freezing Predicate");

        builder.addParagraph("The Is Freezing Predicate checks whether the entity is currently freezing.");
        builder.addParagraph("**Example of Is Freezing Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsFreezingPredicate()));

        builder.addParagraph("The above JSON represents an Is Freezing Predicate that evaluates whether the entity is currently freezing.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsFreezingPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Creative Predicate");

        builder.addParagraph("The Is Creative Predicate checks whether the entity (player) is in Creative mode.");
        builder.addParagraph("**Example of Is Creative Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsCreativePredicate()));

        builder.addParagraph("The above JSON represents an Is Creative Predicate that checks whether the entity (player) is in Creative mode.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsCreativePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Entity Type Predicate");

        builder.addParagraph("The Entity Type Predicate checks whether the entity's type matches a specified entity type.");
        builder.addParagraph("**Example of Entity Type Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new EntityTypePredicate(EntityType.COW)));

        builder.addParagraph("The above JSON represents an Entity Type Predicate that checks whether the entity's type is `minecraft:cow`.");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityTypePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Entity Still Alive Predicate");

        builder.addParagraph("The Entity Still Alive Predicate checks whether an entity is still alive.");
        builder.addParagraph("**Example of Entity Still Alive Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new EntityStillAlivePredicate()));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityStillAlivePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Entity Not Being Ridden Predicate");

        builder.addParagraph("The Entity Not Being Ridden Predicate checks whether an entity is not being ridden.");
        builder.addParagraph("**Example of Entity Not Being Ridden Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new EntityNotBeingRiddenPredicate()));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityNotBeingRiddenPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Entity Is Vehicle Predicate");

        builder.addParagraph("The Entity Is Vehicle Predicate checks whether an entity is currently acting as a vehicle.");
        builder.addParagraph("**Example of Entity Is Vehicle Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new EntityIsVehiclePredicate()));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityIsVehiclePredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Entity Is Passenger Predicate");

        builder.addParagraph("The Entity Is Passenger Predicate checks whether an entity is currently acting as a passenger.");
        builder.addParagraph("**Example of Entity Is Passenger Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new EntityIsPassengerPredicate()));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/EntityIsPassengerPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Can Be Collided With Predicate");

        builder.addParagraph("The Can Be Collided With Predicate checks whether an entity can currently be collided with.");
        builder.addParagraph("**Example of Can Be Collided With Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new CanBeCollidedWithPredicate()));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/CanBeCollidedWithPredicate.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Is Difficulty Predicate");

        builder.addParagraph("The Is Difficulty Predicate checks whether the given difficulty matches the entity's difficulty.");
        builder.addParagraph("**Example of Is Difficulty Predicate:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, new IsDifficultyPredicate(Difficulty.EASY)));
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/predicates/IsDifficultyPredicate.java");

        builder.endCollapsibleSection();

        return builder;
    }

    private WikiPageBuilder operationsPage() {
        // Create a new instance of WikiPageBuilder for building the "Operations" page
        WikiPageBuilder builder = new WikiPageBuilder("Operations");

        // Adding a heading for introduction to operations
        builder.addHeading("Introduction to Operations", 3);

        // Adding paragraphs to explain operations
        builder.addParagraph("Operations in this context refer to the goals performed by entities within the Minecraft." +
                "These operations are able to change the goals of entities by adding, removing or replacing the goals an entity currently has.");

        // Adding an example usage section
        builder.addHeading("Example Usage", 4);
        builder.addParagraph("Let's consider an example using the AddOperation:");

        // Creating a list of goal codecs for the example
        List<GoalCodec> goalCodecs = new ArrayList<>();
        goalCodecs.add(new SitWhenOrderedToGoalCodec(1));  // Example codec

        // Creating an AddOperation instance with the goal codecs
        AddOperation addOperation = new AddOperation(goalCodecs);

        // Adding a code block with JSON-encoded data of the AddOperation
        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, addOperation));

        // Adding a paragraph to explain the example
        builder.addParagraph("In this example, the AddOperation adds a new goal to the entity with the specified priority.");

        // Fetching the number of goal operation entries from a registry
        int goalOperationEntries = GoalOperationRegistry.GOAL_OPERATION.getEntries().size();
        int targetGoalOperationEntries = GoalOperationRegistry.TARGET_GOAL_OPERATION.getEntries().size();

        // Adding a formatted paragraph to display the counts of goal operations
        builder.addFormattedParagraph("A total of %s Goal Operations and %s Target Goal Operations are defined.", goalOperationEntries, targetGoalOperationEntries);
        builder.addHeading("Goal Operations", 2);

        // Adding collapsible section for Add Operation
        builder.startCollapsibleSection("Add Operation");

        // Adding description paragraphs for Add Operation
        builder.addParagraph("The Add Operation adds a new goal to the entity with the specified priority.");
        builder.addParagraph("**Example of Add Operation:**");

        // Creating another example of AddOperation with different goal codecs
        List<GoalCodec> goalCodecs2 = new ArrayList<>();
        goalCodecs2.add(new SitWhenOrderedToGoalCodec(1));  // Example codec

        AddOperation addOperation2 = new AddOperation(goalCodecs2);

        // Adding a code block for the second AddOperation example
        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, addOperation2));

        // Adding a link to the code for AddOperation class
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/AddOperation.java");

        // Ending the collapsible section for Add Operation
        builder.endCollapsibleSection();


        // Adding collapsible section for RemoveAllOperation
        builder.startCollapsibleSection("Remove All Operation");

// Adding description paragraphs for RemoveAllOperation
        builder.addParagraph("The Remove All Operation removes all goals from the entity.");
        builder.addParagraph("**Example of Remove All Operation:**");

// Creating an instance of RemoveAllOperation
        RemoveAllOperation removeAllOperation = new RemoveAllOperation();

// Adding a code block for the RemoveAllOperation
        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, removeAllOperation));

// Adding a link to the code for RemoveAllOperation class (if available)
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveAllOperation.java");

// Ending the collapsible section for RemoveAllOperation
        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Remove Specific Operation");

        builder.addParagraph("The Remove Specific Operation removes goals from the entity that match a specific replacement goal.");
        builder.addParagraph("It checks for goals with the specified priority and removes those whose goal class matches the specified type.");

        builder.addParagraph("**Example of Remove Specific Operation:**");

        RemoveSpecificOperation.ReplacementGoal replacementGoal = new RemoveSpecificOperation.ReplacementGoal(1, SitWhenOrderedToGoal.class);  // Example replacement goal

        RemoveSpecificOperation removeSpecificOperation = new RemoveSpecificOperation(replacementGoal);

        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, removeSpecificOperation));
        builder.addParagraph("This Json checks for a priority for 1 in a specified entity and then checks if the 'SitWhenOrderedToGoal' is in there and then removes it");
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveSpecificOperation.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Remove Specific Priority Operation");

        builder.addParagraph("The Remove Specific Priority Operation removes goals from the entity that match a specific priority.");
        builder.addParagraph("It removes goals from the entity's goal selector that have the specified priority value.");

        builder.addParagraph("**Example of Remove Specific Priority Operation:**");

        RemoveSpecificPriorityOperation removeSpecificPriorityOperation = new RemoveSpecificPriorityOperation(1);  // Example priority to remove

        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, removeSpecificPriorityOperation));

        builder.addParagraph("This Json demonstrates removing goals with priority 1 from the entity's goal selector.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/RemoveSpecificPriorityOperation.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Replace Operation");

        builder.addParagraph("The Replace Operation replaces a goal in the entity's goal selector with a new goal.");
        builder.addParagraph("It first removes goals that match a specific replacement goal, then adds a new goal using a specified goal codec.");

        builder.addParagraph("**Example of Replace Operation:**");

        ReplaceOperation.ReplacementGoal replacementGoal2 = new ReplaceOperation.ReplacementGoal(1, SitWhenOrderedToGoal.class);  // Example replacement goal

        GoalCodec replacementCodec = new SitWhenOrderedToGoalCodec(1);  // Example replacement codec

        ReplaceOperation replaceOperation = new ReplaceOperation(replacementGoal2, replacementCodec);

        builder.addCodeBlock(encodeDataToJsonString(GoalOperation.DIRECT_CODEC, replaceOperation));

        builder.addParagraph("This Json demonstrates replacing goals with priority 1 and matching `SitWhenOrderedToGoal` with a new goal, in this case a new SitWhenOrderedGoal.");
        builder.addParagraph("Note: one is in the `minecraft` namespace and one in the `goalsmith` namespace, all minecraft goals to be removed should always be in the minecraft namespace.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/goal/ReplaceOperation.java");

        builder.endCollapsibleSection();

        builder.addHeading("Target Goal Operations",2);

        builder.startCollapsibleSection("Add Target Operation");

        builder.addParagraph("The Add Target Operation adds target goals to the entity.");
        builder.addParagraph("It applies target goal to add specific target goals to the entity.");

        builder.addParagraph("**Example of Add Target Operation:**");

        List<TargetGoalCodec> targetGoalCodecs = new ArrayList<>();
        targetGoalCodecs.add(new DefendVillageTargetGoalCodec(1));  // Example target goal codec

        AddTargetOperation addTargetOperation2 = new AddTargetOperation(targetGoalCodecs);

        builder.addCodeBlock(encodeDataToJsonString(TargetGoalOperation.DIRECT_CODEC, addTargetOperation2));

        builder.addParagraph("This Json demonstrates adding a target goal using a specified target goal codec. This one being the 'DefendVillageTargetGoalCodec' with a priority of 1");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/AddTargetOperation.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Remove All Target Operation");

        builder.addParagraph("The Remove All Target Operation removes all target goals from the entity.");
        builder.addParagraph("It clears the entity's target selector of all existing target goals.");

        builder.addParagraph("**Example of Remove All Target Operation:**");

        RemoveAllTargetOperation removeAllTargetOperation = new RemoveAllTargetOperation();

        builder.addCodeBlock(encodeDataToJsonString(TargetGoalOperation.DIRECT_CODEC, removeAllTargetOperation));

        builder.addParagraph("This Json demonstrates removing all target goals from the entity's target selector.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveAllTargetOperation.java");

        builder.endCollapsibleSection();

// Adding collapsible section for RemoveSpecificTargetOperation
        builder.startCollapsibleSection("Remove Specific Target Operation");

// Adding description paragraphs for RemoveSpecificTargetOperation
        builder.addParagraph("The Remove Specific Target Operation removes specific target goals from the entity.");
        builder.addParagraph("It checks for target goals with the specified priority and removes those whose goal class matches the specified type.");

// Example of Remove Specific Target Operation
        builder.addParagraph("**Example of Remove Specific Target Operation:**");

// Creating an instance of ReplacementGoal for the example
        RemoveSpecificTargetOperation.ReplacementGoal replacementGoal23 = new RemoveSpecificTargetOperation.ReplacementGoal(1, DefendVillageTargetGoal.class);

// Creating an instance of RemoveSpecificTargetOperation with the replacement goal
        RemoveSpecificTargetOperation removeSpecificTargetOperation = new RemoveSpecificTargetOperation(replacementGoal23);

// Adding a code block for the RemoveSpecificTargetOperation
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalOperation.DIRECT_CODEC, removeSpecificTargetOperation));

// Adding explanation for the example
        builder.addParagraph("This Json demonstrates removing target goals with priority 1 and matching``DefendVillageTargetGoal` from the entity.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveSpecificTargetOperation.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Remove Specific Target Priority Operation");

        builder.addParagraph("The Remove Specific Target Priority Operation removes target goals from the entity based on a specific priority.");
        builder.addParagraph("It filters and removes target goals that match the specified priority.");

        builder.addParagraph("**Example of Remove Specific Target Priority Operation:**");

        RemoveSpecificTargetPriorityOperation removeSpecificTargetPriorityOperation = new RemoveSpecificTargetPriorityOperation(1);  // Example priority to remove

        builder.addCodeBlock(encodeDataToJsonString(TargetGoalOperation.DIRECT_CODEC, removeSpecificTargetPriorityOperation));

        builder.addParagraph("This JSON-encoded data demonstrates removing target goals with priority 1 from the entity.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/RemoveSpecificTargetPriorityOperation.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Replace Target Operation");

        builder.addParagraph("The Replace Target Operation replaces existing target goals with a new target goal.");
        builder.addParagraph("It removes target goals that match a specific criteria and adds a new target goal as replacement.");

        builder.addParagraph("**Example of Replace Target Operation:**");

        ReplaceTargetOperation.ReplacementGoal replacementGoal4 = new ReplaceTargetOperation.ReplacementGoal(1, DefendVillageTargetGoal.class);  // Example replacement goal

        ReplaceTargetOperation replaceTargetOperation = new ReplaceTargetOperation(replacementGoal4, new DefendVillageTargetGoalCodec(1));  // Example replacement target goal codec

        builder.addCodeBlock(encodeDataToJsonString(TargetGoalOperation.DIRECT_CODEC, replaceTargetOperation));
        builder.addParagraph("This Json demonstrates replacing target goals with priority 1 and matching `DefendVillageTargetGoal` with a new target goal. This case it being a `DefendVillageTargetGoal`");
        builder.addParagraph("Note: one is in the `minecraft` namespace and one in the `goalsmith` namespace, all minecraft goals to be removed should always be in the minecraft namespace.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/operation/target/ReplaceTargetOperation.java");

        builder.endCollapsibleSection();


        builder.addHeading("Replaceable and Removable Classes", 2);

// Start collapsible section for Replaceable and Removable Goals
        builder.startCollapsibleSection("Replaceable and Removable Goals");

// Loop through each named goal in GoalMappings.NAMED_GOALS
        for (ResourceLocation goal : GoalMappings.NAMED_GOALS.keySet()) {
            builder.addCodeBlock(goal.toString());
        }

// End collapsible section for Replaceable and Removable Goals
        builder.endCollapsibleSection();

// Start collapsible section for Replaceable and Removable Target Goals
        builder.startCollapsibleSection("Replaceable and Removable Target Goals");

// Loop through each named target goal in TargetGoalMappings.NAMED_TARGET_GOALS
        for (ResourceLocation goal : TargetGoalMappings.NAMED_TARGET_GOALS.keySet()) {
            builder.addCodeBlock(goal.toString());
        }

// End collapsible section for Replaceable and Removable Target Goals
        builder.endCollapsibleSection();

        return builder;
    }

    private WikiPageBuilder goalPage() {
        WikiPageBuilder builder = new WikiPageBuilder("goals");

        // Introduction section
        builder.addHeading("Introduction to Goals", 2);
        builder.addParagraph("In this section, we introduce the concept of goals in the context of PathfinderMob behavior.");
        builder.addParagraph("Goals define specific behaviors that entities (PathfinderMob instances) can perform. Each goal is assigned a priority, where lower numbers indicate higher priority.");
        builder.addParagraph("Adding or replacing goals is achieved using goal operations, allowing entities to dynamically adjust their behavior.");

        // Explanation of goal operations
        builder.addHeading("Goal Operations", 2);
        builder.addParagraph("Goal operations such as AddOperation and ReplaceOperation are used to modify the goals of PathfinderMob instances.");
        builder.addParagraph("These operations accept various parameters depending on the specific goal type being added or replaced.");

        // Example goal and goal codec (using WaterFleeGoalCodec as an example)
        builder.addHeading("Example: Water Flee Goal", 2);
        builder.addParagraph("The Water Flee Goal is an example of a specific behavior that entities can exhibit when encountering water.");
        builder.addParagraph("It requires a goal priority to determine its precedence among other goals.");

        // Explanation of WaterFleeGoalCodec
        builder.addHeading("WaterFleeGoalCodec", 3);
        builder.addParagraph("The WaterFleeGoalCodec implements the GoalCodec interface, which facilitates the integration of the WaterFleeGoal with PathfinderMob instances.");
        builder.addParagraph("Parameters:");
        builder.addList(new String[]{
                "`goal_priority`: Determines the priority of the WaterFleeGoal.",
        });
        builder.addParagraph("Usage:");
        builder.addParagraph("The WaterFleeGoalCodec can be used with goal operations to add or replace this specific behavior in entities.");


        WaterFleeGoalCodec waterFleeGoalCodec = new WaterFleeGoalCodec(1);
        // Adding code block for WaterFleeGoalCodec
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, waterFleeGoalCodec));
        builder.addParagraph("This Json demonstrates a water flee goal with priority 1");


        int goals = GoalRegistry.GOAL_TYPE_SERIALIZER.getEntries().size();
        int targetGoals = GoalRegistry.TARGET_GOAL_TYPE_SERIALIZER.getEntries().size();

        builder.addFormattedParagraph("Currently a total of %s Goals and a total of %s Target Goals have been defined.",goals, targetGoals);


        builder.addHeading("Goals", 2);

        builder.startCollapsibleSection("Avoid Entity Goal");

        builder.addParagraph("The Avoid Entity Goal causes the entity to avoid specific types of entities within a certain distance.");
        builder.addParagraph("It provides options to define predicates for avoiding and interacting with the avoided entity.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`entity_class_to_avoid`: Tag key specifying the class of entities to avoid.",
                "`avoid_predicate`: Predicate determining which entities to avoid.",
                "`max_distance`: Maximum distance at which the entity will avoid the specified entities.",
                "`walk_speed_modifier`: Modifier applied to walking speed while avoiding.",
                "`sprint_speed_modifier`: Modifier applied to sprinting speed while avoiding.",
                "`predicate_on_avoid_entity`: Predicate determining conditions when the entity interacts with the avoided entity."
        });

        builder.addParagraph("**Example of Avoid Entity Goal:**");

// Example values for parameters
        int goalPriority = 1;
        TagKey<EntityType<?>> entityClassToAvoid =TagKey.create(Registries.ENTITY_TYPE, prefix("creeper"));  // Example tag key
        PredicateCodec<Entity> avoidPredicate = new TruePredicate<>();  // Example predicate
        float maxDistance = 10.0f;
        double walkSpeedModifier = 0.8;
        double sprintSpeedModifier = 0.5;
        PredicateCodec<Entity> predicateOnAvoidEntity = new NoCreativeOrSpectatorPredicate();  // Example predicate

// Creating an instance of AvoidEntityGoalCodec
        AvoidEntityGoalCodec avoidEntityGoal = new AvoidEntityGoalCodec(goalPriority, entityClassToAvoid, avoidPredicate, maxDistance, walkSpeedModifier, sprintSpeedModifier, predicateOnAvoidEntity);

// Adding code block for AvoidEntityGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, avoidEntityGoal));

        builder.addParagraph("This example sets up an Avoid Entity Goal with specified parameters to avoid creepers and modify movement speeds.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/AvoidEntityGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Avoid Llama Goal");

        builder.addParagraph("The Avoid Llama Goal causes the entity to avoid llamas within a certain distance.");
        builder.addParagraph("It provides options to modify movement speeds while avoiding llamas.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`entity_class_to_avoid`: Tag key specifying the class of entities to avoid (llamas in this case).",
                "`max_distance`: Maximum distance at which the entity will avoid llamas.",
                "`walk_speed_modifier`: Modifier applied to walking speed while avoiding llamas.",
                "`sprint_speed_modifier`: Modifier applied to sprinting speed while avoiding llamas."
        });

        builder.addParagraph("**Example of Avoid Llama Goal:**");

// Example values for parameters
        int goalPriority2 = 1;
        TagKey<EntityType<?>> entityClassToAvoid2 = TagKey.create(Registries.ENTITY_TYPE, prefix("llama"));  // Example tag key
        float maxDistance2 = 12.0f;
        double walkSpeedModifier2 = 0.75;
        double sprintSpeedModifier2 = 0.4;

// Creating an instance of AvoidLlamaGoalCodec
        AvoidLlamaGoalCodec avoidLlamaGoal = new AvoidLlamaGoalCodec(goalPriority2, entityClassToAvoid2, maxDistance2, walkSpeedModifier2, sprintSpeedModifier2);

// Adding code block for AvoidLlamaGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, avoidLlamaGoal));

        builder.addParagraph("This example sets up an Avoid Llama Goal with specified parameters to avoid llamas and modify movement speeds.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/AvoidLlamaGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Beg Goal");

        builder.addParagraph("The Beg  Goal causes the entity to beg for a specific type of food item, such as food.");
        builder.addParagraph("It allows the entity to perform behaviors related to obtaining the specified food item.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`interesting_food`: Tag key specifying the type of food item to beg for (e.g., fod food tag).",
                "`look_distance`: Distance at which the entity will look for the specified food item."
        });

        builder.addParagraph("**Example of Beg Goal:**");

// Example values for parameters
        int begCoalGoalPriority = 1;
        TagKey<Item> interestingFood = ItemTags.FOX_FOOD;  // Example tag key for fox food item
        float lookDistance = 10.0f;

// Creating an instance of BegCoalCodec
        BegGoalCodec begCoalCodec = new BegGoalCodec(begCoalGoalPriority, interestingFood, lookDistance);

// Adding code block for BegCoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, begCoalCodec));

        builder.addParagraph("This example sets up a Beg Goal with specified parameters to beg for fox food.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BegGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Break Door Goal");

        builder.addParagraph("The Break Door Goal causes the entity to attempt to break doors within a specified time frame under certain conditions.");
        builder.addParagraph("It checks the difficulty level before attempting to break the door.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`door_break_time`: Time in ticks it takes for the entity to break a door.",
                "`difficulty_predicate`: Predicate that defines under which difficulty levels the entity will attempt to break doors."
        });

        builder.addParagraph("**Example of Break Door Goal:**");

// Example values for parameters
        int breakDoorGoalPriority = 1;
        int doorBreakTime = 100;  // Example door break time in ticks
        PredicateCodec<Difficulty> difficultyPredicate = new TruePredicate<>();  // Example predicate (TruePredicate for all difficulties)

        BreakDoorGoalCodec breakDoorGoalCodec = new BreakDoorGoalCodec(breakDoorGoalPriority, doorBreakTime, difficultyPredicate);

        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, breakDoorGoalCodec));

        builder.addParagraph("This example sets up a Break Door Goal with specified parameters to break doors.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreakDoorGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Breath Air Goal");

        builder.addParagraph("The Breath Air Goal allows the entity to prioritize breathing air, typically used by aquatic mobs to surface and replenish their air.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Breath Air Goal:**");

// Example value for parameter
        int breathAirGoalPriority = 1;  // Example goal priority

// Creating an instance of BreathAirGoalCodec
        BreathAirGoalCodec breathAirGoalCodec = new BreathAirGoalCodec(breathAirGoalPriority);

// Adding code block for BreathAirGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, breathAirGoalCodec));

        builder.addParagraph("This example sets up a Breath Air Goal with a specified priority for an aquatic mob to surface and breathe.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreathAirGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Breed Goal");

        builder.addParagraph("The Breed Goal allows animals to prioritize finding a suitable partner for breeding.");

        builder.addParagraph("**Entries Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`possible_partner`: Tag key representing the type of entity that can be a breeding partner.",
                "`speed_modifier`: Speed modifier for movement towards the breeding partner."
        });

        builder.addParagraph("**Example of Breed Goal:**");

// Example values for parameters
        int breedGoalPriority = 1;  // Example goal priority
        TagKey<EntityType<?>> breedPartner = TagKey.create(Registries.ENTITY_TYPE, prefix("cow"));  // Example partner entity type
        double breedSpeedModifier = 1.2;  // Example speed modifier

// Creating an instance of BreedGoalCodec
        BreedGoalCodec breedGoalCodec = new BreedGoalCodec(breedGoalPriority, breedPartner, breedSpeedModifier);

// Adding code block for BreedGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, breedGoalCodec));

        builder.addParagraph("This example sets up a Breed Goal with a specified priority for an animal to find a suitable partner, based on the animal in the tag.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/BreedGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Climb On Top Of Powder Snow Goal");

        builder.addParagraph("The Climb On Top Of Powder Snow Goal allows a mob to prioritize climbing on top of powder snow blocks.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Climb On Top Of Powder Snow Goal:**");

// Example values for parameters
        int climbGoalPriority = 2;  // Example goal priority

// Creating an instance of ClimbOnTopOfPowderSnowGoalCodec
        ClimbOnTopOfPowderSnowGoalCodec climbGoalCodec = new ClimbOnTopOfPowderSnowGoalCodec(climbGoalPriority);

// Adding code block for ClimbOnTopOfPowderSnowGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, climbGoalCodec));

        builder.addParagraph("This example sets up a Climb On Top Of Powder Snow Goal with a specified priority.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/ClimbOnTopOfPowderSnowGoalCodec.java");

        builder.endCollapsibleSection();



        builder.startCollapsibleSection("Eat Block Goal");

        builder.addParagraph("The Eat Block Goal allows a mob, such as a sheep, to prioritize eating a specific block, typically grass blocks.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Eat Block Goal:**");

// Example values for parameters
        int eatGoalPriority = 3;  // Example goal priority

// Creating an instance of EatBlockGoalCodec
        EatBlockGoalCodec eatGoalCodec = new EatBlockGoalCodec(eatGoalPriority);

// Adding code block for EatBlockGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, eatGoalCodec));

        builder.addParagraph("This example sets up an Eat Block Goal with a specified priority, commonly used for sheep to eat grass blocks.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/EatBlockGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Flee Sun Goal");

        builder.addParagraph("The Flee Sun Goal allows a mob to prioritize fleeing from sunlight, adjusting its movement speed based on the provided speed modifier.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier to adjust the movement speed of the mob when fleeing from sunlight."
        });

        builder.addParagraph("**Example of Flee Sun Goal:**");

// Example values for parameters
        int fleeGoalPriority = 4;  // Example goal priority
        double fleeSpeedModifier = -0.5;  // Example speed modifier

// Creating an instance of FleeSunGoalCodec
        FleeSunGoalCodec fleeGoalCodec = new FleeSunGoalCodec(fleeGoalPriority, fleeSpeedModifier);

// Adding code block for FleeSunGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, fleeGoalCodec));

        builder.addParagraph("This example sets up a Flee Sun Goal with a specified priority and speed modifier, enabling mobs to prioritize avoiding sunlight.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FleeSunGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Float Goal");

        builder.addParagraph("The Float Goal allows a mob to prioritize floating in water.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Float Goal:**");

// Example values for parameters
        int floatGoalPriority = 3;  // Example goal priority

// Creating an instance of FloatGoalCodec
        FloatGoalCodec floatGoalCodec = new FloatGoalCodec(floatGoalPriority);

// Adding code block for FloatGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, floatGoalCodec));

        builder.addParagraph("This example sets up a Float Goal with a specified priority, enabling mobs to prioritize floating in water.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FloatGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Follow Boat Goal");

        builder.addParagraph("The Follow Boat Goal enables a mob to prioritize following a boat.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Follow Boat Goal:**");

// Example values for parameters
        int followBoatGoalPriority = 4;  // Example goal priority

// Creating an instance of FollowBoatGoalCodec
        FollowBoatGoalCodec followBoatGoalCodec = new FollowBoatGoalCodec(followBoatGoalPriority);

// Adding code block for FollowBoatGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, followBoatGoalCodec));

        builder.addParagraph("This example sets up a Follow Boat Goal with a specified priority, enabling mobs to prioritize following a boat.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowBoatGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Follow Flock Leader Goal");

        builder.addParagraph("The Follow Flock Leader Goal enables a mob to prioritize following the leader of a flock, specifically designed for AbstractSchoolingFish.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Follow Flock Leader Goal:**");

// Example values for parameters
        int followFlockLeaderGoalPriority = 5;  // Example goal priority

// Creating an instance of FollowFlockLeaderGoalCodec
        FollowFlockLeaderGoalCodec followFlockLeaderGoalCodec = new FollowFlockLeaderGoalCodec(followFlockLeaderGoalPriority);

// Adding code block for FollowFlockLeaderGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, followFlockLeaderGoalCodec));

        builder.addParagraph("This example sets up a Follow Flock Leader Goal with a specified priority, enabling mobs, specifically AbstractSchoolingFish, to prioritize following the flock leader.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowFlockLeaderGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Follow Mob Goal");

        builder.addParagraph("The Follow Mob Goal allows a mob to follow another mob within a specified area.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier for following.",
                "`stop_distance`: Distance at which the mob stops following.",
                "`area_size`: Size of the area within which the mob follows."
        });

        builder.addParagraph("**Example of Follow Mob Goal:**");

// Example values for parameters
        int followMobGoalPriority = 5;  // Example goal priority
        double speedModifier = 1.2;     // Example speed modifier
        float stopDistance = 8.0f;      // Example stop distance
        float areaSize = 10.0f;         // Example area size

// Creating an instance of FollowMobGoalCodec
        FollowMobGoalCodec followMobGoalCodec = new FollowMobGoalCodec(followMobGoalPriority, speedModifier, stopDistance, areaSize);

// Adding code block for FollowMobGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, followMobGoalCodec));

        builder.addParagraph("This example sets up a Follow Mob Goal with a specified priority, speed modifier, stop distance, and area size, allowing mobs to follow another mob within a defined area.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowMobGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Follow Owner Goal");

        builder.addParagraph("The Follow Owner Goal allows a tamable animal to follow its owner within specified distances and conditions.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier for following.",
                "`start_distance`: Distance at which the following starts.",
                "`stop_distance`: Distance at which the mob stops following.",
                "`can_fly` (optional, default: false): Whether the mob can fly to follow the owner."
        });

        builder.addParagraph("**Example of Follow Owner Goal:**");

// Example values for parameters
        int followOwnerGoalPriority = 5;    // Example goal priority
        double speedModifier2 = 1.2;         // Example speed modifier
        float startDistance = 5.0f;         // Example start distance
        float stopDistance2 = 10.0f;         // Example stop distance
        boolean canFly = false;             // Example can fly setting

// Creating an instance of FollowOwnerGoalCodec
        FollowOwnerGoalCodec followOwnerGoalCodec = new FollowOwnerGoalCodec(followOwnerGoalPriority, speedModifier2, startDistance, stopDistance2, canFly);

// Adding code block for FollowOwnerGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, followOwnerGoalCodec));

        builder.addParagraph("This example sets up a Follow Owner Goal with a specified priority, speed modifier, start distance, stop distance, and flying capability, allowing tamable animals to follow their owner.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowOwnerGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Follow Parent Goal");

        builder.addParagraph("The Follow Parent Goal allows an animal to follow its parent with a specified speed modifier.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier for following."
        });

        builder.addParagraph("**Example of Follow Parent Goal:**");

// Example values for parameters
        int followParentGoalPriority = 5;   // Example goal priority
        double speedModifier3 = 1.5;         // Example speed modifier

// Creating an instance of FollowParentGoalCodec
        FollowParentGoalCodec followParentGoalCodec = new FollowParentGoalCodec(followParentGoalPriority, speedModifier3);

// Adding code block for FollowParentGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, followParentGoalCodec));

        builder.addParagraph("This example sets up a Follow Parent Goal with a specified priority and speed modifier, allowing an animal to follow its parent.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/FollowParentGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Interact Goal");

        builder.addParagraph("The Interact Goal allows a mob to interact with entities of a specific type within a certain distance and with defined probabilities.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`look_at_type`: Type of entity to interact with.",
                "`look_distance`: Maximum distance at which interaction can occur.",
                "`probability` (Optional, default: 0.02): Probability of initiating interaction.",
                "`only_horizontal` (Optional, default: false): Whether to restrict interaction to horizontal directions."
        });

        builder.addParagraph("**Example of Interact Goal:**");

// Example values for parameters
        int interactGoalPriority = 5;               // Example goal priority
        TagKey<EntityType<?>> lookAtType = TagKey.create(Registries.ENTITY_TYPE, prefix("player"));    // Example entity type tag key
        float lookDistance3 = 10.0f;                 // Example look distance
        float probability = 0.05f;                  // Example probability
        boolean onlyHorizontal = true;              // Example only horizontal flag

// Creating an instance of InteractGoalCodec
        InteractGoalCodec interactGoalCodec = new InteractGoalCodec(interactGoalPriority, lookAtType, lookDistance3, probability, onlyHorizontal);

// Adding code block for InteractGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, interactGoalCodec));

        builder.addParagraph("This example sets up an Interact Goal with a specified priority, entity type to look at, look distance, probability, and only horizontal flag.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/InteractGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Jump Water Goal");

        builder.addParagraph("The Jump Water Goal enables a mob to jump out of water at defined intervals.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`interval`: Interval in ticks between each jump out of water."
        });

        builder.addParagraph("**Example of Jump Water Goal:**");

// Example values for parameters
        int jumpWaterGoalPriority = 5;      // Example goal priority
        int interval = 40;                  // Example interval in ticks

// Creating an instance of JumpWaterGoalCodec
        JumpWaterGoalCodec jumpWaterGoalCodec = new JumpWaterGoalCodec(jumpWaterGoalPriority, interval);

// Adding code block for JumpWaterGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, jumpWaterGoalCodec));

        builder.addParagraph("This example sets up a Jump Water Goal with a specified priority and interval, allowing the mob to jump out of water periodically.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/JumpWaterGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Land On Owner's Shoulder Goal");

        builder.addParagraph("The Land On Owner's Shoulder Goal allows a mob, specifically a ShoulderRidingEntity, to attempt to land on its owner's shoulder.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Land On Owner's Shoulder Goal:**");

// Example value for goal priority
        int landOnShoulderGoalPriority = 5;  // Example goal priority

// Creating an instance of LandOnOwnersShoulderGoalCodec
        LandOnOwnersShoulderGoalCodec landOnOwnersShoulderGoalCodec = new LandOnOwnersShoulderGoalCodec(landOnShoulderGoalPriority);

// Adding code block for LandOnOwnersShoulderGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, landOnOwnersShoulderGoalCodec));

        builder.addParagraph("This example sets up a Land On Owner's Shoulder Goal with a specified priority, enabling a mob that implements ShoulderRidingEntity to attempt to land on its owner's shoulder.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LandOnOwnersShoulderGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Leap At Target Goal");

        builder.addParagraph("The Leap At Target Goal allows a mob to leap towards a specific target position with a defined vertical movement.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`yd`: Vertical movement distance (Y-axis)."
        });

        builder.addParagraph("**Example of Leap At Target Goal:**");

        int leapAtTargetGoalPriority = 3;  // Example goal priority
        float yMovementDistance = 1.5f;    // Example Y-axis movement distance

        LeapAtTargetGoalCodec leapAtTargetGoalCodec = new LeapAtTargetGoalCodec(leapAtTargetGoalPriority, yMovementDistance);

// Adding code block for LeapAtTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, leapAtTargetGoalCodec));

        builder.addParagraph("This example sets up a Leap At Target Goal with a specified priority and vertical movement distance, enabling a mob to leap towards a target position.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LeapAtTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Lie On Bed Goal");

        builder.addParagraph("The Lie On Bed Goal enables a tamable animal to lie on a bed with specified behavior parameters.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier applied while lying on the bed.",
                "`search_range`: Range within which the bed should be searched for."
        });

        builder.addParagraph("**Example of Lie On Bed Goal:**");

// Example values for goal priority, speed modifier, and search range
        int lieOnBedGoalPriority = 2;    // Example goal priority
        double speedModifier5 = 0.5;      // Example speed modifier
        int searchRange = 5;             // Example search range

// Creating an instance of LieOnBedGoalCodec
        LieOnBedGoalCodec lieOnBedGoalCodec = new LieOnBedGoalCodec(lieOnBedGoalPriority, speedModifier5, searchRange);

// Adding code block for LieOnBedGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, lieOnBedGoalCodec));

        builder.addParagraph("This example sets up a Lie On Bed Goal with specified priority, speed modifier, and search range, allowing a tamable animal to lie on a bed with defined behavior.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LieOnBedGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Look At Entity Goal");

        builder.addParagraph("The Look At Entity Goal enables a mob to look at entities of a specific type within a certain distance with defined behavior parameters.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`look_at_type`: Type of entity to look at, defined using a tag key.",
                "`look_distance`: Maximum distance at which the entity will look at the target entity.",
                "`probability`: (Optional) Probability of performing this goal.",
                "`only_horizontal`: (Optional) Whether the entity should look only horizontally at the target."
        });

        builder.addParagraph("**Example of Look At Entity Goal:**");

// Example values for goal priority, look at type, look distance, probability, and only horizontal
        int lookAtEntityGoalPriority = 3;               // Example goal priority
        TagKey<EntityType<?>> lookAtEntityType = TagKey.create(Registries.ENTITY_TYPE, prefix("player"));  // Example tag key for entity type
        float lookDistance6 = 10.0f;                     // Example look distance
        float probability4 = 0.05f;                      // Example probability
        boolean onlyHorizontal5 = false;                 // Example only horizontal

// Creating an instance of LookAtEntityGoalCodec
        LookAtEntityGoalCodec lookAtEntityGoalCodec = new LookAtEntityGoalCodec(
                lookAtEntityGoalPriority, lookAtEntityType, lookDistance6, probability4, onlyHorizontal5);

// Adding code block for LookAtEntityGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, lookAtEntityGoalCodec));

        builder.addParagraph("This example sets up a Look At Entity Goal with specified priority, entity type to look at, look distance, probability, and horizontal-only behavior, enabling a mob to perform actions based on the specified parameters.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LookAtEntityGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Look At Trading Player Goal");

        builder.addParagraph("The Look At Trading Player Goal enables a villager to look at a trading player within a specified maximum distance.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`max_distance`: Maximum distance at which the villager will look at the trading player."
        });

        builder.addParagraph("**Example of Look At Trading Player Goal:**");

// Example values for goal priority and maximum distance
        int goalPriority7 = 2;            // Example goal priority
        float maxDistance7 = 12.0f;       // Example maximum distance

// Creating an instance of LookAtTradingPlayerGoalCodec
        LookAtTradingPlayerGoalCodec lookAtTradingPlayerGoalCodec = new LookAtTradingPlayerGoalCodec(goalPriority7, maxDistance7);

// Adding code block for LookAtTradingPlayerGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, lookAtTradingPlayerGoalCodec));

        builder.addParagraph("This example sets up a Look At Trading Player Goal with specified priority and maximum distance, allowing a villager to interact with a trading player within a defined range.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/LookAtTradingPlayerGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Melee Attack Goal");

        builder.addParagraph("The Melee Attack Goal allows a mob to perform melee attacks on a target, with options to modify speed and behavior when the target is not visible.");

        builder.addParagraph("**Entry Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of melee attacks.",
                "`following_target_even_if_not_seen`: Boolean indicating whether the mob should continue following the target even when not visible."
        });

        builder.addParagraph("**Example of Melee Attack Goal:**");

// Example values for goal priority, speed modifier, and following target visibility
        int goalPriority5 = 1;               // Example goal priority
        double speedModifier1 = 1.2;         // Example speed modifier
        boolean followingTarget = true;     // Example following target visibility

// Creating an instance of MeleeAttackGoalCodec
        MeleeAttackGoalCodec meleeAttackGoalCodec = new MeleeAttackGoalCodec(goalPriority5, speedModifier1, followingTarget);

// Adding code block for MeleeAttackGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, meleeAttackGoalCodec));

        builder.addParagraph("This example sets up a Melee Attack Goal with specified priority, speed modifier, and behavior regarding target visibility, allowing a mob to perform melee attacks.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MeleeAttackGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Move Back to Village Goal");

        builder.addParagraph("The Move Back to Village Goal instructs a mob to return to its village, with options to modify movement speed and check for idle time.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of movement back to the village.",
                "`check_no_action_time`: Boolean indicating whether to check for idle time before moving back."
        });

        builder.addParagraph("**Example of Move Back to Village Goal:**");

// Example values for goal priority, speed modifier, and check no action time
        int goalPriority6 = 1;               // Example goal priority
        double speedModifie6 = 1.5;         // Example speed modifier
        boolean checkNoActionTime = true;   // Example check no action time

// Creating an instance of MoveBackToVillageGoalCodec
        MoveBackToVillageGoalCodec moveBackGoalCodec = new MoveBackToVillageGoalCodec(goalPriority6, speedModifie6, checkNoActionTime);

// Adding code block for MoveBackToVillageGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, moveBackGoalCodec));

        builder.addParagraph("This example sets up a Move Back to Village Goal with specified priority, speed modifier, and behavior regarding checking idle time before returning.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveBackToVillageGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Move Through Village Goal");

        builder.addParagraph("The Move Through Village Goal directs a mob to move through a village environment, with options to modify movement speed, restrict to nighttime, and interact with doors.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of movement through the village.",
                "`only_at_night`: Boolean indicating whether the goal should be active only during nighttime.",
                "`distance_to_poi`: Distance to Points of Interest (POIs) within the village.",
                "`can_deal_with_doors`: Boolean indicating whether the mob can interact with doors."
        });

        builder.addParagraph("**Example of Move Through Village Goal:**");

// Example values for goal priority, speed modifier, only at night, distance to POI, and dealing with doors
        //int goalPriority = 1;               // Example goal priority
        //double speedModifier = 1.2;         // Example speed modifier
        boolean onlyAtNight = true;         // Example only at night restriction
        int distanceToPoi = 5;              // Example distance to POI
        boolean canDealWithDoors = true;    // Example can deal with doors

// Creating an instance of MoveThroughVillageGoalCodec
        MoveThroughVillageGoalCodec moveThroughGoalCodec = new MoveThroughVillageGoalCodec(goalPriority, speedModifier, onlyAtNight, distanceToPoi, canDealWithDoors);

// Adding code block for MoveThroughVillageGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, moveThroughGoalCodec));

        builder.addParagraph("This example sets up a Move Through Village Goal with specified parameters, adjusting movement speed, restricting to nighttime, setting a distance to POI, and allowing interaction with doors.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveThroughVillageGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Move To Block Goal");

        builder.addParagraph("The Move To Block Goal directs a mob to move towards a specific block type within a defined search range, both horizontally and vertically if specified.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`block`: Type of block that the mob should move towards. Specified as TagKey",
                "`speed_modifier`: Modifier affecting the speed of movement towards the block.",
                "`search_range`: Horizontal distance within which to search for the specified block.",
                "`vertical_search_range`: Optional vertical distance within which to search for the specified block. Defaults to 1 if not provided."
        });

        builder.addParagraph("**Example of Move To Block Goal:**");

// Example values for goal priority, block, speed modifier, search range, and vertical search range
        //int goalPriority = 1;                       // Example goal priority
        TagKey<Block> block = BlockTags.SNIFFER_DIGGABLE_BLOCK;  // Example block type
        //double speedModifier = 1.2;                 // Example speed modifier
        //int searchRange = 10;                       // Example search range
        int verticalSearchRange = 2;                // Example vertical search range

// Creating an instance of MoveToBlockGoalCodec
        MoveToBlockGoalCodec moveToBlockGoalCodec = new MoveToBlockGoalCodec(goalPriority, block, speedModifier, searchRange, verticalSearchRange);

// Adding code block for MoveToBlockGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, moveToBlockGoalCodec));

        builder.addParagraph("This example sets up a Move To Block Goal with specified parameters, directing a mob to move towards stone blocks within a certain range.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveToBlockGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Move Towards Restriction Goal");

        builder.addParagraph("The Move Towards Restriction Goal instructs a mob to move towards a restricted area, typically defined within the mob's navigation constraints.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of movement towards the restricted area."
        });

        builder.addParagraph("**Example of Move Towards Restriction Goal:**");

// Example values for goal priority and speed modifier
        //int goalPriority = 2;                       // Example goal priority
        //double speedModifier = 1.5;                 // Example speed modifier

// Creating an instance of MoveTowardsRestrictionGoalCodec
        MoveTowardsRestrictionGoalCodec moveTowardsRestrictionGoalCodec = new MoveTowardsRestrictionGoalCodec(goalPriority, speedModifier);

// Adding code block for MoveTowardsRestrictionGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, moveTowardsRestrictionGoalCodec));

        builder.addParagraph("This example sets up a Move Towards Restriction Goal with specified parameters, directing a mob to move towards a restricted area defined by its navigation constraints.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveTowardsRestrictionGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Move Towards Target Goal");

        builder.addParagraph("The Move Towards Target Goal instructs a mob to move to its target within a defined distance radius.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of movement towards the target.",
                "`within`: Maximum distance radius within which the mob will attempt to move towards the target."
        });

        builder.addParagraph("**Example of Move Towards Target Goal:**");

// Example values for goal priority, speed modifier, and distance radius
        //int goalPriority = 3;                       // Example goal priority
        //double speedModifier = 1.8;                 // Example speed modifier
        float within = 10.0f;                        // Example distance radius

// Creating an instance of MoveTowardsTargetGoalCodec
        MoveTowardsTargetGoalCodec moveTowardsTargetGoalCodec = new MoveTowardsTargetGoalCodec(goalPriority, speedModifier, within);

// Adding code block for MoveTowardsTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, moveTowardsTargetGoalCodec));

        builder.addParagraph("This example sets up a Move Towards Target Goal with specified parameters, directing a mob to move towards a target within a defined distance radius.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/MoveTowardsTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Offer Flower Goal");

        builder.addParagraph("The Offer Flower Goal instructs a mob to offer a flower to another entity.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Offer Flower Goal:**");

// Example value for goal priority
        //int goalPriority = 4;                       // Example goal priority

// Creating an instance of OfferFlowerGoalCodec
        OfferFlowerGoalCodec offerFlowerGoalCodec = new OfferFlowerGoalCodec(goalPriority);

// Adding code block for OfferFlowerGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, offerFlowerGoalCodec));

        builder.addParagraph("This example sets up an Offer Flower Goal with a specified goal priority, instructing a mob to offer a flower to another entity.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/OfferFlowerGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Open Door Goal");

        builder.addParagraph("The Open Door Goal instructs a mob to open or close a door.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`close_door`: Boolean flag indicating whether to close the door (`true`) or open it (`false`)."
        });

        builder.addParagraph("**Example of Open Door Goal:**");

// Example values for goal priority and close door flag
        //int goalPriority = 3;                       // Example goal priority
        boolean closeDoor = false;                  // Example close door flag

// Creating an instance of OpenDoorGoalCodec
        OpenDoorGoalCodec openDoorGoalCodec = new OpenDoorGoalCodec(goalPriority, closeDoor);

// Adding code block for OpenDoorGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, openDoorGoalCodec));

        builder.addParagraph("This example sets up an Open Door Goal with a specified goal priority and determines whether to open or close the door.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/OpenDoorGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Panic Goal");

        builder.addParagraph("The Panic Goal causes a mob to enter a panic state under specific conditions.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier for movement speed during panic.",
                "`panic_predicate`: Predicate determining when the mob should panic. Default value includes checks for being attacked or freezing."
        });

        builder.addParagraph("**Example of Panic Goal:**");

// Example values for goal priority, speed modifier, and panic predicate
        //int goalPriority = 5;                       // Example goal priority
        //double speedModifier = 1.2;                 // Example speed modifier
        PredicateCodec<Entity> panicPredicate = new OrPredicate<>(new OrPredicate<>(new NegatePredicate<>(new LastHurtByMobIsNullPredicate()), new IsFreezingPredicate()),new IsOnFirePredicate()); // Example panic predicate

// Creating an instance of PanicGoalCodec
        PanicGoalCodec panicGoalCodec = new PanicGoalCodec(goalPriority, speedModifier, panicPredicate);

// Adding code block for PanicGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, panicGoalCodec));

        builder.addParagraph("This example sets up a Panic Goal with a specified goal priority, speed modifier, and panic predicate.");

        builder.addParagraph("**Default Value for Panic Predicate:**");
        builder.addParagraph("The default panic predicate includes checks for whether the mob has been attacked, is freezing, or is on fire.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/PanicGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Pathfind To Raid Goal");

        builder.addParagraph("The Pathfind To Raid Goal directs a mob to pathfind towards a raid event.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Pathfind To Raid Goal:**");

// Example value for goal priority
        //int goalPriority = 5;                       // Example goal priority

// Creating an instance of PathfindToRaidGoalCodec
        PathfindToRaidGoalCodec pathfindToRaidGoalCodec = new PathfindToRaidGoalCodec(goalPriority);

// Adding code block for PathfindToRaidGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, pathfindToRaidGoalCodec));

        builder.addParagraph("This example sets up a Pathfind To Raid Goal with a specified goal priority.");

        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/PathfindToRaidGoalCodec.java");

        builder.endCollapsibleSection();


        builder.startCollapsibleSection("Raise Arm Attack Goal");

        builder.addParagraph("The Raise Arm Attack Goal directs an entity to raise its arm and attack.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier for the speed of the attack.",
                "`following_target_even_if_not_seen`: Whether the zombie will continue following the target even if not seen."
        });

        builder.addParagraph("**Example of Raise Arm Attack Goal:**");

// Example values for goal priority, speed modifier, and following target even if not seen
        //int goalPriority = 5;                       // Example goal priority
        //double speedModifier = 1.2;                 // Example speed modifier
        boolean followingTargetEvenIfNotSeen = true; // Example value for following target even if not seen

// Creating an instance of RaiseArmAttackGoalCodec
        RaiseArmAttackGoalCodec raiseArmAttackGoalCodec = new RaiseArmAttackGoalCodec(goalPriority, speedModifier, followingTargetEvenIfNotSeen);

// Adding code block for RaiseArmAttackGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, raiseArmAttackGoalCodec));

        builder.addParagraph("This example sets up a Raise Arm Attack Goal for a zombie with specified parameters.");

        builder.addParagraph("**Used by:** Zombie, also known as ZombieAttackGoal");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RaiseArmAttackGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Random Look Around Goal");

        builder.addParagraph("The Random Look Around Goal causes a mob to randomly look around.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Random Look Around Goal:**");

// Example value for goal priority
        //int goalPriority = 3; // Example goal priority

// Creating an instance of RandomLookAroundGoalCodec
        RandomLookAroundGoalCodec randomLookAroundGoalCodec = new RandomLookAroundGoalCodec(goalPriority);

// Adding code block for RandomLookAroundGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, randomLookAroundGoalCodec));

        builder.addParagraph("This example sets up a Random Look Around Goal for a mob with the specified goal priority.");

        //builder.addParagraph("**Used by:** Various mobs");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomLookAroundGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Random Stroll Goal");

        builder.addParagraph("The Random Stroll Goal causes a mob to randomly wander around.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier` (Optional): Speed modifier for the stroll. Defaults to `1.0D` if not specified.",
                "`interval` (Optional): Interval in ticks between strolls. Defaults to `120` if not specified.",
                "`check_no_action_time` (Optional): Whether to check for no action time. Defaults to `true` if not specified."
        });

        builder.addParagraph("**Example of Random Stroll Goal:**");

// Example values for goal priority, speed modifier, interval, and check no action time
        //int goalPriority = 3; // Example goal priority
        //double speedModifier = 1.2D; // Example speed modifier
        //int interval = 150; // Example interval
        //boolean checkNoActionTime = true; // Example check no action time

// Creating an instance of RandomStrollGoalCodec
        RandomStrollGoalCodec randomStrollGoalCodec = new RandomStrollGoalCodec(goalPriority, speedModifier, interval, checkNoActionTime);

// Adding code block for RandomStrollGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, randomStrollGoalCodec));

        builder.addParagraph("This example sets up a Random Stroll Goal with specified parameters for a mob.");

        //builder.addParagraph("**Used by:** Various mobs");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomStrollGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Random Stroll In Village Goal");

        builder.addParagraph("The Random Stroll In Village Goal causes a mob to randomly wander around within the village boundaries.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier for the stroll. Adjusts the speed at which the mob moves. Defaults to `1.0D` if not specified."
        });

        builder.addParagraph("**Example of Random Stroll In Village Goal:**");

// Example values for goal priority and speed modifier
        //nt goalPriority = 3; // Example goal priority
        //ouble speedModifier = 1.5D; // Example speed modifier

// Creating an instance of RandomStrollInVillageGoalCodec
        RandomStrollInVillageGoalCodec randomStrollInVillageGoalCodec = new RandomStrollInVillageGoalCodec(goalPriority, speedModifier);

// Adding code block for RandomStrollInVillageGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, randomStrollInVillageGoalCodec));

        builder.addParagraph("This example sets up a Random Stroll In Village Goal with specified parameters for a mob.");

        //builder.addParagraph("**Used by:** Golems and other mobs within the village.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomStrollInVillageGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Random Swimming Goal");

        builder.addParagraph("The Random Swimming Goal causes a mob to swim randomly around its environment.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier` (Optional): Speed modifier for the swimming. Defaults to `1.0D` if not specified.",
                "`interval` (Optional): Interval in ticks between swimming actions. Defaults to `120` if not specified."
        });

        builder.addParagraph("**Example of Random Swimming Goal:**");

// Example values for goal priority, speed modifier, and interval
        //int goalPriority = 2; // Example goal priority
        //double speedModifier = 1.3D; // Example speed modifier
        //int interval = 100; // Example interval

// Creating an instance of RandomSwimmingGoalCodec
        RandomSwimmingGoalCodec randomSwimmingGoalCodec = new RandomSwimmingGoalCodec(goalPriority, speedModifier, interval);

// Adding code block for RandomSwimmingGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, randomSwimmingGoalCodec));

        builder.addParagraph("This example sets up a Random Swimming Goal with specified parameters for a mob.");

        //builder.addParagraph("**Used by:** Various aquatic mobs");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RandomSwimmingGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Ranged Attack Goal");

        builder.addParagraph("The Ranged Attack Goal enables a mob to perform ranged attacks with specified parameters for speed, attack intervals, and attack radius.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier during the attack.",
                "`attack_interval_min`: Minimum interval in ticks between attacks.",
                "`attack_interval_max`: Maximum interval in ticks between attacks.",
                "`attack_radius`: The radius within which the mob can attack."
        });

        builder.addParagraph("**Example of Ranged Attack Goal:**");

// Example values for goal priority, speed modifier, attack intervals, and attack radius
        //int goalPriority = 2; // Example goal priority
        //double speedModifier = 1.5D; // Example speed modifier
        int attackIntervalMin = 20; // Example minimum attack interval
        int attackIntervalMax = 40; // Example maximum attack interval
        float attackRadius = 15.0F; // Example attack radius

// Creating an instance of RangedAttackGoalCodec
        RangedAttackGoalCodec rangedAttackGoalCodec = new RangedAttackGoalCodec(goalPriority, speedModifier, attackIntervalMin, attackIntervalMax, attackRadius);

// Adding code block for RangedAttackGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, rangedAttackGoalCodec));

        builder.addParagraph("This example sets up a Ranged Attack Goal with specified parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that implement the `RangedAttackMob` interface");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedAttackGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Ranged Bow Attack Goal");

        builder.addParagraph("The Ranged Bow Attack Goal enables a mob to perform ranged bow attacks with specified parameters for speed, attack interval, and attack radius.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier during the attack.",
                "`attack_interval_min`: Minimum interval in ticks between attacks.",
                "`attack_radius`: The radius within which the mob can attack."
        });

        builder.addParagraph("**Example of Ranged Bow Attack Goal:**");

// Example values for goal priority, speed modifier, attack interval, and attack radius
        //int goalPriority = 2; // Example goal priority
        //double speedModifier = 1.5D; // Example speed modifier
        //int attackIntervalMin = 20; // Example minimum attack interval
        //float attackRadius = 15.0F; // Example attack radius

// Creating an instance of RangedBowAttackGoalCodec
        RangedBowAttackGoalCodec<?> rangedBowAttackGoalCodec = new RangedBowAttackGoalCodec<>(goalPriority, speedModifier, attackIntervalMin, attackRadius);

// Adding code block for RangedBowAttackGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, rangedBowAttackGoalCodec));

        builder.addParagraph("This example sets up a Ranged Bow Attack Goal with specified parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that implement both the `Mob` and `RangedAttackMob` interfaces");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedBowAttackGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Ranged Crossbow Attack Goal");

        builder.addParagraph("The Ranged Crossbow Attack Goal enables a mob to perform ranged crossbow attacks with specified parameters for speed and attack radius.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier during the attack.",
                "`attack_radius`: The radius within which the mob can attack."
        });

        builder.addParagraph("**Example of Ranged Crossbow Attack Goal:**");

// Example values for goal priority, speed modifier, and attack radius
        //int goalPriority = 1; // Example goal priority
        //double speedModifier = 1.0D; // Example speed modifier
        //float attackRadius = 20.0F; // Example attack radius

// Creating an instance of RangedCrossbowAttackGoalCodec
        RangedCrossbowAttackGoalCodec<?> rangedCrossbowAttackGoalCodec =
                new RangedCrossbowAttackGoalCodec<>(goalPriority, speedModifier, attackRadius);

// Adding code block for RangedCrossbowAttackGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, rangedCrossbowAttackGoalCodec));

        builder.addParagraph("This example sets up a Ranged Crossbow Attack Goal with specified parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that implement the `Monster`, `RangedAttackMob`, and `CrossbowAttackMob` interfaces");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RangedCrossbowAttackGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Remove Block Goal");

        builder.addParagraph("The Remove Block Goal enables a mob to remove specific blocks in its vicinity, with parameters for speed, search range, and block type.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`block_to_remove`: The type of block that the mob will attempt to remove. No tag just a block e.g. minecraft:grass_block",
                "`speed_modifier`: Speed modifier for the action of removing blocks.",
                "`search_range`: The range within which the mob will search for the specified block to remove."
        });

        builder.addParagraph("**Example of Remove Block Goal:**");

// Example values for goal priority, block to remove, speed modifier, and search range
        //int goalPriority = 1; // Example goal priority
        Block blockToRemove = Blocks.DIRT; // Example block to remove
        //double speedModifier = 1.0D; // Example speed modifier
        //int searchRange = 10; // Example search range

// Creating an instance of RemoveBlockGoalCodec
        RemoveBlockGoalCodec removeBlockGoalCodec = new RemoveBlockGoalCodec(goalPriority, blockToRemove, speedModifier, searchRange);

// Adding code block for RemoveBlockGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, removeBlockGoalCodec));

        builder.addParagraph("This example sets up a Remove Block Goal with specified parameters for a mob.");

        //builder.addParagraph("**Used by:** Mobs that can perform block removal actions, typically for pathfinding or breaking obstacles");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RemoveBlockGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Restrict Sun Goal");

        builder.addParagraph("The Restrict Sun Goal prevents a mob from being exposed to sunlight, often to avoid damage or discomfort caused by sunlight.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Restrict Sun Goal:**");

// Example value for goal priority
        //int goalPriority = 2; // Example goal priority

// Creating an instance of RestrictSunGoalCodec
        RestrictSunGoalCodec restrictSunGoalCodec = new RestrictSunGoalCodec(goalPriority);

// Adding code block for RestrictSunGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, restrictSunGoalCodec));

        builder.addParagraph("This example sets up a Restrict Sun Goal with the specified priority for a mob.");

        builder.addParagraph("**Used by:** Mobs that need to avoid sunlight, typically for survival reasons such as avoiding damage from sunlight exposure");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/RestrictSunGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Search For Items Goal");

        builder.addParagraph("The Search For Items Goal enables a mob to search for specific items defined by a tag key. This goal can be used to simulate behaviors where mobs actively seek out certain items in their environment.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`allowed_items`: Tag key for items that the mob is allowed to search for."
        });

        builder.addParagraph("**Example of Search For Items Goal:**");

// Example values for goal priority and allowed items
        //int goalPriority = 1; // Example goal priority
        TagKey<Item> allowedItems = ItemTags.ARROWS; // Example tag key for allowed items

// Creating an instance of SearchForItemsGoalCodec
        SearchForItemsGoalCodec searchForItemsGoalCodec = new SearchForItemsGoalCodec(goalPriority, allowedItems);

// Adding code block for SearchForItemsGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, searchForItemsGoalCodec));

        builder.addParagraph("This example sets up a Search For Items Goal with arrows in a tag.");

        //builder.addParagraph("**Used by:** Mobs that need to actively search for and potentially collect specific items, such as resource-gathering entities or scavenger mobs.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SearchForItemsGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Sit On Block Goal");

        builder.addParagraph("The Sit On Block Goal allows tamable animals to sit on specified blocks with enhanced behavior.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Speed modifier for sitting on the block."
        });

        builder.addParagraph("**Example of Sit On Block Goal:**");

// Example values for goal priority and speed modifier
        //int goalPriority = 1; // Example goal priority
        //double speedModifier = 0.8; // Example speed modifier

// Creating an instance of SitOnBlockGoalCodec
        SitOnBlockGoalCodec sitOnBlockGoalCodec = new SitOnBlockGoalCodec(goalPriority, speedModifier);

// Adding code block for SitOnBlockGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, sitOnBlockGoalCodec));

        builder.addParagraph("This example sets up a Sit On Block Goal with specified parameters for a tamable animal.");

        //builder.addParagraph("**Used by:** Tamable animals that have the ability to sit on specific blocks, enhancing their interaction with the environment.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SitOnBlockGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Sit When Ordered to Goal");

        builder.addParagraph("The Sit When Ordered to Goal allows tamable animals to sit down when ordered to do so.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Sit When Ordered to Goal:**");

// Example value for goal priority
        //int goalPriority = 1; // Example goal priority

// Creating an instance of SitWhenOrderedToGoalCodec
        SitWhenOrderedToGoalCodec sitWhenOrderedToGoalCodec = new SitWhenOrderedToGoalCodec(goalPriority);

// Adding code block for SitWhenOrderedToGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, sitWhenOrderedToGoalCodec));

        builder.addParagraph("This example sets up a Sit When Ordered to Goal with a specified priority for a tamable animal.");

        builder.addParagraph("**Used by:** Tamable animals that have the ability to sit down when they receive an order or command.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/SitWhenOrderedToGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Stroll Through Village Goal");

        builder.addParagraph("The Stroll Through Village Goal causes a mob to wander through a village.");

        builder.addParagraph("**Parameters Needed:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`interval`: Interval in ticks between strolls."
        });

        builder.addParagraph("**Example of Stroll Through Village Goal:**");

// Example values for goal priority and interval
        //int goalPriority = 3; // Example goal priority
        //int interval = 200; // Example interval

// Creating an instance of StrollThroughVillageGoalCodec
        StrollThroughVillageGoalCodec strollThroughVillageGoalCodec = new StrollThroughVillageGoalCodec(goalPriority, interval);

// Adding code block for StrollThroughVillageGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, strollThroughVillageGoalCodec));

        builder.addParagraph("This example sets up a Stroll Through Village Goal with specified parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that are intended to wander through villages in the game environment.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/StrollThroughVillageGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Tempt Goal");

        builder.addParagraph("The Tempt Goal entices a mob to follow and interact with specified items.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier applied to the mob's movement speed while tempted.",
                "`items`: Tag of items that can tempt the mob.",
                "`can_scare`: Whether the mob can be scared away from the tempting item."
        });

        builder.addParagraph("**Example of Tempt Goal:**");

// Example values for goal priority, speed modifier, items tag, and scare capability
        //int goalPriority = 3; // Example goal priority
        //double speedModifier = 1.2; // Example speed modifier
        TagKey<Item> items = ItemTags.FOX_FOOD; // Example tag of items that can tempt the mob
        boolean canScare = true; // Example scare capability

// Creating an instance of TemptGoalCodec
        TemptGoalCodec temptGoalCodec = new TemptGoalCodec(goalPriority, speedModifier, items, canScare);

// Adding code block for TemptGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, temptGoalCodec));

        builder.addParagraph("This example sets up a Tempt Goal with specified parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that can be tempted by items, in the example fox food, such as animals following food or other enticing objects.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TemptGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Trade With Player Goal");

        builder.addParagraph("The Trade With Player Goal enables an AbstractVillager to engage in trading interactions with players.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Trade With Player Goal:**");

// Example values for goal priority
        //int goalPriority = 3; // Example goal priority

// Creating an instance of TradeWithPlayerGoalCodec
        TradeWithPlayerGoalCodec tradeGoalCodec = new TradeWithPlayerGoalCodec(goalPriority);

// Adding code block for TradeWithPlayerGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, tradeGoalCodec));

        builder.addParagraph("This example sets up a Trade With Player Goal with a specified priority for an AbstractVillager.");

        //builder.addParagraph("**Used by:** AbstractVillagers that are capable of trading with players in Minecraft.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TradeWithPlayerGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Try Find Water Goal");

        builder.addParagraph("The Try Find Water Goal allows a mob to actively seek out nearby water sources for hydration or other purposes.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example of Try Find Water Goal:**");

// Example values for goal priority
        //int goalPriority = 3; // Example goal priority

// Creating an instance of TryFindWaterGoalCodec
        TryFindWaterGoalCodec waterGoalCodec = new TryFindWaterGoalCodec(goalPriority);

// Adding code block for TryFindWaterGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, waterGoalCodec));

        builder.addParagraph("This example demonstrates how to set up a Try Find Water Goal with a specified priority for a mob.");

        //builder.addParagraph("**Used by:** Mobs that need to find water sources for survival or other specific behaviors in Minecraft.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/TryFindWaterGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Use Item Goal");

        builder.addParagraph("The Use Item Goal allows a mob to use a specific item under certain conditions.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`item`: The item that the mob will use.",
                "`finish_using_sound`: The sound played when the mob finishes using the item.",
                "`can_use_predicate`: Predicate determining when the mob can use the item."
        });

        builder.addParagraph("**Example of Use Item Goal:**");

// Example values for goal priority, item, finish using sound, and can use predicate
        //int goalPriority = 3; // Example goal priority
        ItemStack item = new ItemStack(Items.CAKE); // Example item
        SoundEvent finishUsingSound = SoundEvents.PLAYER_BURP; // Example finish using sound
        PredicateCodec<Entity> canUsePredicate = new IsNightPredicate(); // Example can use predicate

// Creating an instance of UseItemGoalCodec
        UseItemGoalCodec useItemGoalCodec = new UseItemGoalCodec(goalPriority, item, finishUsingSound, canUsePredicate);

// Adding code block for UseItemGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, useItemGoalCodec));

        builder.addParagraph("This example demonstrates how to set up a Use Item Goal with specific parameters for a mob.");

       // builder.addParagraph("**Used by:** Mobs that need to interact with items in Minecraft, such as feeding animals or using tools.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/UseItemGoalCodec.java");

        builder.endCollapsibleSection();
        builder.startCollapsibleSection("Water Avoiding Random Flying Goal");

        builder.addParagraph("The Water Avoiding Random Flying Goal enables a mob to perform random flying movements while avoiding water.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier that affects the speed of the flying movement."
        });

        builder.addParagraph("**Example of Water Avoiding Random Flying Goal:**");

// Example values for goal priority and speed modifier
        //int goalPriority = 3; // Example goal priority
        //double speedModifier = 1.2; // Example speed modifier

// Creating an instance of WaterAvoidingRandomFlyingGoalCodec
        WaterAvoidingRandomFlyingGoalCodec waterAvoidingRandomFlyingGoalCodec = new WaterAvoidingRandomFlyingGoalCodec(goalPriority, speedModifier);

// Adding code block for WaterAvoidingRandomFlyingGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, waterAvoidingRandomFlyingGoalCodec));

        builder.addParagraph("This example demonstrates how to set up a Water Avoiding Random Flying Goal with specific parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that have the ability to fly and need to avoid water bodies in their movement.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterAvoidingRandomFlyingGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Water Avoiding Random Stroll Goal");

        builder.addParagraph("The Water Avoiding Random Stroll Goal allows a mob to perform random walking movements while avoiding water.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Priority of the goal. Lower numbers indicate higher priority.",
                "`speed_modifier`: Modifier affecting the speed of the walking movement.",
                "`probability`: Optional parameter specifying the probability of performing the random stroll."
        });

        builder.addParagraph("**Example of Water Avoiding Random Stroll Goal:**");

// Example values for goal priority, speed modifier, and probability
        //int goalPriority = 3; // Example goal priority
        //double speedModifier = 1.2; // Example speed modifier
        //float probability = 0.001F; // Example probability

// Creating an instance of WaterAvoidingRandomStrollGoalCodec
        WaterAvoidingRandomStrollGoalCodec waterAvoidingRandomStrollGoalCodec = new WaterAvoidingRandomStrollGoalCodec(goalPriority, speedModifier, probability);

// Adding code block for WaterAvoidingRandomStrollGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, waterAvoidingRandomStrollGoalCodec));

        builder.addParagraph("This example demonstrates how to set up a Water Avoiding Random Stroll Goal with specific parameters for a mob.");

        builder.addParagraph("**Used by:** Mobs that need to perform random walking movements while avoiding water bodies in their path.");

// Link to the source code for further reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterAvoidingRandomStrollGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Water Flee Goal");

        builder.addParagraph("The Water Flee Goal enables a mob to execute evasive actions when near water, facilitating escape from potentially dangerous situations involving water.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example Usage of Water Flee Goal:**");

// Example values for goal priority
        //int goalPriority = 3; // Example goal priority

// Creating an instance of WaterFleeGoalCodec
        //WaterFleeGoalCodec waterFleeGoalCodec = new WaterFleeGoalCodec(goalPriority);

// Adding code block for WaterFleeGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(GoalCodec.DIRECT_CODEC, waterFleeGoalCodec));

        builder.addParagraph("This example demonstrates how to configure a Water Flee Goal with specific parameters for a mob.");

        //builder.addParagraph("**Applied to:** Mobs that need to evade and flee from water sources to avoid potential dangers or unfavorable conditions.");

// Source code reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/WaterFleeGoalCodec.java");

        builder.endCollapsibleSection();


        builder.addHeading("Target Goals",2);


        builder.startCollapsibleSection("Defend Village Target Goal");

        builder.addParagraph("The Defend Village Target Goal instructs a mob to prioritize defending a village when threats are detected, ensuring the safety of the village and its inhabitants.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example Usage of Defend Village Target Goal:**");

// Example values for goal priority
        //int goalPriority = 2; // Example goal priority

// Creating an instance of DefendVillageTargetGoalCodec
        DefendVillageTargetGoalCodec defendVillageTargetGoalCodec = new DefendVillageTargetGoalCodec(goalPriority);

// Adding code block for DefendVillageTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, defendVillageTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure a Defend Village Target Goal with specific parameters for a mob.");

        builder.addParagraph("**Applied to:** Mobs that are tasked with defending villages from threats, ensuring the safety of villagers and structures within the village.");

// Source code reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/DefendVillageTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Hurt By Target Goal");

        builder.addParagraph("The Hurt By Target Goal enables a mob to prioritize attacking entities that have inflicted harm on it, ignoring specified entity types.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.",
                "`to_ignore`: Defines the types of entities that the mob should ignore when selecting targets. Defined as TagKey"
        });

        builder.addParagraph("**Example Usage of Hurt By Target Goal:**");

// Example values for goal priority and entity types to ignore
        //int goalPriority = 1; // Example goal priority
        TagKey<EntityType<?>> ignoreTags =TagKey.create(Registries.ENTITY_TYPE, prefix("player")); // Example entity types to ignore

// Creating an instance of HurtByTargetGoalCodec
        HurtByTargetGoalCodec hurtByTargetGoalCodec = new HurtByTargetGoalCodec(goalPriority, ignoreTags);

// Adding code block for HurtByTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, hurtByTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure a Hurt By Target Goal with specific parameters for a mob.");

        builder.addParagraph("**Applied to:** Mobs that prioritize retaliating against entities that have harmed them, while ignoring specific types of entities.");

// Source code reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/HurtByTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Nearest Attackable Target Goal");

        builder.addParagraph("The Nearest Attackable Target Goal allows a mob to select the nearest living entity of a specified type " +
                "as its target, with optional conditions.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.",
                "`target_type`: Specifies the type of entity that the mob will target.",
                "`must_see`: Determines if the mob must have line-of-sight to the target.",
        });

        builder.addParagraph("**Optional Parameters:**");
        builder.addList(new String[]{
                "`random_interval`: Defines the interval (in ticks) at which the mob re-evaluates its target selection randomly. Default is 10 ticks.",
                "`must_reach`: Specifies whether the mob must be able to pathfind to the target entity.",
                "`target_predicate`: Allows customization of the target selection criteria using a predicate."
        });

        builder.addParagraph("**Example Usage of Nearest Attackable Target Goal:**");

// Example values for goal priority, entity type, and optional parameters
        //int goalPriority = 1; // Example goal priority
        TagKey<EntityType<?>> targetType = TagKey.create(Registries.ENTITY_TYPE, prefix("player")); // Example target type
        int randomInterval = 15; // Example random interval
        boolean mustSee = true; // Example must see flag
        boolean mustReach = false; // Example must reach flag
        PredicateCodec<Entity> targetPredicate = new TruePredicate<>(); // Example target predicate

// Creating an instance of NearestAttackableTargetGoalCodec
        NearestAttackableTargetGoalCodec nearestAttackableTargetGoalCodec = new NearestAttackableTargetGoalCodec(goalPriority, targetType, randomInterval, mustSee, mustReach, targetPredicate);

// Adding code block for NearestAttackableTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, nearestAttackableTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure a Nearest Attackable Target Goal with specific parameters for a mob.");

        builder.addParagraph("**Applied to:** Mobs that prioritize targeting the nearest living entity of a specified type, " +
                "based on defined parameters.");

// Source code reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/NearestAttackableTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Non-Tame Random Target Goal");

        builder.addParagraph("The Non-Tame Random Target Goal allows tamable animals to randomly select nearby entities of a specified type as their target, with optional conditions.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`target_goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority.",
                "`target_type`: Specifies the type of entity that the tamable animal will randomly target.",
                "`must_see`: Determines if the tamable animal must have line-of-sight to the target."
        });

        builder.addParagraph("**Optional Parameters:**");
        builder.addList(new String[]{
                "`random_interval`: Defines the interval (in ticks) at which the tamable animal re-evaluates its target selection randomly. Default is 10 ticks.",
                "`must_reach`: Specifies whether the tamable animal must be able to pathfind to the target entity.",
                "`target_predicate`: Allows customization of the target selection criteria using a predicate."
        });

        builder.addParagraph("**Example Usage of Non-Tame Random Target Goal:**");

// Example values for goal priority, entity type, and optional parameters
        //nt goalPriority = 1; // Example goal priority
        //agKey<EntityType<?>> targetType = EntityTypeTags.PLAYER; // Example target type
        //nt randomInterval = 15; // Example random interval
        //oolean mustSee = true; // Example must see flag
        //oolean mustReach = false; // Example must reach flag
        //redicateCodec<Entity> targetPredicate = new TruePredicate<>(); // Example target predicate

// Creating an instance of NonTameRandomTargetGoalCodec
        NonTameRandomTargetGoalCodec nonTameRandomTargetGoalCodec = new NonTameRandomTargetGoalCodec(goalPriority, targetType, randomInterval, mustSee, mustReach, targetPredicate);

// Adding code block for NonTameRandomTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, nonTameRandomTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure a Non-Tame Random Target Goal with specific parameters for a tamable animal.");

        builder.addParagraph("**Applied to:** Tamable animals that randomly select nearby entities of a specified type as targets, based on defined parameters.");

// Source code reference
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/NonTameRandomTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Owner Hurt By Target Goal");

        builder.addParagraph("The Owner Hurt By Target Goal enables tamable animals to prioritize attacking entities that have harmed their owner.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example Usage of Owner Hurt By Target Goal:**");

// Example value for goal priority
        //int goalPriority = 1; // Example goal priority

// Creating an instance of OwnerHurtByTargetGoalCodec
        OwnerHurtByTargetGoalCodec ownerHurtByTargetGoalCodec = new OwnerHurtByTargetGoalCodec(goalPriority);

// Adding code block for OwnerHurtByTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, ownerHurtByTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure an Owner Hurt By Target Goal with a specific priority for a tamable animal.");

        builder.addParagraph("**Applied to:** Tamable animals that prioritize retaliating against entities that have harmed their owner.");

// Source code reference
        builder.addLink("Source Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/OwnerHurtByTargetGoalCodec.java");

        builder.endCollapsibleSection();

        builder.startCollapsibleSection("Owner Hurt Target Goal");

        builder.addParagraph("The Owner Hurt Target Goal enables tamable animals to prioritize attacking entities that have hurt their owner.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                "`goal_priority`: Specifies the priority level of this goal. Lower numbers indicate higher priority."
        });

        builder.addParagraph("**Example Usage of Owner Hurt Target Goal:**");

// Example value for goal priority
        //int goalPriority = 1; // Example goal priority

// Creating an instance of OwnerHurtTargetGoalCodec
        OwnerHurtTargetGoalCodec ownerHurtTargetGoalCodec = new OwnerHurtTargetGoalCodec(goalPriority);

// Adding code block for OwnerHurtTargetGoalCodec instance
        builder.addCodeBlock(encodeDataToJsonString(TargetGoalCodec.DIRECT_CODEC, ownerHurtTargetGoalCodec));

        builder.addParagraph("This example demonstrates how to configure an Owner Hurt Target Goal with a specific priority for a tamable animal.");

        builder.addParagraph("**Applied to:** Tamable animals that prioritize retaliating against entities that have hurt their owner.");

// Source code reference
        builder.addLink("Source Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/goalcodec/targetgoalcodec/OwnerHurtTargetGoalCodec.java");

        builder.endCollapsibleSection();


        builder.addHeading("Currently Defined Entity Tags", 2);
        builder.startCollapsibleSection("Tags");
        BuiltInRegistries.ENTITY_TYPE.iterator().forEachRemaining(e -> {
            String name = e.getDefaultLootTable().getPath().split("/")[1];
            TagKey< EntityType<?> > tag = TagKey.create(Registries.ENTITY_TYPE, prefix(name));
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(name));
            builder.addCodeBlock(prefix(name).toString());
            builder.addFormattedParagraph("With value %s", new ResourceLocation(name));
        });
        builder.endCollapsibleSection();
        return builder;
    }

    private WikiPageBuilder attributesPage() {
        WikiPageBuilder builder = new WikiPageBuilder("attributes");

        // Introduction to Attributes
        builder.addHeading("Introduction to Attributes", 3);
        builder.addParagraph("Attributes in Minecraft are used to define various properties and behaviors of entities, such as health, speed, and attack damage.");
        builder.addParagraph(" Because some goals use attributes that an entity might not have you will need to add it.");


        // Example Usage
        builder.addHeading("Example Usage", 3);
        builder.addParagraph("Let's consider an example where we add a custom attribute to an entity:");

        // Create an example AttributesAdditions
        List<AttributesAdditions.AttributesMap> attributesMapList = new ArrayList<>();
        attributesMapList.add(new AttributesAdditions.AttributesMap(Attributes.MAX_HEALTH, 40.0));
        AttributesAdditions attributesAdditions = new AttributesAdditions(attributesMapList);

        // Encode AttributesAdditions to JSON string for display
        builder.addCodeBlock(encodeDataToJsonString(AttributesAdditions.CODEC, attributesAdditions));
        builder.addParagraph("In this example, the `AttributesAdditions` class is used to add a custom attribute (`MAX_HEALTH`) to an entity, setting its base value to 40.0. ");


        // Link to code repository (if applicable)
        builder.addLink("Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/AttributesAdditions.java");

        builder.addHeading("Available attributes",2);
        builder.startCollapsibleSection("Attributes");
        builder.addParagraph("The following attributes can be added to an entity");
        for(Map.Entry<ResourceKey<Attribute>, Attribute> attribute : BuiltInRegistries.ATTRIBUTE.entrySet()) {
            builder.addCodeBlock(String.valueOf(attribute.getKey().location()));
        }
        builder.endCollapsibleSection();
        return builder;
    }

    private WikiPageBuilder goalDataPage() {
        WikiPageBuilder builder = new WikiPageBuilder("goal");
        builder.startCollapsibleSection("Goal Data");

        builder.addParagraph("The Goal Data record defines the entity to target and specifies the operations for adding or modifying the entity's goals and attributes.");

        builder.addParagraph("**Parameters Required:**");
        builder.addList(new String[]{
                " `target_entity`: The entity type to which the goals and attributes will be applied, identified by its resource location.",
                " `goal_operations`:(optional value) A list of operations that specify the goals to add or modify.",
                " `target_goal_operations`:(optional value) A list of operations that specify the target goals to add or modify.",
                " `attributes_additions`:(optional value) A list of attribute additions to apply to the target entity."
        });

        builder.addParagraph("**Example Usage of Goal Data:**");

// Example values for GoalData parameters
        ResourceLocation targetEntity = new ResourceLocation("minecraft", "cow");
        List<GoalOperation> goalOperations = List.of(new AddOperation(List.of(new WaterFleeGoalCodec(1)))); // Add appropriate GoalOperation instances
        List<TargetGoalOperation> targetGoalOperations = List.of(new AddTargetOperation(List.of(new NearestAttackableTargetGoalCodec(1, TagKey.create(Registries.ENTITY_TYPE, prefix("player")), 10, true, false, new TruePredicate<>())))); // Add appropriate TargetGoalOperation instances
        List<AttributesAdditions> attributeAdditions = List.of(new AttributesAdditions(List.of(new AttributesAdditions.AttributesMap(Attributes.ATTACK_DAMAGE, 10D)))); // Add appropriate AttributesAdditions instances

// Creating an instance of GoalData
        GoalData goalData = new GoalData(targetEntity, goalOperations, targetGoalOperations, attributeAdditions);

// Adding code block for GoalData instance
        builder.addCodeBlock(encodeDataToJsonString(GoalData.CODEC, goalData));

        builder.addParagraph("This example demonstrates how to configure Goal Data with specific parameters for targeting an entity and modifying its goals and attributes.");

        builder.addParagraph("**Applied to:** Entities that require custom goal and attribute configurations based on the specified operations and additions.");
        builder.addParagraph("The Json file should be in the following location: `data/<ID>/goalsmith/goaldata`");

// Source code reference
        builder.addLink("Source Code", "https://github.com/Scouter456/Goal_Smith/tree/goal_smith_forge_1.20.1/src/main/java/com/scouter/goalsmith/data/GoalData.java");

        builder.endCollapsibleSection();
        return builder;
    }

    private void createWikiPage(String filename, WikiPageBuilder builder, BiConsumer<String, Supplier<String>> consumer){
        consumer.accept(filename, builder::getContent);
        contentsGenerator.addPage(builder);
    }


}
