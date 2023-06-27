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

public class ImpregnableModifier extends Modifier implements ProjectileLaunchModifierHook, ConditionalStatModifierHook{
    private static final Component UNBREAKING = TConstruct.makeTranslation("modifier", "impregnable.unbreaking");
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT, TinkerHooks.PROJECTILE_LAUNCH);
    }
    private static float getBonus(LivingEntity living, RegistryObject<? extends TinkerEffect> effect, int level, float scale) {
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
    public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        float bonus = getBonus(holder, Utils.impregnableEffect, level, 6);
        int off = 0;
        for (int i = 0; i < amount; i++) {
            if (RANDOM.nextFloat() >= 1 / bonus) {
                off++;
            }
        }
        return amount - off;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        if (primary && (arrow == null || arrow.isCritArrow())) {
            // 16 arrows gets you to max
            int effectLevel = Math.min(15, Utils.impregnableEffect.get().getLevel(shooter) + 1);
            Utils.impregnableEffect.get().apply(shooter, 5 * 20, effectLevel, true);
        }
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        return baseValue;
    }
    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
            float bonus;
            if (player != null && key == TooltipKey.SHIFT) {
                bonus = getBonus(player, Utils.impregnableEffect, level, 6);
                if (bonus > 0) {
                    addPercentTooltip(UNBREAKING, 1 / bonus, tooltip);
                }
            }
        }
    }
}
