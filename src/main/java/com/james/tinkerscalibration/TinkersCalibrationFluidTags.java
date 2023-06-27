package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

import javax.annotation.Nullable;

public class TinkersCalibrationFluidTags extends FluidTagsProvider {
    public TinkersCalibrationFluidTags(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, TinkersCalibration.MODID, existingFileHelper);
    }
    @Override
    protected void addTags() {
        tagAll(TinkersCalibrationFluids.moltenfiberglass);
        tagAll(TinkersCalibrationFluids.moltenmangobberslime);
        tagAll(TinkersCalibrationFluids.moltengobber);
        tagAll(TinkersCalibrationFluids.moltennethergobber);
        tagAll(TinkersCalibrationFluids.moltenfazelle);
        tagAll(TinkersCalibrationFluids.moltenemperorslime);
        tagAll(TinkersCalibrationFluids.moltenlindsteel);
        tagAll(TinkersCalibrationFluids.moltentitanium);
        tagAll(TinkersCalibrationFluids.moltenmoltenhymon);
        tagAll(TinkersCalibrationFluids.moltenmandite);
        tagAll(TinkersCalibrationFluids.moltencarminite);
        tagAll(TinkersCalibrationFluids.moltenredmatter);
        tagAll(TinkersCalibrationFluids.moltenendgobber);
        tagAll(TinkersCalibrationFluids.moltensoulgold);
        tagAll(TinkersCalibrationFluids.moltenichor);
        tagAll(TinkersCalibrationFluids.moltenrefinedquartz);
        tagAll(TinkersCalibrationFluids.dragonbreath);
        tag(TinkersCalibrationFluids.moltenemperorslime.getLocalTag()).addTag(TinkerTags.Fluids.METAL_TOOLTIPS);
    }
    private void tagLocal(FluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    private void tagAll(FluidObject<?> fluid) {
        tagLocal(fluid);
        tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }
}
