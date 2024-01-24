package me.myster.mysterymod.item.custom;


import me.myster.mysterymod.mixin.CrossbowItemInvoker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RoundShotCrossbowItem extends CrossbowItem {

    public RoundShotCrossbowItem(Item.Settings settings) {
        super(settings);
    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        int j = 72;
        boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity) shooter).getAbilities().creativeMode;
        ItemStack itemStack = shooter.getProjectileType(crossbow);
        ItemStack itemStack2 = itemStack.copy();
        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemStack = itemStack2.copy();
            }
            if (itemStack.isEmpty() && bl) {
                itemStack = new ItemStack(Items.ARROW);
                itemStack2 = itemStack.copy();
            }
            if (!CrossbowItemInvoker.invokeLoadProjectile(shooter, crossbow, itemStack, k > 0, bl)) return false;
        }
        return true;
    }

    public static void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        List<ItemStack> list = CrossbowItemInvoker.invokeGetProjectiles(stack);
        ArrayList<Float> fs = RoundShotCrossbowItem.getSoundPitches(entity.getRandom());
        for (int i = 0; i < list.size(); ++i) {
            boolean isCreative;
            ItemStack itemStack = list.get(i);
            isCreative = entity instanceof PlayerEntity && ((PlayerEntity) entity).getAbilities().creativeMode;
            if (itemStack.isEmpty()) continue;
            CrossbowItemInvoker.invokeShoot(world, entity, hand, stack, itemStack, fs.get(i), isCreative, speed, divergence, i * ((float) 360 / list.size()));
        }
        CrossbowItemInvoker.invokePostShoot(world, entity, stack);
    }

    private static ArrayList<Float> getSoundPitches(Random random) {
        boolean bl = random.nextBoolean();
        ArrayList<Float> soundPitches = new ArrayList<>();
        soundPitches.add(1.0f);
        for (int i = 0; i < 71; i++) {
            soundPitches.add(CrossbowItemInvoker.invokeGetSoundPitch((i % 2 == 0) == bl, random));
        }
        return soundPitches;
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = CrossbowItem.getPullProgress(i, stack);
        if (f >= 1.0f && !CrossbowItem.isCharged(stack) && RoundShotCrossbowItem.loadProjectiles(user, stack)) {
            CrossbowItem.setCharged(stack, true);
            SoundCategory soundCategory = user instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, soundCategory, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.5f + 1.0f) + 0.2f);
        }
    }
}
