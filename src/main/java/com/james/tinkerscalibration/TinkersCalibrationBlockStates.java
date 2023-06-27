package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.FluidObject;

public class TinkersCalibrationBlockStates extends BlockStateProvider {

    public TinkersCalibrationBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TinkersCalibration.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fluid(TinkersCalibrationFluids.moltenfiberglass);
        fluid(TinkersCalibrationFluids.moltenmangobberslime);
        fluid(TinkersCalibrationFluids.moltengobber);
        fluid(TinkersCalibrationFluids.moltennethergobber);
        fluid(TinkersCalibrationFluids.moltenfazelle);
        fluid(TinkersCalibrationFluids.moltenemperorslime);
        fluid(TinkersCalibrationFluids.moltenlindsteel);
        fluid(TinkersCalibrationFluids.moltentitanium);
        fluid(TinkersCalibrationFluids.moltenmoltenhymon);
        fluid(TinkersCalibrationFluids.moltenmandite);
        fluid(TinkersCalibrationFluids.moltencarminite);
        fluid(TinkersCalibrationFluids.moltenredmatter);
        fluid(TinkersCalibrationFluids.moltenendgobber);
        fluid(TinkersCalibrationFluids.moltensoulgold);
        fluid(TinkersCalibrationFluids.moltenichor);
        fluid(TinkersCalibrationFluids.moltenrefinedquartz);
        fluid(TinkersCalibrationFluids.dragonbreath);
        blockWithItem(TinkersCalibrationBlocks.lindsteel_block);
        blockWithItem(TinkersCalibrationBlocks.emperorslime_block);
    }
    public void blockWithItem(RegistryObject<Block> registryObject) {
        //block model
        simpleBlock(registryObject.get());
        //itemblock model
        ResourceLocation id = registryObject.getId();
        ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
        itemModels().cubeAll(id.getPath(), textureLocation);
    }
    public void fluid(FluidObject<ForgeFlowingFluid> fluid) {
        ResourceLocation name = fluid.getBlock().getRegistryName();
        simpleBlock(fluid.getBlock(), models().cubeAll(name.getPath().replace("_fluid", ""), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/fluid/" + name.getPath().replace("_fluid", "")+"/still")));
        itemModels().cubeAll(name.getPath(), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/fluid/" + name.getPath().replace("_fluid", "")+"/still"));
    }
}
