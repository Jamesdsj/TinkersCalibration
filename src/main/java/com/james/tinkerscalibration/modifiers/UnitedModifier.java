package com.james.tinkerscalibration.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class UnitedModifier extends Modifier {
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
        ToolStats.DRAW_SPEED.multiply(builder, 1 + 0.6 * level);
        ToolStats.MINING_SPEED.multiply(builder, 1 + 0.6 * level);
        ToolStats.DURABILITY.multiply(builder, 1 + 0.6 * level);
        ToolStats.ATTACK_SPEED.multiply(builder, 1 + 0.6 * level);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.6 * level);
        ToolStats.VELOCITY.multiply(builder, 1 + 0.6 * level);
        ToolStats.ACCURACY.multiply(builder, 1 + 0.6 * level);
        ToolStats.PROJECTILE_DAMAGE.multiply(builder, 1 + 0.6 * level);
    }
    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        volatileData.addSlots(SlotType.UPGRADE, 3);
        volatileData.addSlots(SlotType.ABILITY, 2);
    }
}
