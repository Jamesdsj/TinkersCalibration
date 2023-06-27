package com.james.tinkerscalibration.modifiers;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

public class EnergyStorageModifier extends Modifier {

	@Override
	public ValidatedResult validate(IToolStackView tool, int level) {
		int max = tool.getVolatileData().getInt(EnergyBaseModifier.MAX_ENERGY);
		if (tool.getPersistentData().getInt(EnergyBaseModifier.STORED_ENERGY) > max)
			tool.getPersistentData().putInt(EnergyBaseModifier.STORED_ENERGY, max);
		return ValidatedResult.PASS;
	}

	@Override
	public void onRemoved(IToolStackView tool) {
		if (tool.getVolatileData().getInt(EnergyBaseModifier.MAX_ENERGY) == 0)
			tool.getPersistentData().remove(EnergyBaseModifier.STORED_ENERGY);
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		if (volatileData.contains(EnergyBaseModifier.MAX_ENERGY, Tag.TAG_INT)) {
			volatileData.putInt(EnergyBaseModifier.MAX_ENERGY, volatileData.getInt(EnergyBaseModifier.MAX_ENERGY) + getCapacity() * level);
		} else {
			volatileData.putInt(EnergyBaseModifier.MAX_ENERGY, getCapacity() * level);
		}
		if (!volatileData.contains(EnergyBaseModifier.ENERGY_OWNER, Tag.TAG_STRING))
			volatileData.putString(EnergyBaseModifier.ENERGY_OWNER, getId().toString());
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (tool instanceof ToolStack && isOwner(tool.getVolatileData())) {
			tooltip.add(new TranslatableComponent(EnergyBaseModifier.STORED_ENERGY_KEY, tool.getPersistentData().getInt(EnergyBaseModifier.STORED_ENERGY), tool.getVolatileData().getInt(EnergyBaseModifier.MAX_ENERGY)).withStyle(style -> style.withColor(TextColor.fromRgb(getColor()))));
		}
	}

	public int getCapacity() {
		return 50000;
	}

	public boolean isOwner(IModDataView volatileData) {
		return getId().toString().equals(volatileData.getString(EnergyBaseModifier.ENERGY_OWNER));
	}
}