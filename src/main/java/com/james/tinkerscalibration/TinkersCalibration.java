package com.james.tinkerscalibration;
import com.james.tinkerscalibration.item.FiberGlass;
import com.james.tinkerscalibration.item.HymonArrow;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.List;

@Mod(Utils.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersCalibration {
    public TinkersCalibration() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TinkersCalibrationConfig.COMMON_CONFIG);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setup);
        TinkersCalibrationFluids.FLUIDS.register(bus);
        TinkersCalibrationBlocks.BLOCKS.register(bus);
        Utils.ITEMS.register(bus);
        TinkersCalibrationMaterialItems.ITEMS.register(bus);
        Utils.MODIFIERS.register(bus);
        FiberGlass.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HymonArrow.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Utils.RECIPE_SERIALIZERS.register(bus);
    }

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if(event.includeClient())
        {
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            TinkersCalibrationMaterialsSprite materialSprites = new TinkersCalibrationMaterialsSprite();
            gen.addProvider(new TinkersCalibrationRenderInfo(gen, materialSprites));
            AbstractMaterialSpriteProvider provider = new TinkersCalibrationMaterialsSprite();
            gen.addProvider(new MaterialPartTextureGenerator(gen, fileHelper, new TinkerPartSpriteProvider(), materialSprites));
            gen.addProvider(new TinkersCalibrationBlockStates(gen, fileHelper));
            gen.addProvider(new MaterialPartTextureGenerator(gen, fileHelper, new TinkerPartSpriteProvider(), provider));
            gen.addProvider(new TinkersCalibrationItemsModels(gen, fileHelper));
            gen.addProvider(new TinkersCalibrationRecipes(gen));
            gen.addProvider(new TinkersCalibrationLootTables(gen));
        }
        if (event.includeServer()) {
            TinkersCalibrationBlockTags tags = new TinkersCalibrationBlockTags(gen, fileHelper);
            gen.addProvider(tags);
            gen.addProvider(new TinkersCalibrationFluidTags(gen, fileHelper));
            gen.addProvider(new TinkersCalibrationItemTags(gen, tags, fileHelper));
        }
    }
    private void setup(final FMLCommonSetupEvent event) {
            if (!TierSortingRegistry.isTierSorted(GobberTier.instance)) {
                if (TierSortingRegistry.getSortedTiers().size() != 0) {
                    TierSortingRegistry.registerTier(GobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":gobber"), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
                } else {
                    TierSortingRegistry.registerTier(GobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":gobber"), List.of(Tiers.NETHERITE), List.of());
                }
            }
            if (!TierSortingRegistry.isTierSorted(NetherGobberTier.instance)) {
                if (TierSortingRegistry.getSortedTiers().size() != 0) {
                    TierSortingRegistry.registerTier(NetherGobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":nethergobber"), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
                } else {
                    TierSortingRegistry.registerTier(NetherGobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":nethergobber"), List.of(Tiers.NETHERITE), List.of());
                }
            }
            if (!TierSortingRegistry.isTierSorted(EndGobberTier.instance)) {
                if (TierSortingRegistry.getSortedTiers().size() != 0) {
                    TierSortingRegistry.registerTier(EndGobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":endgobber"), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
                } else {
                    TierSortingRegistry.registerTier(EndGobberTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":endgobber"), List.of(Tiers.NETHERITE), List.of());
                }
            }


            if (!TierSortingRegistry.isTierSorted(RedMatterTier.instance)) {
                if (TierSortingRegistry.getSortedTiers().size() != 0) {
                    TierSortingRegistry.registerTier(RedMatterTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":redmatter"), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
                } else {
                    TierSortingRegistry.registerTier(RedMatterTier.instance, new ResourceLocation(TinkersCalibration.MODID + ":redmatter"), List.of(Tiers.NETHERITE), List.of());
                }
            }

    }
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tinkerscalibration";

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(ModGroup.itemGroup);
    }
    public static Item register() {
        return new Item(new Item.Properties().tab(ModGroup.itemGroup));
    }
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    public static RegistryObject<Item> Fiberglass = Items.register("fiberglass", TinkersCalibration::register);
    public static RegistryObject<Item> Hymon_Arrow = Items.register("hymon_arrow", TinkersCalibration::register);
    public static Logger getLogger() {
        return LOGGER;
    }

}