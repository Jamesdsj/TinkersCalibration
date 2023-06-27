package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.upgrades.general.ReinforcedModifier;

import javax.annotation.Nonnull;
import java.util.List;

import static slimeknights.tconstruct.library.tools.stat.ToolStats.DRAW_SPEED;
import static slimeknights.tconstruct.tools.modifiers.upgrades.general.ReinforcedModifier.damageReinforced;

public class IgneousModifier extends Modifier implements ConditionalStatModifierHook {

    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "igneous_mod");
    private static final Component UNBREAKING = TConstruct.makeTranslation("modifier", "igneous.unbreaking");
    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        ModDataNBT persistantData = tool.getPersistentData();
        if(persistantData.contains(KEY, 5)) {
            float value = persistantData.getFloat(KEY);
            return (float) (damage * (1 + value * 0.1));
        }
        return super.getEntityDamage(tool, level, context, baseDamage, damage);
    }

    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        ModDataNBT persistantData = tool.getPersistentData();
        if(persistantData.contains(KEY, 5)) {
            float value = persistantData.getFloat(KEY);
            event.setNewSpeed((float) (event.getOriginalSpeed() * (1 + value * 0.1)));
        }
    }
    @Override
    public int getPriority() {
        return 120;
    }

    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, @javax.annotation.Nullable LivingEntity holder) {
        ModDataNBT persistantData = tool.getPersistentData();
        if(persistantData.contains(KEY, 5)) {
            float value = persistantData.getFloat(KEY);
            int reinforcedlevel = tool.getModifierLevel(TinkerModifiers.reinforced.get());
            float reinforced = ReinforcedModifier.diminishingPercent(reinforcedlevel);
            if(value >= 2.5 * level) {
                return damageReinforced(amount, (float) (0.25 * level) + reinforced);
            }
            else {
                return damageReinforced(amount, value / 10 + reinforced);
            }
        }
        return amount;
    }
    @Override
    public void onRemoved(IToolStackView tool) {
        tool.getPersistentData().remove(KEY);
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        ModDataNBT persistantData = tool.getPersistentData();
        if(persistantData.contains(KEY, 5)) {
            float value = persistantData.getFloat(KEY);
            if (stat == DRAW_SPEED) {
                return (float) (baseValue * (1 + value * 0.1));
            }
        }
        return baseValue;
    }
    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, int level, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){;
        ModDataNBT persistantData = tool.getPersistentData();
        if (!world.isClientSide && holder.tickCount % 100 == 0 && holder.getUseItem() != stack && isSelected && holder.isOnFire()) {
            if(persistantData.getFloat(KEY) <= 5 * level && RANDOM.nextFloat() <= 0.6f * level)
            persistantData.putFloat(KEY, persistantData.getFloat(KEY)+0.1f);
        }
    }

    @Override
    public void addInformation(@Nonnull IToolStackView tool, int level, @Nullable Player player, @Nonnull List<Component> tooltip, @Nonnull TooltipKey tooltipKey, @Nonnull TooltipFlag tooltipFlag) {
        if(player != null) {
            ModDataNBT persistantData = tool.getPersistentData();
            boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
            if(persistantData.contains(KEY, 5)) {
                float value = persistantData.getFloat(KEY);
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.igneous.attack_damage"), value * 0.1, tooltip);
                if(harvest) {
                    addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.igneous.mining_speed"), value * 0.1, tooltip);
                }
                else {
                    addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.igneous.draw_speed"), value * 0.1, tooltip);
                }
                tooltip.add(new TranslatableComponent("modifier.tinkerscalibration.igneous.cap").withStyle(ChatFormatting.DARK_PURPLE));
                if(value >= 2.5 * level) {
                    addPercentTooltip(UNBREAKING, 0.25 * level, tooltip);

                }
                else if(value < 2.5 * level)
                {
                    addPercentTooltip(UNBREAKING, value / 10, tooltip);
                }
            }
        }
    }

    protected void addSpeedTooltip(IToolStackView tool, float amount, List<Component> tooltip) {
        addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.MELEE_OR_UNARMED, amount, tooltip);
    }
}

