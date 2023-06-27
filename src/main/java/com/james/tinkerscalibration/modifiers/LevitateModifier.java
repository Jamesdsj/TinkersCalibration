package com.james.tinkerscalibration.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;

public class LevitateModifier extends Modifier implements ProjectileLaunchModifierHook, ProjectileHitModifierHook {
    private static MobEffectInstance makeLevitateEffect(int level) {
        return new MobEffectInstance(MobEffects.LEVITATION, 60 * level, level);
    }
    @Override
    protected void registerHooks(Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.PROJECTILE_LAUNCH, TinkerHooks.PROJECTILE_HIT);
    }
    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        if (context.isFullyCharged()) {
            LivingEntity target = context.getLivingTarget();
            LivingEntity attacker = context.getAttacker();
            if (target != null && target.isAlive()) {
                target.addEffect(makeLevitateEffect(level));
                ToolDamageUtil.damageAnimated(tool,  2 * level, attacker);
            }
        }
        return 0;
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null && (!(projectile instanceof AbstractArrow arrow) || arrow.isCritArrow())) {
            target.addEffect(makeLevitateEffect(modifier.getLevel()));
        }
        return false;
    }
    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
    }
}
