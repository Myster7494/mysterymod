package me.myster.mysterymod.mixin;

import me.myster.mysterymod.entity.CrossbowWitherSkullEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Objects;

@Mixin(WitherSkullEntity.class)
public abstract class WitherSkullEntityMixin {
    @ModifyConstant(method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V", constant = @Constant(floatValue = 1.0f))
    private float injected(float constant) {
        if (((WitherSkullEntity) (Object) this) instanceof CrossbowWitherSkullEntity) {
            return ((LivingEntity) (Objects.requireNonNull(((WitherSkullEntity) (Object) this).getOwner()))).getRandom().nextBetween(2, 4);
        }
        return constant;
    }
}
