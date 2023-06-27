package com.james.tinkerscalibration;

import moze_intel.projecte.gameObjs.PETags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fml.ModList;

public class RedMatterTier implements Tier {

    public static Tier instance = new RedMatterTier();

    @Override
    public int getUses() {
        return 5000;
    }

    @Override
    public float getSpeed() {
        return 5f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5f;
    }

    @Override
    public int getLevel() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    //@Override
    //public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getTag()
    //{
    //    return PETags.Blocks.NEEDS_RED_MATTER_TOOL;
    //}
}
