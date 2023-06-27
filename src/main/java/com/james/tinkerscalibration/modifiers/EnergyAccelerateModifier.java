package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nonnull;
import java.util.List;

public class EnergyAccelerateModifier extends EnergyStorageModifier implements ConditionalStatModifierHook {
    private static final int ENERGY_COST = 500;

    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective && !tool.isBroken() && EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, true, false)) {
            event.setNewSpeed(event.getNewSpeed() + 6f * level * tool.getMultiplier(ToolStats.MINING_SPEED));
        }
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        if (!context.isAOE() && context.isEffective() && !tool.isBroken()) {
            EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, false, false);
        }
    }
    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        if (!tool.isBroken() && EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, true, false)) {
            EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level * 3, false, false);
            return damage + 6 * level;
        }
        return damage;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        int level = modifier.getLevel();
        if (!tool.isBroken() && EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, true, false)) {
            if (stat == ToolStats.DRAW_SPEED) {
                EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, false, false);
                return baseValue + 0.3f * level;
            }
        }
        return baseValue;
    }
    @Override
    public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        super.addInformation(tool, level, player, tooltip, key, flag);
        float bonus = 0;
        if (EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, true, false))
            bonus = 6f * level;
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if(harvest) {
            addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, bonus, tooltip);
            addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, bonus, tooltip);
        }
        else {
            addStatTooltip(tool, ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, bonus / 20, tooltip);
        }
    }
}
