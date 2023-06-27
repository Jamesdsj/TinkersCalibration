package com.james.tinkerscalibration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

public class TinkersCalibrationFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TinkersCalibration.MODID);
    public static FluidObject<ForgeFlowingFluid> moltenfiberglass = register("moltenfiberglass", 750);
    public static FluidObject<ForgeFlowingFluid> moltenmangobberslime = register("moltenmangobberslime", 1500);
    public static FluidObject<ForgeFlowingFluid> moltengobber = register("moltengobber", 800);
    public static FluidObject<ForgeFlowingFluid> moltennethergobber = register("moltennethergobber", 950);
    public static FluidObject<ForgeFlowingFluid> moltenfazelle = register("moltenfazelle", 1200);
    public static FluidObject<ForgeFlowingFluid> moltenemperorslime = register("moltenemperorslime", 1150);
    public static FluidObject<ForgeFlowingFluid> moltenlindsteel = register("moltenlindsteel", 1200);
    public static FluidObject<ForgeFlowingFluid> moltentitanium = register("moltentitanium", 1350);
    public static FluidObject<ForgeFlowingFluid> moltenmoltenhymon = register("moltenhymon", 550);
    public static FluidObject<ForgeFlowingFluid> moltenmandite = register("moltenmandite", 1450);
    public static FluidObject<ForgeFlowingFluid> moltencarminite = register("moltencarminite", 900);
    public static FluidObject<ForgeFlowingFluid> moltenredmatter = register("moltenredmatter", 1500);
    public static FluidObject<ForgeFlowingFluid> moltenendgobber = register("moltenendgobber", 1100);
    public static FluidObject<ForgeFlowingFluid> moltensoulgold = register("moltensoulgold", 1000);
    public static FluidObject<ForgeFlowingFluid> moltenrefinedquartz = register("moltenrefinedquartz", 1200);

    public static FluidObject<ForgeFlowingFluid> moltenichor = register("moltenichor", 1000);
    public static FluidObject<ForgeFlowingFluid> dragonbreath = register("dragonbreath", 2000);
    private static FluidObject<ForgeFlowingFluid> register(String name, int temp) {
        String still = String.format("%s:block/fluid/%s/still", TinkersCalibration.MODID, name);
        String flow = String.format("%s:block/fluid/%s/flowing", TinkersCalibration.MODID, name);
        return FLUIDS.register(name, FluidAttributes.builder(new ResourceLocation(still), new ResourceLocation(flow)).density(2000).viscosity(10000).temperature(temp).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);
    }
}
