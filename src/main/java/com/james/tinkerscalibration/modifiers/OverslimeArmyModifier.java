package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static slimeknights.mantle.item.RetexturedBlockItem.addTooltip;

public class OverslimeArmyModifier extends Modifier implements ProjectileLaunchModifierHook, ConditionalStatModifierHook {
    @Override
    public int getPriority() {
        return 70;
    }
    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int current = overslime.getOverslime(tool);
        if (current > 0) {
            event.setNewSpeed((float) (event.getNewSpeed() + current * 0.012));
        }

    }

    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int current = overslime.getOverslime(tool);
        if (current > 0) {
            return (float) (damage + current * 0.012);
        }
        return damage;
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        float current = overslime.getOverslime(tool);
        if (stat == ToolStats.DRAW_SPEED) {
            return (float) (baseValue + current * 0.002);
        }
        return baseValue;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int current = overslime.getOverslime(tool);
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
            if (player != null && key == TooltipKey.SHIFT) {
            if(harvest) {
                addDamageTooltip(tool, (float) (current * 0.012), tooltip);
                addSpeedTooltip(tool, (float) (current * 0.012), tooltip);
            }
            else
            {
                addStatTooltip(tool, ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, (float) (current * 0.002), tooltip);
            }
            }
        }
    }
    protected void addSpeedTooltip(IToolStackView tool, float amount, List<Component> tooltip) {
        addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.MELEE_OR_UNARMED, amount, tooltip);
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @org.jetbrains.annotations.Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {

    }
}


