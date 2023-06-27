package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.Utils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class EnergyShieldModifier extends EnergyBaseModifier {
    @Override
    protected int getShieldCapacity(IToolStackView tool, int level) {

        return 100000 * level;
    }
    public int getEnergy(IToolStackView tool) {
        return getShield(tool);
    }
    @Override
    public int getPriority() {
        return 175;
    }
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, LivingEntity holder) {
        Level world = holder.getLevel();
        float chance = 0.15f * level;
        if (holder instanceof Player player) {
            if (RANDOM.nextFloat() < chance) {
                ToolDamageUtil.directDamage(tool, 5 * level, player, player.getUseItem());
                player.broadcastBreakEvent(player.getUsedItemHand());
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return amount;
    }
    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, int level) {
        EnergyStorageModifier energy = (EnergyStorageModifier) Utils.energystorage.get();
        return getShield(tool) < getShieldCapacity(tool, level);
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, int level) {
        if (getShield(tool) > 0) {
            // just always display light blue, not much point in color changing really
            return 0xff4500;
        }
        return -1;
    }
}
