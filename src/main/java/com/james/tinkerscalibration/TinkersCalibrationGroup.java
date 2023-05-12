package com.james.tinkerscalibration;

import com.james.tinkerscalibration.item.FiberGlass;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
public class TinkersCalibrationGroup extends CreativeModeTab {
    public TinkersCalibrationGroup() {
        super("TinkersCalibrationGroup");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(FiberGlass.ItemRegistry.fiberglass.get());
    }
}
