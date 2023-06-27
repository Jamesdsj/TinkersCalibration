package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class HydrophilousModifier extends Modifier{
    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        Player player = event.getPlayer();
        if(player != null && player.isEyeInFluid(FluidTags.WATER)) {
            if(player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                int amplifier = player.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier() + 1;
                double scale;
                if(amplifier <= 3) {
                    scale = Math.pow(3, amplifier);
                }
                else {
                    scale = Math.pow(3, 4);
                }
                event.setNewSpeed((float) (event.getNewSpeed() * scale));
            }
            else if (!ModifierUtil.hasAquaAffinity(player)) {
                event.setNewSpeed(event.getNewSpeed() * 5);
            }
            else {
                event.setNewSpeed(event.getNewSpeed() + 5 * level);
            }
        }
    }
    @Override
    public int getPriority() {
        return 60;
    }
    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        if(player.isEyeInFluid(FluidTags.WATER)) {
            return damage + level * 5;
        }
        return damage;
    }
    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        if(player.isEyeInFluid(FluidTags.WATER)) {
            addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 5 * level, tooltip);
            if(player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                int amplifier = player.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier() + 1;
                double scale, percent;
                if(amplifier <= 3) {
                    scale = Math.pow(3, amplifier);
                    percent = Math.pow(0.3, amplifier);
                }
                else {
                    scale = Math.pow(3, 4);
                    percent = Math.pow(0.3, 4);
                }
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.hydrophilous.mining_speed1"), percent * scale, tooltip);
            }
            else if (!ModifierUtil.hasAquaAffinity(player)) {
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.hydrophilous.mining_speed2"), 5f, tooltip);
            }
            else
            {
                addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 5 * level, tooltip);
            }


        }
    }
}
