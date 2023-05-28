package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
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

public class HydrophilousModifier extends Modifier{
    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        {
            Player player = event.getPlayer();
            if(player.isEyeInFluid(FluidTags.WATER)) {
                if (!ModifierUtil.hasAquaAffinity(player)) {
                    event.setNewSpeed(event.getOriginalSpeed() * 5);
                }
                else {
                    event.setNewSpeed(event.getOriginalSpeed() + 5 * level);
                }
            }
        }
    }
    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        // gives +2 damage per level at max
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
            if (!ModifierUtil.hasAquaAffinity(player))
            addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 5 * level, tooltip);
        }
    }
}
