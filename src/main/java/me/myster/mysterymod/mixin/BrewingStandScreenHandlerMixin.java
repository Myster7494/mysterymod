package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$PotionSlot")
public abstract class BrewingStandScreenHandlerMixin {
    @Inject(method = "matches(Lnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private static void injected(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(stack.isOf(Items.POTION) || stack.isOf(Items.SPLASH_POTION) || stack.isOf(Items.LINGERING_POTION) || stack.isOf(Items.GLASS_BOTTLE) || stack.isOf(ModItems.BLACK_TEA) || stack.isOf(ModItems.GREEN_TEA));
    }
}
