package com.james.tinkerscalibration.entity.projectile;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HymonArrow extends AbstractArrow {
    private static final float defaultDamage = 2F;
    private BlockPos origin;
    public HymonArrow(EntityType<? extends HymonArrow> entityIn, Level worldIn) {

        super(entityIn, worldIn);
        this.setBaseDamage(defaultDamage);
    }

    public HymonArrow(Level worldIn, LivingEntity shooter) {

        super(HymonArrow.get(), shooter, worldIn);
        this.setBaseDamage(defaultDamage);
        this.origin = shooter.blockPosition();
    }

    private static EntityType<? extends AbstractArrow> get() {

        return null;
    }
    public HymonArrow(Level worldIn, double x, double y, double z) {

        super(HymonArrow.get(), x, y, z, worldIn);
        this.setBaseDamage(defaultDamage);
        this.origin = new BlockPos(x, y, z);
    }
    public void tick() {
        super.tick();
        if (this.level.isClientSide && !this.inGround && this.isInWater()) {

        }

    }
    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TinkersCalibration.Hymon_Arrow.get());
    }
    protected float getWaterInertia() {
        return 1F;
    }
}
