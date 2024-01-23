package me.myster.mysterymod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;


//右鍵火焰彈

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin extends Item {
    public FireChargeItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d vec3d = user.getOppositeRotationVector(1.0f);
        Quaternionf quaternionf = new Quaternionf().setAngleAxis((double) ((float) Math.PI / 180), vec3d.x, vec3d.y, vec3d.z);
        Vec3d vec3d2 = user.getRotationVec(1.0f);
        Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
        FireballEntity projectileEntity = new FireballEntity(world, user, vector3f.x, vector3f.y, vector3f.z, 4);
        projectileEntity.setPos(user.getX() + vector3f.x, user.getEyeY() - (double) 0.15f, user.getZ() + vector3f.z);
        projectileEntity.setVelocity(vector3f.x * 1.25, vector3f.y * 1.25, vector3f.z * 1.25);
        world.spawnEntity(projectileEntity);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
