package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DarknessModifier extends Modifier implements ConditionalStatModifierHook {
    public int getLight(Level world, BlockPos pos)
    {
        return Math.max(world.getBrightness(LightLayer.SKY, pos) - world.getSkyDarken(), world.getBrightness(LightLayer.BLOCK, pos));
    }

    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        Player player = event.getPlayer();
        Level world = player.getCommandSenderWorld();
        BlockPos pos = event.getPos().above();
        int light = getLight(world, pos) + 1;
        if (light < 10) {
            event.setNewSpeed((float) (event.getNewSpeed() * (1 + 0.5 * level / light)));
        }
    }
    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        Level world = player.getCommandSenderWorld();
        BlockPos pos = player.getOnPos().above();
        int light = getLight(world, pos) + 1;
        if (light < 10) {
            return (float) (damage * (1 + 0.5 * level / light));
        }
        return damage;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        Level world = living.getCommandSenderWorld();
        int level = modifier.getLevel();
        BlockPos pos = living.getOnPos().above();
        int light = getLight(world, pos) + 1;
        if (light < 10) {
            if (stat == ToolStats.DRAW_SPEED) {
                return (float) (baseValue * (1 + 0.5 * level / light));
            }
        }
        return 0;
    }
    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (player != null) {
            if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
                if (key == TooltipKey.SHIFT) {
                    if (harvest) {
                        addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.darkness.attack_damage"), 0.5 * level, tooltip);
                        addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.darkness.mining_speed"), 0.5 * level, tooltip);
                    } else {
                        addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.darkness.draw_speed"), 0.5 * level, tooltip);
                    }
                }
            }
        }
    }
}
