package net.frozenblock.wild.mod.blocks;

import net.frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.ScheduledTick;


import javax.annotation.Nullable;
import java.util.Random;

public class SculkCatalystBlock extends Block {

    public static final BooleanProperty BLOOM = BooleanProperty.create("bloom");

    public SculkCatalystBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BLOOM, Boolean.FALSE));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_55659_) {
        return this.defaultBlockState().setValue(BLOOM, Boolean.FALSE);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(BLOOM);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state1, boolean correct) {
        super.onPlace(state, world, pos, state1, correct);
        world.getBlockTicks().schedule(new ScheduledTick<>(this, pos, 5, 1));
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);
        if (state.getValue(BLOOM)) {
            world.setBlock(pos, RegisterBlocks.SCULK_CATALYST.get().defaultBlockState(), 1);
        }
        world.getBlockTicks().schedule(new ScheduledTick<>(this, pos, 5, 1));
    }
}
