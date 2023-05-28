package com.james.tinkerscalibration;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class GobberTier implements Tier {

    public static Tier instance = new GobberTier();

    @Override
    public int getUses() {
        return 2500;
    }

    @Override
    public float getSpeed() {
        return 2f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 2f;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

}