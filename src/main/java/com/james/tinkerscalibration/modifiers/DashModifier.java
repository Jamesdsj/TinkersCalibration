package com.james.tinkerscalibration.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nonnull;
import java.util.List;

public class DashModifier extends Modifier implements ConditionalStatModifierHook {
    public float getbonus(float speed, int level, int status)
    {
        return speed * level * status;
    }
    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        if (context.getLivingTarget() != null) {
            Player player = context.getPlayerAttacker();
            float speed = player.getSpeed();
            float bonus;
            if (player != null && player.isSprinting()) {
                bonus = getbonus(speed, level, 20);
                return damage * (1 + bonus);
            }
            return damage;
        }
        return damage;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        int level = modifier.getLevel();
        float speed = living.getSpeed();
        float bonus;
        if(living != null && living.isSprinting()) {
            bonus = getbonus(speed, level, 20);
            if (stat == ToolStats.DRAW_SPEED) {
                return baseValue * (1 + bonus);
            }
            return baseValue;
        }
        return baseValue;
    }
    @Override
    public void addInformation(@Nonnull IToolStackView tool, int level, @Nullable Player player, @Nonnull List<Component> tooltip, @Nonnull TooltipKey tooltipKey, @Nonnull TooltipFlag tooltipFlag) {
        if(player != null) {
            float speed = player.getSpeed();
            float bonus = getbonus(speed, level, 20);
            boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
            if (player != null && player.isSprinting()) {
                if(harvest) {
                    addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.dash.attack_damage"), bonus, tooltip);
                    addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.dash.draw_speed"), bonus, tooltip);
                }
                else
                {
                    addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.dash.draw_speed"), bonus, tooltip);
                }
            }
        }
    }

}
