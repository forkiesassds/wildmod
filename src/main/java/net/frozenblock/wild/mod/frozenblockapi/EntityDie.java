package net.frozenblock.wild.mod.frozenblockapi;

import net.frozenblock.wild.mod.blocks.SculkCatalystBlock;
import net.frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

public class EntityDie {
    @Mod.EventBusSubscriber
    private static class Trigger {
        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event) {
            if(event != null && event.getEntity() != null) {
                if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player)) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    BlockState blockState = RegisterBlocks.SCULK_CATALYST.get().defaultBlockState();
                    ArrayList<BlockPos> catalystnear = Sphere.checkSpherePos(blockState, entity.level, entity.getOnPos(), 10, true);
                    if (catalystnear != null) {
                        BlockPos element_192889172 = catalystnear.get(0);
                        entity.level.setBlock(element_192889172, blockState.setValue(SculkCatalystBlock.BLOOM, true), 1);
                        entity.level.addParticle(ParticleTypes.SOUL, element_192889172.getX() + 0.5, element_192889172.getY() + 1, element_192889172.getZ() + 0.5, 0, 0.3, 10);
                        GenerateSculk.generateSculk(entity.level, entity.getOnPos());
                    }
                }
            }
        }
    }
}
