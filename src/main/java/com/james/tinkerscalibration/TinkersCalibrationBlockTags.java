package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TinkersCalibrationBlockTags extends BlockTagsProvider {
    public TinkersCalibrationBlockTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, TinkersCalibration.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
