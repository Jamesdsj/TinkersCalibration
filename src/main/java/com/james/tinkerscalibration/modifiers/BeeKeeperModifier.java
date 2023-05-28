package com.james.tinkerscalibration.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BeeKeeperModifier extends NoLevelsModifier {
    @Override
    public void onAttacked(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        Entity attacker = source.getEntity();
        LivingEntity target = context.getEntity();
        if(target != null) {
            if (source == DamageSource.CACTUS) {
                target.heal(amount);
            }
            if (attacker != null && attacker.getType() == EntityType.BEE) {
                target.heal(amount);
                target.removeEffect(MobEffects.POISON);
            }
        }
    }
}
