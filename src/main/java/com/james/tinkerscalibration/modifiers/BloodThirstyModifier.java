package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.Utils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.List;

public class BloodThirstyModifier extends Modifier implements ProjectileLaunchModifierHook, ConditionalStatModifierHook {
    private static final Component SPEED = TConstruct.makeTranslation("modifier", "bloodthirsty.speed");

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT, TinkerHooks.PROJECTILE_LAUNCH);
    }

    @Override
    public int getPriority() {
        // run this last as we boost original speed, adds to existing boosts
        return 75;
    }

    /** Gets the bonus for the modifier */
    private static float getBonus(LivingEntity living, RegistryObject<? extends TinkerEffect> effect, int level, float scale) {
        // 25% boost per level at max
        int effectLevel = effect.get().getLevel(living) + 1;
        return level * effectLevel / scale;
    }

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        // 8 hits gets you to max, levels faster at higher levels
        if (!context.isExtraAttack() && context.isFullyCharged()) {
            LivingEntity attacker = context.getAttacker();
            int effectLevel = Math.min(7, Utils.bloodthirstyEffect.get().getLevel(attacker) + 1);
            Utils.bloodthirstyEffect.get().apply(attacker, 5 * 20, effectLevel, true);
        }
        return 0;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        if (primary && (arrow == null || arrow.isCritArrow())) {
            // 16 arrows gets you to max
            int effectLevel = Math.min(15, Utils.bloodthirstyEffect.get().getLevel(shooter) + 1);
            Utils.bloodthirstyEffect.get().apply(shooter, 5 * 20, effectLevel, true);
        }
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == ToolStats.ATTACK_SPEED) {
            return baseValue * (1 + getBonus(living, Utils.bloodthirstyEffect, modifier.getLevel(), 64f));
        }
        if (stat == ToolStats.VELOCITY) {
            return baseValue * (1 + getBonus(living, Utils.bloodthirstyEffect, modifier.getLevel(), 64f));
        }
        return baseValue;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
            float bonus;
            if (player != null && key == TooltipKey.SHIFT) {
                if (harvest) {
                    bonus = getBonus(player, Utils.bloodthirstyEffect, level, 128f);
                } else {
                    bonus = getBonus(player, Utils.bloodthirstyEffect, level, 64f);
                }
            } else {
                bonus = level * 0.25f;
            }
            if (bonus > 0) {
                addPercentTooltip(SPEED, bonus, tooltip);
            }
        }
    }
}

