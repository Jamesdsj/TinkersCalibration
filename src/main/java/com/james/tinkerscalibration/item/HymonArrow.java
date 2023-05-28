package com.james.tinkerscalibration.item;

import com.james.tinkerscalibration.ModGroup;
import com.james.tinkerscalibration.Utils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HymonArrow extends Item {
    public HymonArrow() {
        super(new Properties().tab(ModGroup.itemGroup));
    }
    public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
        public static final RegistryObject<Item> Hymon_Arrow = ITEMS.register("hymon_arrow", HymonArrow::new);
    }
}


