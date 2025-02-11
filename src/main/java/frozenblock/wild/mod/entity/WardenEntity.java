package frozenblock.wild.mod.entity;


import frozenblock.wild.mod.liukrastapi.WardenGoal;
import frozenblock.wild.mod.registry.RegisterSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.PositionSourceType;

import java.util.Optional;

public class WardenEntity extends HostileEntity {

    private int attackTicksLeft1;

    private int roarTicksLeft1;

    private double roarAnimationProgress;

    private static final double speed = 0.5D;

    public BlockPos lasteventpos;
    public World lasteventworld;
    public LivingEntity lastevententity;


    public WardenEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createWardenAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 84.0D);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new WardenGoal(this, speed));
    }


    public void tickMovement() {
        if(this.attackTicksLeft1 > 0) {
            --this.attackTicksLeft1;
        }
        if(this.roarTicksLeft1 > 0) {
            --this.roarTicksLeft1;
        }
        super.tickMovement();
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public void setRoarAnimationProgress(double a) {
        this.roarAnimationProgress = a;
    }
    public void roar() {
        this.attackTicksLeft1 = 10;
        this.world.sendEntityStatus(this, (byte)3);
    }

    public boolean tryAttack(Entity target) {
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(DamageSource.mob(this), g);
        if (bl) {
            this.attackTicksLeft1 = 10;
            this.world.sendEntityStatus(this, (byte)4);
            target.setVelocity(target.getVelocity().add(0.0D, 0.4000000059604645D, 0.0D));
            this.applyDamageEffects(this, target);
            this.playSound(RegisterSounds.ENTITY_WARDEN_SLIGHTLY_ANGRY, 1.0F,1.0F);
        }
        return bl;
    }

    public int getAttackTicksLeft1() {
        return this.attackTicksLeft1;
    }

    public double getRoarAnimationProgress() {
        return this.roarAnimationProgress;
    }

    public int getRoarTicksLeft1() {
        return this.roarTicksLeft1;
    }

    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackTicksLeft1 = 10;
            this.playSound(RegisterSounds.ENTITY_WARDEN_AMBIENT, 1.0F, 1.0F);
        } else if(status == 3) {
            this.roarTicksLeft1 = 10;
        } else {
            super.handleStatus(status);
        }

    }



    protected SoundEvent getAmbientSound(){return RegisterSounds.ENTITY_WARDEN_AMBIENT;}

    public void listen(BlockPos eventPos, World eventWorld, LivingEntity eventEntity) {
        if(this.lasteventpos == eventPos && this.lasteventworld == eventWorld && this.lastevententity == eventEntity) {
            this.playSound(SoundEvents.BLOCK_SCULK_SENSOR_CLICKING, 0.5F, 1.0F);
        }
        this.lasteventpos = eventPos;
        this.lasteventworld = eventWorld;
        this.lastevententity = eventEntity;
        System.out.println("ahhhhhhhhhhhhhhhhhhhhhhhhhh");
    }
    //your mom is fat lol
}