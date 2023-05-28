package com.james.tinkerscalibration;

import com.james.tinkerscalibration.modifiers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
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
import slimeknights.tconstruct.tools.modifiers.traits.melee.LaceratingModifier;

import java.util.function.IntFunction;
import java.util.function.Supplier;


public class Utils extends TinkerModule{
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
    public static final MaterialId fazelle = createMaterial("fazelle");
    public static final MaterialId blazewood = createMaterial("blazewood");
    public static final MaterialId carminite = createMaterial("carminite");
    public static final MaterialId soulgold = createMaterial("soulgold");
    public static final MaterialId andesitalloy = createMaterial("andesitalloy");
    public static final MaterialId cupronickel = createMaterial("cupronickel");
    public static final MaterialId redmatter = createMaterial("redmatter");
    public static final MaterialId titanium = createMaterial("titanium");
    public static final MaterialId breashell = createMaterial("breashell");
    private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT = color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);
    public static RegistryObject<TinkerEffect> impregnableEffect = MOB_EFFECTS.register("impregnable", MARKER_EFFECT.apply(0x30236c));
    public static StaticModifier<Modifier> sharplikeglass = MODIFIERS.register("sharp_like_glass", SharpLikeGlassModifier::new);
    public static StaticModifier<Modifier> lightlikeglass = MODIFIERS.register("light_like_glass", LightLikeGlassModifier::new);
    public static StaticModifier<Modifier> fragilelikeglass = MODIFIERS.register("fragile_like_glass", FragileLikeGlassModifier::new);
    public static StaticModifier<Modifier> purgatory = MODIFIERS.register("purgatory", PurgatoryModifier::new);
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
    public static StaticModifier<Modifier> dash = MODIFIERS.register("dash", DashModifier::new);
    public static StaticModifier<Modifier> darkness = MODIFIERS.register("darkness", DarknessModifier::new);
    public static StaticModifier<Modifier> stonecold = MODIFIERS.register("stone_cold", StoneColdModifier::new);
    public static StaticModifier<Modifier> vengeance = MODIFIERS.register("vengeance", VengeanceModifier::new);
    public static StaticModifier<Modifier> igneous = MODIFIERS.register("igneous", IgneousModifier::new);
    public static StaticModifier<Modifier> beekeeper = MODIFIERS.register("bee_keeper", BeeKeeperModifier::new);
    public static StaticModifier<Modifier> familiar = MODIFIERS.register("familiar", FamiliarModifier::new);
    public static StaticModifier<Modifier> subdue = MODIFIERS.register("subdue", SubdueModifier::new);
    public static StaticModifier<Modifier> dominate = MODIFIERS.register("dominate", DominateModifier::new);
    public static StaticModifier<Modifier> airblade = MODIFIERS.register("airblade", AirBladeModifier::new);
    public static StaticModifier<Modifier> laceratingtitanium = MODIFIERS.register("laceratingtitanium", LaceratingModifier::new);
    public static StaticModifier<Modifier> criticalattack = MODIFIERS.register("criticalattack", CriticalAttackModifier::new);
    private static IEventBus HymonArrow;

    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(TinkersCalibration.MODID, name));
    }
}
