package com.james.tinkerscalibration.modifiers;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.definition.module.ToolModuleHooks;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class GlobalTravellerModifier extends Modifier implements BlockInteractionModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.BLOCK_INTERACT);
    }
    @Override
    public InteractionResult afterBlockUse(IToolStackView tool, ModifierEntry modifier, UseOnContext context, InteractionSource source) {
        if (tool.getCurrentDurability() >= 10 && tool.getDefinitionData().getModule(ToolModuleHooks.INTERACTION).canInteract(tool, modifier.getId(), source)) {
//            Player player = context.getPlayer();
        	Level level = context.getLevel();
//            if (!level.isClientSide) {
//                Level world = context.getLevel();
//                Direction face = context.getClickedFace();
//                BlockPos pos = context.getClickedPos().relative(face);
//                BlockState state = world.getBlockState(pos);
//                if (state.is((HolderSet<Block>) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
//                {
//                    ItemEntity entity = state.
//                    if(!chest.is)
//                }
//            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }
}
