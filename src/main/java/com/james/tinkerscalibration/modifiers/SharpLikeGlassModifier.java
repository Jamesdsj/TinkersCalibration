package com.james.tinkerscalibration.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class SharpLikeGlassModifier extends Modifier {
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.05 * level);
    }
}
