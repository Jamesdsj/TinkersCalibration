package com.james.tinkerscalibration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TinkersCalibrationBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinkersCalibration.MODID);
    private static final BlockBehaviour.Properties METAL = Block.Properties.of(Material.METAL).strength(5F, 1200f).sound(SoundType.METAL);
    public static RegistryObject<Block> lindsteel_block = BLOCKS.register("lindsteel_block", () -> new Block(METAL));
    public static RegistryObject<Block> emperorslime_block = BLOCKS.register("emperorslime_block", () -> new Block(METAL));
}
