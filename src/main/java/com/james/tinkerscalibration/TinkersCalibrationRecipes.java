package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class TinkersCalibrationRecipes extends RecipeProvider implements IConditionBuilder, IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, ICommonRecipeHelper{

    public TinkersCalibrationRecipes(DataGenerator gen) {
        super(gen);
    }
    public void alloyRecipes(Consumer<FinishedRecipe> consumer) {
        String alloyFolder = "smeltery/alloy/";
    }
    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        this.alloyRecipes(consumer);
    }
    @Override
    public String getModId() {
        return TinkersCalibration.MODID;
    }
}
