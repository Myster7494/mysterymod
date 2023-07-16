package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "isOf(Lnet/minecraft/item/Item;)Z", at = @At("RETURN"), cancellable = true)
    private void injected(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item == Items.CROSSBOW) {
            if (this.getItem() == ModItems.ROUND_SHOT_CROSSBOW) {
                cir.setReturnValue(true);
            }
        }
    }
}
