package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.ArrowWater;
import com.james.tinkerscalibration.ProjectileWaterHook;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.List;

public class HydrophilousModifier extends Modifier implements ProjectileLaunchModifierHook {
    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        {
            Player player = event.getPlayer();
            if (!ModifierUtil.hasAquaAffinity(player) && player.isEyeInFluid(FluidTags.WATER)) {
                event.setNewSpeed(event.getOriginalSpeed() * 5);
            }
        }
    }
    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        if (arrow != null) {
            arrow.setNoGravity(true);
            arrow.setNoPhysics(true);
            double x = arrow.getX();
            double y = arrow.getY();
            double z = arrow.getZ();

            if(arrow.isInWater()) {
                arrow.shoot(x, y, z, 10, 0);

                arrow.setDeltaMovement(x * 1.67, y * 1.67, z * 1.67);
            }
        }
    }


    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        // gives +2 damage per level at max
        Player player = context.getPlayerAttacker();
        if(player.isEyeInFluid(FluidTags.WATER)) {
            return damage + level * 2;
        }
        return damage;
    }
    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        if(player.isEyeInFluid(FluidTags.WATER)) {
            addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 2 * level, tooltip);
        }
    }
}
