package com.james.tinkerscalibration.modifiers;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nonnull;

public class CriticalAttackModifier extends Modifier implements ConditionalStatModifierHook {
    @Override
    public float getEntityDamage(@Nonnull IToolStackView tool, int level, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        Level world = context.getAttacker().level;
        Player player = context.getPlayerAttacker();
        LivingEntity target = context.getLivingTarget();
        ItemStack stack = player.getUseItem();
        double x = target.getX();
        double y = target.getY();
        double z = target.getZ();
        if (tool.getCurrentDurability() == 1) {
            ToolDamageUtil.breakTool(stack);
            world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 0.0D, 0.0D, 0.0D);
            return 100;

        }
        return damage;
    }
    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, LivingEntity holder) {
        if(tool.getCurrentDurability() == 2)
        {
            return 1;
        }
        return amount;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if(tool.getCurrentDurability() == 1)
        {
            if(harvest) {
                if (stat == ToolStats.ATTACK_SPEED) {
                    return 0.1F;
                }
            }
            else if(!harvest){
                if (stat == ToolStats.DRAW_SPEED) {
                    return 0.1F;
                }
            }
        }
        return baseValue;
    }
}
