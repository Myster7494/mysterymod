package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ModelPredicateProviderRegistry.class)
public abstract class ModelPredicateProviderRegistryMixin {

    @Shadow
    @Final
    private static Map<Item, Map<Identifier, ModelPredicateProvider>> ITEM_SPECIFIC;

    @Inject(method = "register(Lnet/minecraft/item/Item;Lnet/minecraft/util/Identifier;Lnet/minecraft/client/item/ClampedModelPredicateProvider;)V", at = @At("RETURN"))
    private static void injected(Item item, Identifier id, ClampedModelPredicateProvider provider, CallbackInfo ci) {
        if (item == Items.CROSSBOW) {
            ModelPredicateProviderRegistry.register(ModItems.ROUND_SHOT_CROSSBOW, id, provider);
        }
    }
}
