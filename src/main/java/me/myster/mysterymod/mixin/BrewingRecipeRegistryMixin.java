package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRecipeRegistryMixin {

    @Inject(method = "registerDefaults()V", at = @At("TAIL"))
    private static void injectedRegisterDefaults(CallbackInfo ci) {
        BrewingRecipeRegistry.registerItemRecipe(ModItems.BLACK_TEA, Items.GOLDEN_APPLE, ModItems.GOLDEN_APPLE_BLACK_TEA);
        BrewingRecipeRegistry.registerItemRecipe(ModItems.GREEN_TEA, Items.GOLDEN_APPLE, ModItems.GOLDEN_APPLE_GREEN_TEA);
    }

    @Inject(method = "registerItemRecipe(Lnet/minecraft/item/Item;Lnet/minecraft/item/Item;Lnet/minecraft/item/Item;)V", at = @At("HEAD"), cancellable = true)
    private static void injectedRegisterItemRecipe(Item input, Item ingredient, Item output, CallbackInfo ci) {
        BrewingRecipeRegistry.ITEM_RECIPES.add(new BrewingRecipeRegistry.Recipe<Item>(input, Ingredient.ofItems(ingredient), output));
        ci.cancel();
    }

    @Inject(method = "hasRecipe(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private static void injectedHasRecipe(ItemStack input, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(BrewingRecipeRegistry.hasItemRecipe(input, ingredient) || BrewingRecipeRegistry.hasPotionRecipe(input, ingredient));
    }

}
