package com.james.tinkerscalibration;

import javax.annotation.Nonnull;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class TinkersCalibrationMaterialsSprite extends AbstractMaterialSpriteProvider {
    public TinkersCalibrationMaterialsSprite() {
    }

    @Nonnull
    public String getName() {
        return "Tinkers Calibration Material Sprite Provider";
    }

    protected void addAllMaterials() {
        this.buildMaterial(Utils.fiberglass).meleeHarvest().fallbacks(new String[]{"metal"}).colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0).addARGB(102, -4144960).addARGB(140, -4144960).addARGB(178, -4144960).addARGB(216, -4144960).addARGB(255, -4144960).build());
        this.buildMaterial(Utils.obsidian).meleeHarvest().fallbacks(new String[]{"rock"}).colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0).addARGB(102, -10079335).addARGB(140, -16777216).addARGB(178, -10079335).addARGB(216, -10079335).addARGB(255, -16777216).build());
        this.buildMaterial(Utils.netherbrick).meleeHarvest().fallbacks(new String[]{"rock"}).colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0).addARGB(102, -10079335).addARGB(140, -16777216).addARGB(178, -10079335).addARGB(216, -10079335).addARGB(255, -16777216).build());
        this.buildMaterial(Utils.bamboosteel).meleeHarvest().fallbacks(new String[]{"medal"}).colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0).addARGB(102, -10079335).addARGB(140, -16777216).addARGB(178, -10079335).addARGB(216, -10079335).addARGB(255, -16777216).build());
        this.buildMaterial(Utils.purpur).meleeHarvest().fallbacks(new String[]{"rock"}).colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0).addARGB(102, -10079335).addARGB(140, -16777216).addARGB(178, -10079335).addARGB(216, -10079335).addARGB(255, -16777216).build());
    }
}

