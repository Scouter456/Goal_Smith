package com.scouter.goalsmith.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.data.goalcodec.MeleeAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.NearestAttackableTargetGoalCodec;
import com.scouter.goalsmith.data.operation.goal.ReplaceOperation;
import com.scouter.goalsmith.data.operation.target.AddTargetOperation;
import com.scouter.goalsmith.data.predicates.IsCreativePredicate;
import com.scouter.goalsmith.data.predicates.TruePredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;

import java.util.ArrayList;
import java.util.List;
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
        AttributesAdditions.AttributesMap attm = new AttributesAdditions.AttributesMap(Attributes.ATTACK_DAMAGE, 10, UUID.randomUUID());
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

        // Generate the Table of Contents
        contentsGenerator.addPage(homePage());
    }


    private WikiPageBuilder homePage() {
        WikiPageBuilder builder = new WikiPageBuilder("Home");
        builder.addParagraph("Welcome to the Goal Smith Wiki!");
        builder.addHeading("Goals", 1);
        builder.addParagraph("**Example of GoalData:**");
        return builder;
    }

    private WikiPageBuilder predicatePage() {
        WikiPageBuilder builder = new WikiPageBuilder("Predicates");
        IsCreativePredicate isCreativePredicate = new IsCreativePredicate();

        builder.addParagraph("Welcome to the Goal Smith Wiki!");
        builder.addHeading("Goals", 1);
        builder.startCollapsibleSection("goal 1");
        builder.addParagraph("**Example of GoalData:**");
        builder.addCodeBlock(encodeDataToJsonString(PredicateCodec.DIRECT_CODEC, isCreativePredicate));
        builder.endCollapsibleSection();
        return builder;
    }




    private void createWikiPage(String filename, WikiPageBuilder builder, BiConsumer<String, Supplier<String>> consumer){
        consumer.accept(filename, builder::getContent);
        contentsGenerator.addPage(builder);
    }


}
