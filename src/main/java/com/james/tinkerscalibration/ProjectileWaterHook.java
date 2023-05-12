package com.james.tinkerscalibration;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Function;

public interface ProjectileWaterHook {
    slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook EMPTY = (tool, modifier, shooter, projectile, arrow, persistentData, primary) -> {};

    Function<Collection<slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook>, slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook> ALL_MERGER = slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook.AllMerger::new;

    void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable ArrowWater arrow, NamespacedNBT persistentData, boolean primary);

    /** Logic to merge multiple hooks into one */
    record AllMerger(Collection<com.james.tinkerscalibration.ProjectileWaterHook> modules) implements com.james.tinkerscalibration.ProjectileWaterHook {
        @Override
        public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable ArrowWater arrow, NamespacedNBT persistentData, boolean primary) {
            for (com.james.tinkerscalibration.ProjectileWaterHook module : modules) {
                module.onProjectileLaunch(tool, modifier, shooter, projectile, arrow, persistentData, primary);
            }
        }
    }
}

