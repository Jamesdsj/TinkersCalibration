package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

import javax.annotation.Nullable;
public class TinkersCalibrationItemTags extends ItemTagsProvider {
    private static TagKey<Item> create(String name) {
        return ItemTags.create(new ResourceLocation(name));
    }

    public TinkersCalibrationItemTags(DataGenerator gen, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, blockTagsProvider, TinkersCalibration.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkersCalibrationTags.Items.MANGOBBERSLIME_INGOT).add(TinkersCalibrationMaterialItems.mangobberslime_ingot.get());
        tag(TinkersCalibrationTags.Items.FAZELLE_INGOT).add(TinkersCalibrationMaterialItems.fazelle_ingot.get());
        tag(TinkersCalibrationTags.Items.LINDSTEEL_INGOT).add(TinkersCalibrationMaterialItems.lindsteel_ingot.get());
        tag(TinkersCalibrationTags.Items.EMPERORSLIME_INGOT).add(TinkersCalibrationMaterialItems.emperorslime_ingot.get());
        tag(TinkersCalibrationTags.Items.TITANIUM_INGOT).add(TinkersCalibrationMaterialItems.titanium_ingot.get());
        tag(TinkersCalibrationTags.Items.MANDITE_INGOT).add(TinkersCalibrationMaterialItems.mandite_ingot.get());
        tag(TinkersCalibrationTags.Items.MANGOBBERSLIME_NUGGET).add(TinkersCalibrationMaterialItems.mangobberslime_nugget.get());
        tag(TinkersCalibrationTags.Items.FAZELLE_NUGGET).add(TinkersCalibrationMaterialItems.fazelle_nugget.get());
        tag(TinkersCalibrationTags.Items.LINDSTEEL_NUGGET).add(TinkersCalibrationMaterialItems.lindsteel_nugget.get());
        tag(TinkersCalibrationTags.Items.EMPERORSLIME_NUGGET).add(TinkersCalibrationMaterialItems.emperorslime_nugget.get());
        tag(TinkersCalibrationTags.Items.TITANIUM_NUGGET).add(TinkersCalibrationMaterialItems.titanium_nugget.get());
        tag(TinkersCalibrationTags.Items.MANDITE_NUGGET).add(TinkersCalibrationMaterialItems.mandite_nugget.get());
    }
}
