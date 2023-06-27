package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class TinkersCalibrationItemsModels extends ItemModelProvider {
    public TinkersCalibrationItemsModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TinkersCalibration.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemWithModel(TinkersCalibrationMaterialItems.mangobberslime_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.lindsteel_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.fazelle_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.titanium_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.mandite_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.emperorslime_ingot, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.mangobberslime_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.lindsteel_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.fazelle_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.titanium_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.mandite_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.emperorslime_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.mangobberslime_nugget, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.hymon, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.hymon_scrap, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.glass_silk, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.ocean_compound, "item/generated");
        itemWithModel(TinkersCalibrationMaterialItems.breashell, "item/generated");
    }
    public void itemWithModel(RegistryObject<? extends Item> registryObject, String model) {
        ResourceLocation id = registryObject.getId();
        ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
        singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
    }
}
