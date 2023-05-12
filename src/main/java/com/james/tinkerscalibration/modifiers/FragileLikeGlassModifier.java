package com.james.tinkerscalibration.modifiers;

import lombok.NonNull;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static slimeknights.tconstruct.TConstruct.RANDOM;

public class FragileLikeGlassModifier extends Modifier {
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, LivingEntity holder) {
        Level world = holder.getLevel();
        float chance = 0.15f * level;
        if (holder instanceof Player player) {
            if (RANDOM.nextFloat() < chance) {
                ToolDamageUtil.directDamage(tool, 5 * level, player, player.getUseItem());
                player.broadcastBreakEvent(player.getUsedItemHand());
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return amount;
    }
}