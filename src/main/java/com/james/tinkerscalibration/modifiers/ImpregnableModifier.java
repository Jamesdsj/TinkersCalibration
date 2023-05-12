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
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.List;

public class ImpregnableModifier extends Modifier implements ProjectileLaunchModifierHook{
    private static final Component UNBREAKING = TConstruct.makeTranslation("modifier", "impregnable.unbreaking");
    private static float getBonus(LivingEntity living, RegistryObject<? extends TinkerEffect> effect, int level, float scale) {
        // 25% boost per level at max
        int effectLevel = effect.get().getLevel(living) + 1;
        return level * effectLevel / scale;
    }



    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        if (context.canHarvest() && context.isEffective() && !context.isAOE()) {
            // 32 blocks gets you to max, effect is stronger at higher levels
            LivingEntity living = context.getLiving();
            int effectLevel = Math.min(31, Utils.impregnableEffect.get().getLevel(living) + 1);
            Utils.impregnableEffect.get().apply(living, 160, effectLevel, true);
        }
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        if (primary && (arrow == null || arrow.isCritArrow())) {
            // 16 arrows gets you to max
            int effectLevel = Math.min(15, TinkerModifiers.momentumRangedEffect.get().getLevel(shooter) + 1);
            TinkerModifiers.momentumRangedEffect.get().apply(shooter, 5 * 20, effectLevel, true);
        }
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
            float bonus;
            if (player != null && key == TooltipKey.SHIFT) {
                if (harvest) {
                    bonus = getBonus(player, Utils.impregnableEffect, level, 128f);
                } else {
                    bonus = getBonus(player, Utils.impregnableEffect, level, 64f);
                }
            } else {
                bonus = level * 0.25f;
            }
            if (bonus > 0) {
                addPercentTooltip(UNBREAKING, bonus, tooltip);
            }
        }
    }
}
