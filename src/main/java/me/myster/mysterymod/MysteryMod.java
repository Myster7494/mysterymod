package me.myster.mysterymod;

import me.myster.mysterymod.block.ModBlocks;
import me.myster.mysterymod.item.ModItemGroups;
import me.myster.mysterymod.item.ModItems;
import me.myster.mysterymod.mixin.RangedWeaponItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysteryMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("mysterymod");
    public static final String MOD_ID = "mysterymod";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
        ModModelPredicates.registerModelPredicates();

        //使弩能裝備凋零骷髏頭顱
        RangedWeaponItemAccessor.setCrossbowHeldProjectiles(RangedWeaponItemAccessor.getCrossbowHeldProjectiles().or(stack -> stack.isOf(Items.WITHER_SKELETON_SKULL)));
    }
}