package com.james.tinkerscalibration;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArrowWater extends AbstractArrow {
    protected ArrowWater(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
    }

    protected float getWaterInertia() {
        return 0.99F;
    }
    @Override
    protected void defineSynchedData() {

    }
    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
