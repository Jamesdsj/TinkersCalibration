package com.james.tinkerscalibration;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TinkersCalibrationRenderInfo extends AbstractMaterialRenderInfoProvider {

	public TinkersCalibrationRenderInfo(DataGenerator gen, AbstractMaterialSpriteProvider spriteProvider) {
		super(gen, spriteProvider);
	}

	@Override
	protected void addMaterialRenderInfo() {
		buildRenderInfo(Utils.fiberglass).color(0xE8E5D5).luminosity(0);
		buildRenderInfo(Utils.obsidian).color(0x663399).luminosity(0);
		buildRenderInfo(Utils.netherbrick).color(0x8b0000).luminosity(0);
		buildRenderInfo(Utils.bamboosteel).color(0x2f4f4f).luminosity(15);
	}

	@Override
	public String getName() {
		return "Tinkers Calibration Material Render Info Provider";
	}
}
