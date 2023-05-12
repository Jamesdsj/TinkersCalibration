package com.james.tinkerscalibration.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OverslimeEmpireModifier extends Modifier {
    private int getBoost(StatsNBT baseStats, int level, float perLevel) {
        return (int)(baseStats.get(ToolStats.DURABILITY) * perLevel * level);
    }

    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        overslime.setFriend(volatileData);
        overslime.addCapacity(volatileData, getBoost(context.getBaseStats(), level, 0.15f * context.getDefinition().getData().getMultiplier(ToolStats.DURABILITY)));

    }

}
