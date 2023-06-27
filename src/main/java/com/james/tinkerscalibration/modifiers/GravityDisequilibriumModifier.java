package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;

public class GravityDisequilibriumModifier extends Modifier implements GeneralInteractionModifierHook {

    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TinkerHooks.CHARGEABLE_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.RIGHT_CLICK && !tool.isBroken() && player.canEat(false)) {
            ModifierUtil.startUsingItem(tool, modifier.getId(), player, hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
    @Override
    public boolean onFinishUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        // remove is eating tag to prevent from messing with other modifiers
        ModDataNBT persistentData = tool.getPersistentData();
        if (!tool.isBroken() && entity instanceof Player player) {
            // eat the food
            int level = modifier.getLevel();
            Level world = entity.getLevel();

            return true;
        }
        return false;
    }

    //public List<Entity> getNearbyMobs(ToolAttackContext context, int range) {
        //BlockPos b = context.getPlayerAttacker().getOnPos();
        //return context.getAttacker().getLevel().getNearbyEntities(10, 10, 10, 10);
    //}

}
