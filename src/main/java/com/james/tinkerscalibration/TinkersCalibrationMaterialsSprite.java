package com.james.tinkerscalibration;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

import javax.annotation.Nonnull;
public class TinkersCalibrationMaterialsSprite extends AbstractMaterialSpriteProvider {

    @Nonnull
    @Override
    public String getName() {
        return "Tinkers Calibration Material Sprite Provider";
    }

    @Override
    protected void addAllMaterials() {

        buildMaterial(Utils.fiberglass)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 00000000).addARGB(102, 0xFFC0C0C0).addARGB(140, 0xFFC0C0C0).addARGB(178, 0xFFC0C0C0).addARGB(216, 0xFFC0C0C0).addARGB(255, 0xFFC0C0C0).build());
        buildMaterial(Utils.obsidian)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 00000000).addARGB(102, 0xFF663399).addARGB(140, 0xFF000000).addARGB(178, 0xFF663399).addARGB(216, 0xFF663399).addARGB(255, 0xFF000000).build());
        buildMaterial(Utils.netherbrick)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 00000000).addARGB(102, 0xFF663399).addARGB(140, 0xFF000000).addARGB(178, 0xFF663399).addARGB(216, 0xFF663399).addARGB(255, 0xFF000000).build());
        buildMaterial(Utils.bamboosteel)
                .meleeHarvest()
                .fallbacks("medal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 00000000).addARGB(102, 0xFF663399).addARGB(140, 0xFF000000).addARGB(178, 0xFF663399).addARGB(216, 0xFF663399).addARGB(255, 0xFF000000).build());
        buildMaterial(Utils.purpur)
                .meleeHarvest()
                .fallbacks("rock")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 00000000).addARGB(102, 0xFF663399).addARGB(140, 0xFF000000).addARGB(178, 0xFF663399).addARGB(216, 0xFF663399).addARGB(255, 0xFF000000).build());
    }
    }

