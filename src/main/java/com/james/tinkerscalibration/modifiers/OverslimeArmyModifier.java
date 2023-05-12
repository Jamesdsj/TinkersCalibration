package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static slimeknights.mantle.item.RetexturedBlockItem.addTooltip;

public class OverslimeArmyModifier extends Modifier implements ProjectileHitModifierHook, ConditionalStatModifierHook {

    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int current = overslime.getOverslime(tool);
        if (current > 0) {
            event.setNewSpeed((float) (event.getOriginalSpeed() + current * level * 0.001));
        }

    }

    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int capacity = overslime.getCapacity(tool);
        int current = overslime.getOverslime(tool);
        if (current > 0) {
            return (float) (damage + current * 0.001 * level);
        }
        return damage;
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        int capacity = overslime.getCapacity(tool);
        int current = overslime.getOverslime(tool);
        int level = modifier.getLevel();
        if (stat == ToolStats.DRAW_SPEED) {
            return (float) (baseValue * (1 + current * level* 0.0001));
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
                addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, (float) (current * level * 0.001), tooltip);
                addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.HARVEST, (float) (current * level * 0.001), tooltip);
            }
            else
            {
                addStatTooltip(tool, ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, (float) (current * level * 0.0001), tooltip);
            }
            }
        }
    }
}


