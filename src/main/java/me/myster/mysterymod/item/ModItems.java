package me.myster.mysterymod.item;

import me.myster.mysterymod.MysteryMod;
import me.myster.mysterymod.item.custom.RoundShotCrossbowItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ROUND_SHOT_CROSSBOW = registerItem("round_shot_crossbow", new RoundShotCrossbowItem(new Item.Settings().maxCount(1).maxDamage(10000)));
    //public static final Item HOTBAR = registerItem("hotbar", new HotbarItem(new Item.Settings().maxCount(1)));
    public static final Item BLACK_TEA = registerItem("black_tea", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_APPLE_BLACK_TEA = registerItem("golden_apple_black_tea", new Item(new FabricItemSettings()));
    public static final Item GREEN_TEA = registerItem("green_tea", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_APPLE_GREEN_TEA = registerItem("golden_apple_green_tea", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MysteryMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MysteryMod.LOGGER.info("Registering Mod Items for " + MysteryMod.MOD_ID);
    }
}
