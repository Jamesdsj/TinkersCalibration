package com.james.tinkerscalibration.modifiers;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.impl.IncrementalModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;

public class BambooGrowingModifier extends IncrementalModifier {
    private static int getBonus(LivingEntity living) {
        int bonus = 0;
        if (living.isEyeInFluid(FluidTags.WATER)) {
            bonus = 4;
        } else if (living.getCommandSenderWorld().isRainingAt(living.blockPosition())) {
            bonus = 2;
        }
        return bonus;
    }
    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, int level, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){;
        if (!world.isClientSide && holder.tickCount % 8 == 0 && holder.getUseItem() != stack && isSelected) {
            int bonus = getBonus(holder);
            if (bonus > 0 && RANDOM.nextFloat() < (level * 0.25) && !tool.isBroken()) {
                ToolDamageUtil.repair(tool, bonus);
            }
        }
    }
}

