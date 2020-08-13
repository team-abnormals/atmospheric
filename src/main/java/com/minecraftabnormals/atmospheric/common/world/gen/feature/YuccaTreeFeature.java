package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.atmospheric.common.world.gen.feature.config.YuccaTreeFeatureConfig;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.utils.TreeUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class YuccaTreeFeature extends Feature<YuccaTreeFeatureConfig> {
    public YuccaTreeFeature(Codec<YuccaTreeFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, YuccaTreeFeatureConfig config) {
        if (config.baby) {
            int height = 2 + rand.nextInt(2) + rand.nextInt(2);
            boolean flag = true;

            if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
                for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
                    int k = 1;
                    if (j == position.getY())
                        k = 0;
                    if (j >= position.getY() + 1 + height - 2)
                        k = 2;
                    BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
                    for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                        for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                            if (j >= 0 && j < worldIn.getHeight()) {
                                if (!TreeUtils.isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
                                    flag = false;
                            } else
                                flag = false;
                        }
                    }
                }

                if (!flag) {
                    return false;
                } else if (TreeUtils.isInTag(worldIn, position.down(), AtmosphericTags.YUCCA_PLANTABLE_ON) && position.getY() < worldIn.getHeight()) {
                    if (!TreeUtils.isInTag(worldIn, position.down(), BlockTags.SAND))
                        TreeUtils.setDirtAt(worldIn, position.down());

                    int logX = position.getX();
                    int logZ = position.getZ();
                    int logY = position.getY();

                    for (int k1 = 0; k1 < height; ++k1) {
                        logY = position.getY() + k1;
                        BlockPos blockpos = new BlockPos(logX, logY, logZ);
                        if (TreeUtils.isAirOrLeaves(worldIn, blockpos)) {
                            TreeUtils.setForcedState(worldIn, blockpos, config.trunkProvider.getBlockState(rand, blockpos));
                        }
                    }

                    logY = position.getY() + height - 1;

                    position = new BlockPos(logX, logY, logZ);

                    this.createBabyYuccaLeaves(worldIn, position.up(), rand, config, false);
                    this.createBabyYuccaLeaves(worldIn, position, rand, config, true);
                    this.createBabyYuccaLeaves(worldIn, position.down(), rand, config, false);

                    if (config.patch) {
                        for (int j = 0; j < 64; ++j) {
                            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                            if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && config.flowerProvider.getBlockState(rand, blockpos).isValidPosition((IWorldReader) worldIn, blockpos)) {
                                placeFlowerAt(worldIn, blockpos, rand, config);
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            int height = 4 + rand.nextInt(2) + rand.nextInt(2);
            int reduction = 2 + rand.nextInt(3);
            if (config.petrified)
                height -= reduction;
            boolean flag = true;

            if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
                for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
                    int k = 1;
                    if (j == position.getY())
                        k = 0;
                    if (j >= position.getY() + 1 + height - 2)
                        k = 2;
                    BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
                    for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                        for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                            if (j >= 0 && j < worldIn.getHeight()) {
                                if (!TreeUtils.isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
                                    flag = false;
                            } else
                                flag = false;
                        }
                    }
                }

                if (!flag) {
                    return false;
                } else if (TreeUtils.isInTag(worldIn, position.down(), AtmosphericTags.YUCCA_PLANTABLE_ON) && position.getY() < worldIn.getHeight()) {
                    // base log
                    if (!TreeUtils.isInTag(worldIn, position.down(), BlockTags.SAND))
                        TreeUtils.setDirtAt(worldIn, position.down());
                    for (int ja = 0; ja < reduction; ++ja) {
                        if (config.petrified)
                            this.placeLogAt(worldIn, position.down(ja), Direction.UP, false, rand, config);
                    }

                    int logX = position.getX();
                    int logZ = position.getZ();
                    int logY = position.getY();

                    for (int k1 = 0; k1 < height; ++k1) {
                        logY = position.getY() + k1;
                        BlockPos blockpos = new BlockPos(logX, logY, logZ);
                        if (TreeUtils.isAirOrLeaves(worldIn, blockpos)) {
                            this.placeLogAt(worldIn, blockpos, Direction.UP, false, rand, config);
                        }
                    }

                    logY = position.getY() + height - 1;

                    BlockPos newPos = this.createYuccaBranch(height, worldIn, position, Direction.NORTH, rand, config);
                    this.createYuccaLeaves(worldIn, newPos.up(), rand, false, config);
                    this.createYuccaLeaves(worldIn, newPos, rand, true, config);
                    this.createYuccaLeaves(worldIn, newPos.down(), rand, false, config);

                    newPos = this.createYuccaBranch(height, worldIn, position, Direction.EAST, rand, config);
                    this.createYuccaLeaves(worldIn, newPos.up(), rand, false, config);
                    this.createYuccaLeaves(worldIn, newPos, rand, true, config);
                    this.createYuccaLeaves(worldIn, newPos.down(), rand, false, config);

                    newPos = this.createYuccaBranch(height, worldIn, position, Direction.SOUTH, rand, config);
                    this.createYuccaLeaves(worldIn, newPos.up(), rand, false, config);
                    this.createYuccaLeaves(worldIn, newPos, rand, true, config);
                    this.createYuccaLeaves(worldIn, newPos.down(), rand, false, config);

                    newPos = this.createYuccaBranch(height, worldIn, position, Direction.WEST, rand, config);
                    this.createYuccaLeaves(worldIn, newPos.up(), rand, false, config);
                    this.createYuccaLeaves(worldIn, newPos, rand, true, config);
                    this.createYuccaLeaves(worldIn, newPos.down(), rand, false, config);

                    if (config.patch) {
                        for (int j = 0; j < 64; ++j) {
                            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                            if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && config.flowerProvider.getBlockState(rand, blockpos).isValidPosition((IWorldReader) worldIn, blockpos)) {
                                placeFlowerAt(worldIn, blockpos, rand, config);
                            }
                        }
                    }

                    if (config.petrified && rand.nextInt(12) == 0) {
                        for (int j = 0; j < 12; ++j) {
                            BlockPos blockpos = position.add(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(6) - rand.nextInt(6));
                            if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && TreeUtils.isInTag(worldIn, blockpos.down(), BlockTags.SAND)) {
                                TreeUtils.setForcedState(worldIn, blockpos, config.bundleProvider.getBlockState(rand, blockpos));
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private void createYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, boolean square, YuccaTreeFeatureConfig config) {
        int leafSize = 1;
        for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
            for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
                if (square) {
                    this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                } else {
                    if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
                        this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                    } else if (rand.nextInt(4) == 0) {
                        this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                    }
                }
            }
        }
    }

    private BlockPos createYuccaBranch(int height, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, Random rand, YuccaTreeFeatureConfig config) {
        int logX = pos.getX();
        int logZ = pos.getZ();
        int logY = pos.getY() + height - 1;
        int length = 4 + rand.nextInt(2);
        BlockPos blockpos = new BlockPos(logX, logY, logZ);
        boolean bundle = false;
        boolean anyBundle = false;

        for (int i = 0; i < length; i++) {
            blockpos = new BlockPos(logX, logY, logZ);
            if (!anyBundle && rand.nextInt(16) == 0) {
                bundle = true;
                anyBundle = true;
            } else {
                bundle = false;
            }
            this.createHorizontalLog(1, worldIn, blockpos, direction, bundle, rand, config);
            if (i != length) {
                if (direction == Direction.EAST || direction == Direction.WEST) {
                    if (direction == Direction.EAST) {
                        logX += rand.nextInt(2);
                    } else {
                        logX -= rand.nextInt(2);
                    }
                } else {
                    if (direction == Direction.SOUTH) {
                        logZ += rand.nextInt(2);
                    } else {
                        logZ -= rand.nextInt(2);
                    }
                }
                logY += 1;
            }
        }
        return blockpos.offset(direction);
    }

    private void createHorizontalLog(int branchLength, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, boolean bundle, Random rand, YuccaTreeFeatureConfig config) {
        int logX = pos.getX();
        int logY = pos.getY();
        int logZ = pos.getZ();

        for (int k3 = 0; k3 < branchLength; ++k3) {
            logX += direction.getXOffset();
            logZ += direction.getZOffset();
            BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
            if (TreeUtils.isAirOrLeaves(worldIn, blockpos1)) {
                if (!TreeUtils.isAir(worldIn, blockpos1.down()))
                    bundle = false;
                this.placeLogAt(worldIn, blockpos1, Direction.UP, bundle, rand, config);
            }
        }
    }

    private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Direction direction, boolean bundle, Random rand, YuccaTreeFeatureConfig config) {
        BlockState logState = config.petrified ? config.trunkProvider.getBlockState(rand, pos) : config.trunkProvider.getBlockState(rand, pos).with(RotatedPillarBlock.AXIS, direction.getAxis());
        TreeUtils.setForcedState(worldIn, pos, logState);
        if (bundle && !config.petrified) {
            TreeUtils.setForcedState(worldIn, pos.down(), config.branchProvider.getBlockState(rand, pos.down()));
        }
    }

    private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, YuccaTreeFeatureConfig config) {
        if (TreeUtils.isAirOrLeaves(world, pos) && !config.petrified) {
            if(config.leavesProvider.getBlockState(rand, pos).hasProperty(LeavesBlock.DISTANCE)) {
                TreeUtils.setForcedState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
            } else {
                TreeUtils.setForcedState(world, pos, config.leavesProvider.getBlockState(rand, pos));
            }
        }
        if (rand.nextInt(8) == 0 && !config.petrified) {
            placeFlowerAt(world, pos.up(), rand, config);
        }
    }

    private void createBabyYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, YuccaTreeFeatureConfig config, boolean square) {
        int leafSize = 1;
        for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
            for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
                if (square) {
                    this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                } else {
                    if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
                        this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                    } else if (rand.nextInt(4) == 0) {
                        this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
                    }
                }
            }
        }
    }

    private void placeFlowerAt(IWorldGenerationReader world, BlockPos pos, Random rand, YuccaTreeFeatureConfig config) {
        if (TreeUtils.isAir(world, pos)) {
            if (!TreeUtils.isAir(world, pos.up())) {
                TreeUtils.setForcedState(world, pos, config.flowerProvider.getBlockState(rand, pos));
            } else if (rand.nextInt(4) == 0) {
                TreeUtils.setForcedState(world, pos, config.tallFlowerBottomProvider.getBlockState(rand, pos));
                TreeUtils.setForcedState(world, pos.up(), config.tallFlowerTopProvider.getBlockState(rand, pos.up()));
            } else {
                TreeUtils.setForcedState(world, pos, config.flowerProvider.getBlockState(rand, pos));
            }
        }
    }
}