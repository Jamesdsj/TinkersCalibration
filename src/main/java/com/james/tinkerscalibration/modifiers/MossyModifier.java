package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MossBlock;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeTagAppender;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Stream;

public class MossyModifier extends Modifier {
    @Override
    public int getPriority() {
        return 170;
    }
    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, int level, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        boolean isMossy = event.getState().is(BlockTags.MOSS_REPLACEABLE);
        if (isMossy) {
            event.setNewSpeed(event.getNewSpeed() * 1.5f * level);
        }
    }
}

