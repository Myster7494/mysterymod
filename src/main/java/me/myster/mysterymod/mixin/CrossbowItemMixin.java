package me.myster.mysterymod.mixin;

import me.myster.mysterymod.entity.CrossbowWitherSkullEntity;
import me.myster.mysterymod.item.custom.RoundShotCrossbowItem;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//弩發射凋零骷髏頭顱

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
//    @Shadow
//    public static PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
//    }

    @Redirect(method = "use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;shootAll(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;FF)V"))
    private static void injectedShootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        RoundShotCrossbowItem.shootAll(world, entity, hand, stack, speed, divergence);
    }

    @Inject(method = "shoot(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;FZFFF)V", at = @At("HEAD"), cancellable = true)
    private static void shootInjected(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated, CallbackInfo ci) {
        ProjectileEntity projectileEntity;
        if (world.isClient) {
            return;
        }
        Vec3d vec3d = shooter.getOppositeRotationVector(1.0f);
        Quaternionf quaternionf = new Quaternionf().setAngleAxis((double) (simulated * ((float) Math.PI / 180)), vec3d.x, vec3d.y, vec3d.z);
        Vec3d vec3d2 = shooter.getRotationVec(1.0f);
        Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
        boolean isFirework = projectile.isOf(Items.FIREWORK_ROCKET);
        if (isFirework) {
            projectileEntity = new FireworkRocketEntity(world, projectile, shooter, shooter.getX(), shooter.getEyeY() - (double) 0.15f, shooter.getZ(), true);
        } else if (projectile.isOf(Items.WITHER_SKELETON_SKULL)) {
            projectileEntity = new CrossbowWitherSkullEntity(world, shooter, vector3f.x, vector3f.y, vector3f.z);
            projectileEntity.setPos(shooter.getX(), shooter.getEyeY() - (double) 0.15f, shooter.getZ());
        } else {
            projectileEntity = CrossbowItem.createArrow(world, shooter, crossbow, projectile);
            if (creative || simulated != 0.0f) {
                ((PersistentProjectileEntity) projectileEntity).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
        }
        if (shooter instanceof CrossbowUser) {
            CrossbowUser crossbowUser = (CrossbowUser) ((Object) shooter);
            crossbowUser.shoot(crossbowUser.getTarget(), crossbow, projectileEntity, simulated);
        } else {
            projectileEntity.setVelocity(vector3f.x(), vector3f.y(), vector3f.z(), speed, divergence);
        }
        crossbow.damage(isFirework ? 3 : 1, shooter, e -> e.sendToolBreakStatus(hand));
        world.spawnEntity(projectileEntity);
        world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0f, soundPitch);
        ci.cancel();
    }

}
