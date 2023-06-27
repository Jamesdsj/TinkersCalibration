package com.james.tinkerscalibration;
//import com.kwpugh.gobber2.lists.ToolMaterialTiers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class EndGobberTier implements Tier {

    public static Tier instance = new EndGobberTier();

    @Override
    public int getUses() {
        return 3500;
    }

    @Override
    public float getSpeed() {
        return 3f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 3f;
    }

    @Override
    public int getLevel() {
        return 7;
    }

    @Override
    public int getEnchantmentValue() {
        return 40;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    //@Override
    //public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getTag()
    //{
    //    return ToolMaterialTiers.END_GOBBER.getTag();
    //}

}