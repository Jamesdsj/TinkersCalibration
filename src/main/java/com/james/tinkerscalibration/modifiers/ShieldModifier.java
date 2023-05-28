package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.Utils;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class ShieldModifier extends Modifier {
    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        //DefenseShieldModifier defenseshield = Utils.defenseshield.get();
        //defenseshield.addCapacity(volatileData, 70);
    }
}
