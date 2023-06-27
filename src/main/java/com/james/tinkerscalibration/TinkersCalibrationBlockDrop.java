package com.james.tinkerscalibration;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.stream.Collectors;

public class TinkersCalibrationBlockDrop extends BlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter((block) -> TinkersCalibration.MODID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                .collect(Collectors.toList());
    }
    @Override
    protected void addTables() {
        this.dropSelf(TinkersCalibrationBlocks.lindsteel_block.get());
        this.dropSelf(TinkersCalibrationBlocks.emperorslime_block.get());
    }
}
