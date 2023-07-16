package me.myster.mysterymod.item;

import me.myster.mysterymod.MysteryMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static RegistryKey<ItemGroup> MYSTERYMOD = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MysteryMod.MOD_ID, "mysterymod"));


    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, MYSTERYMOD, FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mysterymod.mysterymod"))
                .entries((displayContext, entries) -> {
                    entries.add(ModItems.ROUND_SHOT_CROSSBOW);
                }).build());
    }
}
