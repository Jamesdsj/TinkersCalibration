package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;
import java.util.List;

public class PurgatoryModifier extends Modifier {
    public boolean isNetherDimension(Entity entity) {
        return entity != null && isNetherDimension(entity.level);
    }

    public boolean isNetherDimension(Level level) {
        return level.dimension().equals(Level.NETHER);
    }

    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        if (context.getLivingTarget() != null) {
            LivingEntity target = context.getLivingTarget();
            if (isNetherDimension(target.level)) {
                return damage * 1.08f * level;
            }
        }
        return damage;
    }
    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if(isNetherDimension(event.getPlayer())) {
            event.setNewSpeed(event.getNewSpeed() * 1.08f * level);
        }
    }
    @Override
    public void addInformation(@Nonnull IToolStackView tool, int level, @Nullable Player player, @Nonnull List<Component> tooltip, @Nonnull TooltipKey tooltipKey, @Nonnull TooltipFlag tooltipFlag) {
        if(player != null) {
            if(isNetherDimension(player)) {
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.purgator.attack_damage"), 0.08f * level, tooltip);
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.purgator.mining_speed"), 0.08f * level, tooltip);
            }
            else
            {
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.purgator.attack_damage"), 0, tooltip);
                addPercentTooltip(new TranslatableComponent("modifier.tinkerscalibration.modifier.purgator.mining_speed"), 0, tooltip);
            }
        }
    }

}