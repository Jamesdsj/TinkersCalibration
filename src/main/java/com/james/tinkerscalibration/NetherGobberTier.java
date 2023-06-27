package com.james.tinkerscalibration;
//import com.kwpugh.gobber2.lists.ToolMaterialTiers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class NetherGobberTier implements Tier {

    public static Tier instance = new NetherGobberTier();

    @Override
    public int getUses() {
        return 3000;
    }

    @Override
    public float getSpeed() {
        return 2.5f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 2.5f;
    }

    @Override
    public int getLevel() {
        return 6;
    }

    @Override
    public int getEnchantmentValue() {
        return 35;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    //@Override
    //public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getTag()
    //{
    //    return ToolMaterialTiers.NETHER_GOBBER.getTag();
    //}
}