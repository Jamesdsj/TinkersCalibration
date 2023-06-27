package com.james.tinkerscalibration;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TinkersCalibrationLootTables extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(TinkersCalibrationBlockDrop::new, LootContextParamSet.builder().build()));

    public TinkersCalibrationLootTables(DataGenerator gen) {
        super(gen);
    }
    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationtracker) {
        map.keySet().removeIf((loc) -> !loc.getNamespace().equals(TinkersCalibration.MODID));
    }

    @Nonnull
    @Override
    public String getName() {
        return TinkersCalibration.MODID + " LootTables";
    }
}
