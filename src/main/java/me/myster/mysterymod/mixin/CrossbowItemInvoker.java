package me.myster.mysterymod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemInvoker {
    @Invoker("loadProjectile")
    public static boolean invokeLoadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        throw new AssertionError();
    }

    @Invoker("getProjectiles")
    public static List<ItemStack> invokeGetProjectiles(ItemStack crossbow) {
        throw new AssertionError();
    }

    @Invoker("shoot")
    public static void invokeShoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated) {
        throw new AssertionError();
    }

    @Invoker("postShoot")
    public static void invokePostShoot(World world, LivingEntity entity, ItemStack stack) {
        throw new AssertionError();
    }

    @Invoker("getSoundPitch")
    public static float invokeGetSoundPitch(boolean flag, Random random) {
        throw new AssertionError();
    }
}
