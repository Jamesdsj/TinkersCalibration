package com.james.tinkerscalibration.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class GobberBlessModifier extends Modifier {
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
        ToolStats.DRAW_SPEED.multiply(builder, 1 + 0.1 * level);
        ToolStats.MINING_SPEED.multiply(builder, 1 + 0.1 * level);
        ToolStats.DURABILITY.multiply(builder, 1 + 0.1 * level);
        ToolStats.ATTACK_SPEED.multiply(builder, 1 + 0.1 * level);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.1 * level);
        ToolStats.VELOCITY.multiply(builder, 1 + 0.1 * level);
        ToolStats.ACCURACY.multiply(builder, 1 + 0.1 * level);
        ToolStats.PROJECTILE_DAMAGE.multiply(builder, 1 + 0.1 * level);
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {}
    {

    }
}
