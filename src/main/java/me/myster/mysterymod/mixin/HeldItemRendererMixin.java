package me.myster.mysterymod.mixin;

import me.myster.mysterymod.item.ModItems;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//讓玩家自己看到圓形射擊弩的動畫正確播放

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Redirect(method = {"getHandRenderType(Lnet/minecraft/client/network/ClientPlayerEntity;)Lnet/minecraft/client/render/item/HeldItemRenderer$HandRenderType;", "getUsingItemHandRenderType(Lnet/minecraft/client/network/ClientPlayerEntity;)Lnet/minecraft/client/render/item/HeldItemRenderer$HandRenderType;", "isChargedCrossbow(Lnet/minecraft/item/ItemStack;)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean injectedStatic(ItemStack instance, Item item) {
        return item == Items.CROSSBOW && (instance.isOf(Items.CROSSBOW) || instance.isOf(ModItems.ROUND_SHOT_CROSSBOW));
    }

    @Redirect(method = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean injected(ItemStack instance, Item item) {
        return item == Items.CROSSBOW && (instance.isOf(Items.CROSSBOW) || instance.isOf(ModItems.ROUND_SHOT_CROSSBOW));
    }
}
