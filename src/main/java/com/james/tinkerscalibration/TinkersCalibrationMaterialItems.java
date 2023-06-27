package com.james.tinkerscalibration;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TinkersCalibrationMaterialItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    public static RegistryObject<Item> mangobberslime_ingot = ITEMS.register("mangobberslime_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> lindsteel_ingot = ITEMS.register("lindsteel_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> fazelle_ingot = ITEMS.register("fazelle_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> titanium_ingot = ITEMS.register("titanium_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> mandite_ingot = ITEMS.register("mandite_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> emperorslime_ingot = ITEMS.register("emperorslime_ingot", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> hymon = ITEMS.register("hymon", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> mangobberslime_nugget = ITEMS.register("mangobberslime_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> lindsteel_nugget = ITEMS.register("lindsteel_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> fazelle_nugget = ITEMS.register("fazelle_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> titanium_nugget = ITEMS.register("titanium_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> mandite_nugget = ITEMS.register("mandite_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> emperorslime_nugget = ITEMS.register("emperorslime_nugget", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> glass_silk = ITEMS.register("glass_silk", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> hymon_scrap = ITEMS.register("hymon_scrap", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> ocean_compound = ITEMS.register("ocean_compound", TinkersCalibrationMaterialItems::register);
    public static RegistryObject<Item> breashell = ITEMS.register("breashell", TinkersCalibrationMaterialItems::register);
    public static Item register() {
        return new Item(new Item.Properties().tab(ModGroup.itemGroup));
    }
}
