package com.james.tinkerscalibration.modifiers;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class EnergyShieldModifier extends EnergyStorageModifier{
    private static final int ENERGY_COST = 500;
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, LivingEntity holder) {
        if (holder instanceof Player) {
            if (!tool.isBroken() && EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, true, false)) {
                EnergyBaseModifier.removeEnergy(tool, ENERGY_COST * level, false, false);
                return 0;
            }
        }
        return amount;
    }
}
