package com.james.tinkerscalibration.modifiers;

import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class GobberCurseNetherModifier extends NoLevelsModifier {
    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        IModDataView persistentData = context.getPersistentData();
        int number = persistentData.getSlots(SlotType.UPGRADE);
        volatileData.addSlots(SlotType.UPGRADE, 0 - number);
        number = persistentData.getSlots(SlotType.ABILITY);
        volatileData.addSlots(SlotType.ABILITY, 0 - number);
    }
}
