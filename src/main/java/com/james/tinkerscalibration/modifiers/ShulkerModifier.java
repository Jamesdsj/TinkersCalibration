package com.james.tinkerscalibration.modifiers;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import slimeknights.mantle.util.RegistryHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class ShulkerModifier extends NoLevelsModifier {
    @Override
    public boolean isSourceBlocked(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        return source.isProjectile();
    }
    public ShulkerModifier() {
        MinecraftForge.EVENT_BUS.addListener(this::CrouchingImpact);
    }

    private void CrouchingImpact(ProjectileImpactEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level.isClientSide) {
            Projectile projectile = event.getProjectile();
            HitResult hit = event.getRayTraceResult();
            if (!RegistryHelper.contains(TinkerTags.EntityTypes.REFLECTING_BLACKLIST, projectile.getType())
                    && hit.getType() == HitResult.Type.ENTITY && ((EntityHitResult) hit).getEntity() instanceof LivingEntity living && living.isUsingItem()) {
                ItemStack stack = living.getUseItem();
                if (stack.is(TinkerTags.Items.SHIELDS)) {
                    ToolStack tool = ToolStack.from(stack);

                    ModifierEntry activeModifier = ModifierUtil.getActiveModifier(tool);
                    if (activeModifier != null) {
                        GeneralInteractionModifierHook hook = activeModifier.getHook(TinkerHooks.CHARGEABLE_INTERACT);
                        if (activeModifier.getLevel() >= 1 && living.isCrouching()) {
                            if (!RegistryHelper.contains(TinkerTags.EntityTypes.REFLECTING_PRESERVE_OWNER, projectile.getType())) {
                                projectile.setOwner(living);
                            }

                            Vec3 reboundAngle = living.getLookAngle();
                            float velocity = ConditionalStatModifierHook.getModifiedStat(tool, living, ToolStats.VELOCITY) * 1.1f;
                            projectile.shoot(reboundAngle.x, reboundAngle.y, reboundAngle.z, velocity, ModifierUtil.getInaccuracy(tool, living, (float)(velocity * projectile.getDeltaMovement().length())));
                            if (projectile instanceof AbstractHurtingProjectile hurting) {
                                hurting.xPower = reboundAngle.x * 0.1;
                                hurting.yPower = reboundAngle.y * 0.1;
                                hurting.zPower = reboundAngle.z * 0.1;
                            }
                            living.level.playSound(null, living.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.5F + living.level.random.nextFloat() * 0.4F);
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}
