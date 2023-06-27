package com.james.tinkerscalibration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TinkersCalibrationTags {
    public static void init() {
        Items.init();
    }
    public static class Items {

        public static void init() {
        }

        private static TagKey<Item> create(String name) {
            return ItemTags.create(new ResourceLocation(name));
        }
        public static final TagKey<Item> MANGOBBERSLIME_INGOT = create("forge:ingots/mangobberslime");
        public static final TagKey<Item> FAZELLE_INGOT = create("forge:ingots/fazelle");
        public static final TagKey<Item> MANDITE_INGOT = create("forge:ingots/mandite");
        public static final TagKey<Item> EMPERORSLIME_INGOT = create("forge:ingots/emperorslime");
        public static final TagKey<Item> LINDSTEEL_INGOT = create("forge:ingots/lindsteel");
        public static final TagKey<Item> TITANIUM_INGOT = create("forge:ingots/titanium");
        public static final TagKey<Item> MANGOBBERSLIME_NUGGET = create("forge:nuggets/mangobberslime");
        public static final TagKey<Item> FAZELLE_NUGGET = create("forge:nuggets/fazelle");
        public static final TagKey<Item> MANDITE_NUGGET = create("forge:nuggets/mandite");
        public static final TagKey<Item> EMPERORSLIME_NUGGET = create("forge:nuggets/emperorslime");
        public static final TagKey<Item> LINDSTEEL_NUGGET = create("forge:nuggets/lindsteel");
        public static final TagKey<Item> TITANIUM_NUGGET = create("forge:nuggets/titanium");
    }
}
