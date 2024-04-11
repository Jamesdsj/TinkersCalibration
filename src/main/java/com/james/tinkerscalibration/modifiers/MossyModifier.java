package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import javax.annotation.Nonnull;

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

