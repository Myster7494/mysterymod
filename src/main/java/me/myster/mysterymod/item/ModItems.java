package me.myster.mysterymod.item;

import me.myster.mysterymod.MysteryMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CITRINE = registerItem("citrine",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MysteryMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MysteryMod.LOGGER.info("Registering Mod Items for " + MysteryMod.MOD_ID);
    }
}
