package me.myster.mysterymod.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

import java.util.Objects;

public class CrossbowWitherSkullEntity extends WitherSkullEntity {
    public CrossbowWitherSkullEntity(EntityType<? extends CrossbowWitherSkullEntity> entityType, World world) {
        super(entityType, world);
    }

    public CrossbowWitherSkullEntity(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(world, owner, directionX, directionY, directionZ);
    }

    @Override
    public float getEffectiveExplosionResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, float max) {
        if (this.isCharged() && WitherEntity.canDestroy(blockState)) {
            return Math.min(0.5f, max);
        }
        return max;
    }

    @Override
    protected float getDrag() {
        return 0.95f;
    }

    @Override
    public void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult) hitResult);
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));
        } else if (type == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            this.onBlockHit(blockHitResult);
            BlockPos blockPos = blockHitResult.getBlockPos();
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Emitter.of(this, this.getWorld().getBlockState(blockPos)));
        }
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion((Entity) this, this.getX(), this.getY(), this.getZ(), ((LivingEntity) (Objects.requireNonNull(this.getOwner()))).getRandom().nextBetween(1, 3), false, World.ExplosionSourceType.MOB);
            this.discard();
        }
    }
}
