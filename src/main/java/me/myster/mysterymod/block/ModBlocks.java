package me.myster.mysterymod.block;

import me.myster.mysterymod.MysteryMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MysteryMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(MysteryMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks() {
        MysteryMod.LOGGER.info("Registering ModBlocks for " + MysteryMod.MOD_ID);
    }
}
