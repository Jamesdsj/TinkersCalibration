package com.james.tinkerscalibration.item;

import com.james.tinkerscalibration.ModGroup;
import com.james.tinkerscalibration.Utils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FiberGlass extends Item {
    public FiberGlass() {

        super(new Properties().tab(ModGroup.itemGroup));
}
public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
        public static final RegistryObject<Item> Fiberglass = ITEMS.register("fiberglass", FiberGlass::new);
    }}

