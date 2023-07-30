package me.myster.mysterymod.mixin;

import net.minecraft.command.EntityDataObject;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(EntityDataObject.class)
public class EntityDataObjectMixin {
    @Inject(method = "setNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"), cancellable = true)
    private void injected(NbtCompound nbt, CallbackInfo ci) {
        EntityDataObject entityDataObject = (EntityDataObject) (Object) this;
        UUID uUID = entityDataObject.entity.getUuid();
        entityDataObject.entity.readNbt(nbt);
        entityDataObject.entity.setUuid(uUID);
        ci.cancel();
    }

}
