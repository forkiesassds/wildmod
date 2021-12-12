package net.frozenblock.wild.mod.frozenblockapi;

import net.frozenblock.wild.mod.blocks.SculkVeinBlock;
import net.frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;


public class GenerateSculk {

    public static void generateSculk(Level world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        double sx = 0;
        double sy = 0;
        double sz = 0;
        sx = (double) (-2);

        for (int index0 = 0; index0 < (int) (5); index0++) {
            sy = (double) (-2);
            for (int index1 = 0; index1 < (int) (5); index1++) {
                sz = (double) (-2);
                for (int index2 = 0; index2 < (int) (5); index2++) {
                    if (Math.random() > 0.5) {
                        if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).is(BlockTags.MOSS_REPLACEABLE)) {
                            world.setBlock(new BlockPos(x + sx, y + sy, z + sz), RegisterBlocks.SCULK.get().defaultBlockState(), 1);
                        }
                    }
                    sz = (double) (sz + 1);
                }
                sy = (double) (sy + 1);
            }
            sx = (double) (sx + 1);
        }

        sx = (double) (-1);
        for (int index0 = 0; index0 < (int) (3); index0++) {
            sy = (double) (-1);
            for (int index1 = 0; index1 < (int) (3); index1++) {
                sz = (double) (-1);
                for (int index2 = 0; index2 < (int) (3); index2++) {
                    if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).is(BlockTags.MOSS_REPLACEABLE)) {
                        world.setBlock(new BlockPos(x + sx, y + sy, z + sz), RegisterBlocks.SCULK.get().defaultBlockState(), 1);
                    }
                    BlockState blockState = world.getBlockState(new BlockPos(x + sx, y + sy, z + sz));
                    if (blockState.getBlock().defaultBlockState() == RegisterBlocks.SCULK_VEIN.get().defaultBlockState()) {
                        world.setBlock(new BlockPos(x + sx, y + sy, z + sz), Blocks.AIR.defaultBlockState(), 1);
                    }
                    sz = (double) (sz + 1);
                }
                sy = (double) (sy + 1);
            }
            sx = (double) (sx + 1);
        }

        sx = (double) (-3);
        for (int index0 = 0; index0 < (int) (7); index0++) {
            sy = (double) (-3);
            for (int index1 = 0; index1 < (int) (7); index1++) {
                sz = (double) (-3);
                for (int index2 = 0; index2 < (int) (7); index2++) {
                    if (checkforVein(world, new BlockPos(x + sx, y + sy, z + sz))) {
                        if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getMaterial() == Material.AIR || world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getMaterial() == Material.WATER) {
                            world.setBlock(new BlockPos(x + sx, y + sy, z + sz), RegisterBlocks.SCULK_VEIN.get().defaultBlockState()
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.UP),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).above()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).above()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.DOWN),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).below()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).below()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.NORTH),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).north()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).north()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.SOUTH),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).south()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).south()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.EAST),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).east()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).east()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(GlowLichenBlock.getFaceProperty(Direction.WEST),
                                            world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).west()) != RegisterBlocks.SCULK.get().defaultBlockState()
                                                    && world.getBlockState(new BlockPos(x + sx, y + sy, z + sz).west()).is(BlockTags.MOSS_REPLACEABLE))
                                    .setValue(BooleanProperty.create("waterlogged"), world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getMaterial() == Material.WATER),
                                    1
                            );
                        }
                    }
                    sz = (double) (sz + 1);
                }
                sy = (double) (sy + 1);
            }
            sx = (double) (sx + 1);
        }

    }

    private static boolean checkforVein(Level world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        double sx = 0;
        double sy = 0;
        double sz = 0;


        boolean exit = true;


        if (!world.getBlockState(pos.above()).is(BlockTags.MOSS_REPLACEABLE)) {
            if (!world.getBlockState(pos.below()).is(BlockTags.MOSS_REPLACEABLE)) {
                if (!world.getBlockState(pos.below()).is(BlockTags.MOSS_REPLACEABLE)) {
                    if (!world.getBlockState(pos.below()).is(BlockTags.MOSS_REPLACEABLE)) {
                        if (!world.getBlockState(pos.below()).is(BlockTags.MOSS_REPLACEABLE)) {
                            if (!world.getBlockState(pos.below()).is(BlockTags.MOSS_REPLACEABLE)) {
                                exit = false;
                            }
                        }
                    }
                }
            }
        }


        if (world.getBlockState(pos.above()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
            if (world.getBlockState(pos.below()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                if (world.getBlockState(pos.north()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                    if (world.getBlockState(pos.south()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                        if (world.getBlockState(pos.west()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                            if (world.getBlockState(pos.east()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                if (world.getBlockState(pos.above().north()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                    if (world.getBlockState(pos.above().south()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                        if (world.getBlockState(pos.west()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                            if (world.getBlockState(pos.above().east()) != RegisterBlocks.SCULK.get().defaultBlockState()) {

                                                if (world.getBlockState(pos.below().north()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                                    if (world.getBlockState(pos.below().south()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                                        if (world.getBlockState(pos.below().west()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                                            if (world.getBlockState(pos.below().east()) != RegisterBlocks.SCULK.get().defaultBlockState()) {
                                                                exit = false;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return exit;
    }
    
}
