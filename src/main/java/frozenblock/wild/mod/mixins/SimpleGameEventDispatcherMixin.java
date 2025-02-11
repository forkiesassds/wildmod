package frozenblock.wild.mod.mixins;

import frozenblock.wild.mod.blocks.SculkCatalystBlock;
import frozenblock.wild.mod.blocks.SculkShriekerBlock;
import frozenblock.wild.mod.entity.WardenEntity;
import frozenblock.wild.mod.liukrastapi.GenerateSculk;
import frozenblock.wild.mod.liukrastapi.MathAddon;
import frozenblock.wild.mod.liukrastapi.Sphere;
import frozenblock.wild.mod.liukrastapi.WardenGoal;
import frozenblock.wild.mod.registry.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.listener.SimpleGameEventDispatcher;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mixin(SimpleGameEventDispatcher.class)
public class SimpleGameEventDispatcherMixin{

    @Shadow private final World world;

    public SimpleGameEventDispatcherMixin(World world) {
        this.world = world;
    }

    @Inject(at = @At("HEAD"), method = "dispatch")
    private void dispatch(GameEvent event, Entity entity, BlockPos pos, CallbackInfo ci) {
        BlockPos eventpos;
        World eventworld;
        LivingEntity evententity;

        if(event == GameEvent.ENTITY_KILLED) {
            if(entity != null) {
                if (entity instanceof MobEntity) {
                    BlockState blockState = RegisterBlocks.SCULK_CATALYST.getDefaultState();
                    ArrayList<BlockPos> catalystnear = Sphere.checkSpherePos(blockState, entity.getEntityWorld(), entity.getBlockPos(), 10, true);
                    if(catalystnear.size() > 0) {
                        GenerateSculk.generateSculk(entity.getEntityWorld(), entity.getBlockPos());
                    }

                    for (BlockPos pos1 : catalystnear) {
                        world.setBlockState(pos1, RegisterBlocks.SCULK_CATALYST.getDefaultState().with(SculkCatalystBlock.BLOOM, true));
                        world.createAndScheduleBlockTick(pos1, world.getBlockState(pos1).getBlock(), 40);
                        entity.getEntityWorld().addParticle(RegisterParticles.SCULK_SOUL, pos1.getX() + 0.5, pos1.getY() + 0.5, pos1.getZ() + 0.5, 0, 0.3, 0);
                    }
                }
            }
        }

        if(SculkSensorBlock.FREQUENCIES.containsKey(event)) {
            
            if(entity == null) {
                eventpos = pos;
                eventworld = this.world;
                evententity = null;
            } else {
                if(entity.getType() == EntityType.PLAYER) {
                    if(event == GameEvent.STEP || event == GameEvent.HIT_GROUND || event == GameEvent.PROJECTILE_SHOOT) {
                        if (entity.isSneaking()) {
                            eventpos = null;
                            eventworld = null;
                            evententity = null;
                        } else {
                            PlayerEntity player = (PlayerEntity) entity;
                            Item booties = player.getEquippedStack(EquipmentSlot.FEET).getItem();
                            Identifier identity = Registry.ITEM.getId(booties);
                            if(
                                    new Identifier("wooledboots", "wooled_chainmail_boots").equals(identity) ||
                                            new Identifier("wooledboots", "wooled_gold_boots").equals(identity) ||
                                            new Identifier("wooledboots", "wooled_diamond_boots").equals(identity) ||
                                            new Identifier("wooledboots", "wooled_netherite_boots").equals(identity) ||
                                            new Identifier("wooledboots", "wooled_iron_boots").equals(identity)

                            ) {
                                eventpos = null;
                                eventworld = null;
                                evententity = null;
                            } else {
                                eventpos = pos;
                                eventworld = entity.getEntityWorld();
                                evententity = (LivingEntity) entity;
                            }
                        }
                    } else {
                        eventpos = pos;
                        eventworld = entity.getEntityWorld();
                        evententity = (LivingEntity) entity;
                    }
                } else if(entity.getType() != RegisterEntities.WARDEN) {
                    eventpos = pos;
                    eventworld = entity.getEntityWorld();
                    if(entity.isLiving()) {
                        evententity = (LivingEntity) entity;
                    } else {
                        evententity = null;
                    }
                } else {
                    eventpos = null;
                    eventworld = null;
                    evententity = null;
                }
            }

            if(eventpos != null && eventworld != null && evententity != null) {
                List<WardenEntity> wardens = this.world.getNonSpectatingEntities(WardenEntity.class, new Box(
                        eventpos.getX() -16, eventpos.getY() -16, eventpos.getZ() -16,
                        eventpos.getX() +16, eventpos.getY() +16, eventpos.getZ() +16)
                );
                Iterator var11 = wardens.iterator();

                WardenEntity wardie;
                while(var11.hasNext()) {
                    wardie = (WardenEntity)var11.next();
                    if(
                            wardie.getEntityWorld() == eventworld &&
                            MathAddon.distance(eventpos.getX(), eventpos.getY(), eventpos.getZ(), wardie.getX(), wardie.getY(), wardie.getZ()) <= 15
                    ) {
                        wardie.listen(eventpos, eventworld, evententity);
                    }
                }
            }

        }
    }

}
