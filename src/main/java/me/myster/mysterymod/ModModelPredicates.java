package me.myster.mysterymod;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

//為弩註冊裝備凋零骷髏頭顱的model predicate

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(Items.CROSSBOW, new Identifier("wither_skull"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.WITHER_SKELETON_SKULL) ? 1.0f : 0.0f);
    }
}
