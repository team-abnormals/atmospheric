package com.minecraftabnormals.atmospheric.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericParticles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AloeVeraTallBlock extends DoublePlantBlock implements IGrowable {
	public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 6, 8);
    
	public AloeVeraTallBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(AGE, 6).with(HALF, DoubleBlockHalf.LOWER));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d vec3d = state.getOffset(worldIn, pos);
		VoxelShape shape = state.get(HALF) == DoubleBlockHalf.UPPER ? SHAPE_TOP : SHAPE;
        return shape.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, AGE);
    }
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
		DoubleBlockHalf half = state.get(HALF);
		if (half == DoubleBlockHalf.UPPER) {
	        return block instanceof AridSandBlock || block instanceof AloeVeraTallBlock;
		} else {
	        return block instanceof AridSandBlock;
		}
    }

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!(entityIn instanceof BeeEntity)) entityIn.setMotionMultiplier(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
			Random rand = new Random();
			
			for (int i = 0; i < 3; i++) {
				double offsetX = rand.nextFloat() * 0.6F;
				double offsetZ = rand.nextFloat() * 0.45F;
				
				double x = pos.getX() + 0.5D + offsetX;
				double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
				double z = pos.getZ() + 0.65D + offsetZ;
				
				if (state.get(HALF) == DoubleBlockHalf.UPPER && worldIn.isRemote && worldIn.getGameTime() % (9 / (state.get(AGE) - 5))  == 0) worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}
			
			if (!worldIn.isRemote && state.get(AGE) > 3 && Math.random() <= 0.4 && state.get(HALF) == DoubleBlockHalf.LOWER && !(entityIn instanceof BeeEntity)) {
				entityIn.setMotionMultiplier(state, new Vector3d((double)0.2F, 0.2D, (double)0.2F));
				entityIn.attackEntityFrom(AtmosphericDamageSources.ALOE_LEAVES, 1.0F);
				if (entityIn instanceof ServerPlayerEntity) {
            		ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
            		if(!entityIn.getEntityWorld().isRemote() && !serverplayerentity.isCreative()) {
            			AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.trigger(serverplayerentity); 
            		}
            	}
			}		
		}
	}
	
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericItems.ALOE_KERNELS.get());
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double offsetX = rand.nextFloat() * 0.6F;
		double offsetZ = rand.nextFloat() * 0.45F;
		
		double x = pos.getX() + 0.5D + offsetX;
		double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
		double z = pos.getZ() + 0.65D + offsetZ;
		
		if (state.get(HALF) == DoubleBlockHalf.UPPER && worldIn.isRemote && worldIn.getGameTime() % (6 / (state.get(AGE) - 5)) == 0) worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int age = state.get(AGE);
		Random rand = new Random();

		if (player.getHeldItem(handIn).getItem() == Items.SHEARS) {
						
			player.getHeldItem(handIn).damageItem(1, player, (onBroken) -> { onBroken.sendBreakAnimation(handIn); });
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SLIME_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			
			for (int i = 0; i < (50 + rand.nextInt(50)); i++) {
				double offsetX = rand.nextFloat();
				double offsetZ = rand.nextFloat();
				
				double x = pos.getX() + offsetX;
				double y = pos.getY() + 0.5D + (rand.nextFloat());
				double z = pos.getZ() + 0.15D + offsetZ;
				
				if (worldIn.isRemote) worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}
			
			if (state.get(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.setBlockState(pos, AtmosphericBlocks.ALOE_VERA.get().getDefaultState().with(AloeVeraBlock.AGE, 2), 32);
				worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 32);
			} else {
				worldIn.setBlockState(pos, AtmosphericBlocks.ALOE_VERA.get().getDefaultState().with(AloeVeraBlock.AGE, 2), 32);
				worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 32);
			}
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), new Random().nextInt(5) + 3));
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_KERNELS.get(), age - 5));
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), age - 5));
			
			return ActionResultType.SUCCESS;
		} else  {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.isValidGround(state, worldIn, pos);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(AGE) < 8;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return state.get(AGE) < 8;
	}
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }
	
    @Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		if (state.get(HALF) == DoubleBlockHalf.LOWER) {
			boolean flag = worldIn.getBlockState(pos.down()).getBlock() == AtmosphericBlocks.RED_ARID_SAND.get();
	        if (!flag && state.get(AGE) < 8 && worldIn.getLightSubtracted(pos.up(), 0) >= 12 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(12) == 0)) {
        		worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
        		worldIn.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER).with(AGE, state.get(AGE) + 1));
	            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
	        }
		}
     }
	
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		int age = state.get(AGE);
		DoubleBlockHalf half = state.get(HALF);
		if (age < 8) {
			if (half == DoubleBlockHalf.LOWER) {
				world.setBlockState(pos, state.with(AGE, age + 1));
				world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER).with(AGE, age + 1));
			} else if (half == DoubleBlockHalf.UPPER) {
				world.setBlockState(pos, state.with(AGE, age + 1));
				world.setBlockState(pos.down(), state.with(HALF, DoubleBlockHalf.LOWER).with(AGE, age + 1));
			}
		}
	}
}
