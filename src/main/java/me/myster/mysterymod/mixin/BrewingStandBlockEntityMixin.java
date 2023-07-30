package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBlockEntityMixin {
    @Inject(method = "isValid(ILnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    public void injected(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!(slot == 3 || slot == 4)) {
            cir.setReturnValue((stack.isOf(Items.POTION) || stack.isOf(Items.SPLASH_POTION) || stack.isOf(Items.LINGERING_POTION) || stack.isOf(Items.GLASS_BOTTLE) || stack.isOf(ModItems.BLACK_TEA) || stack.isOf(ModItems.GREEN_TEA)) && ((BrewingStandBlockEntity) (Object) this).getStack(slot).isEmpty());
        }
    }
}
