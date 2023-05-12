package com.james.tinkerscalibration;

import com.james.tinkerscalibration.modifiers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.function.IntFunction;
import java.util.function.Supplier;


public class Utils extends TinkerModule {
    public static final String MOD_ID = "tinkerscalibration";
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TinkersCalibration.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinkersCalibration.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static final MaterialId fiberglass = createMaterial("fiberglass");
    public static final MaterialId obsidian = createMaterial("obsidian");
    public static final MaterialId netherbrick = createMaterial("nether_brick");
    public static final MaterialId bamboosteel = createMaterial("bamboo_steel");
    public static final MaterialId purpur = createMaterial("purpur");
    public static final MaterialId refinedquartz = createMaterial("refinedquartz");
    public static final MaterialId lindsteel = createMaterial("lindsteel");
    public static final MaterialId gobber = createMaterial("gobber");
    public static final MaterialId gobbernether = createMaterial("gobbernether");
    public static final MaterialId mangobberslime = createMaterial("mangobberslime");
    public static final MaterialId emperorslime = createMaterial("emperorslime");
    public static final MaterialId hymon = createMaterial("hymon");
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    public static RegistryObject<TinkerEffect> impregnableEffect = MOB_EFFECTS.register("impregnable", MARKER_EFFECT.apply(0x30236c));
    public static StaticModifier<Modifier> sharplikeglass = MODIFIERS.register("sharp_like_glass", SharpLikeGlassModifier::new);
    public static StaticModifier<Modifier> lightlikeglass = MODIFIERS.register("light_like_glass", LightLikeGlassModifier::new);
    public static StaticModifier<Modifier> fragilelikeglass = MODIFIERS.register("fragile_like_glass", FragileLikeGlassModifier::new);
    public static StaticModifier<Modifier> purgator = MODIFIERS.register("purgator", PurgatorModifier::new);
    public static StaticModifier<Modifier> levitate = MODIFIERS.register("levitate", LevitateModifier::new);
    public static StaticModifier<Modifier> bamboogrowing = MODIFIERS.register("bamboo_growing", BambooGrowingModifier::new);
    public static StaticModifier<Modifier> refined = MODIFIERS.register("refined", RefinedModifier::new);
    public static StaticModifier<Modifier> impregnable = MODIFIERS.register("impregnable", ImpregnableModifier::new);
    public static StaticModifier<Modifier> gobberbless = MODIFIERS.register("gobber_bless", GobberBlessModifier::new);
    public static StaticModifier<Modifier> gobbercurse = MODIFIERS.register("gobber_curse", GobberCurseModifier::new);
    public static StaticModifier<Modifier> gobberblessnether = MODIFIERS.register("gobber_bless_nether", GobberBlessNetherModifier::new);
    public static StaticModifier<Modifier> gobbercursenether = MODIFIERS.register("gobber_curse_nether", GobberCurseNetherModifier::new);
    public static StaticModifier<Modifier> united = MODIFIERS.register("united", UnitedModifier::new);
    public static StaticModifier<Modifier> overslimeempire = MODIFIERS.register("overslime_empire", OverslimeEmpireModifier::new);
    public static StaticModifier<Modifier> overslimeexpedition = MODIFIERS.register("overslime_army", OverslimeArmyModifier::new);
    public static StaticModifier<Modifier> hydrophilous = MODIFIERS.register("hydrophilous", HydrophilousModifier::new);
    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(TinkersCalibration.MODID, name));
    }
}
