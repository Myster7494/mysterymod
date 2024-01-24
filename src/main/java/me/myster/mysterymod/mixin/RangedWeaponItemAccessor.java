package me.myster.mysterymod.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemAccessor {
    @Accessor("CROSSBOW_HELD_PROJECTILES")
    public static void setCrossbowHeldProjectiles(Predicate<ItemStack> projectiles) {
        throw new AssertionError();
    }

    @Accessor("CROSSBOW_HELD_PROJECTILES")
    public static Predicate<ItemStack> getCrossbowHeldProjectiles() {
        throw new AssertionError();
    }
}
