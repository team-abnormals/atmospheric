package com.minecraftabnormals.atmospheric.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
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
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class AloeVeraBlock extends BushBlock implements IGrowable {
	
	public static final VoxelShape SHAPE_SMALL = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	public static final VoxelShape SHAPE_MEDIUM = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	public static final VoxelShape SHAPE_LARGE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;

	public AloeVeraBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(AGE, 0));
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int age = state.get(AGE);
		VoxelShape shape = age >= 4 ? SHAPE_LARGE : age >= 2 ? SHAPE_MEDIUM : SHAPE_SMALL;
		Vector3d vec3d = state.getOffset(worldIn, pos);
        return shape.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.get(AGE);

		if (i > 2 && player.getHeldItem(handIn).getItem() == Items.SHEARS) {
			Random rand = new Random();
			
			int amount = rand.nextInt(5) + 1;
			if (i == 4) amount = rand.nextInt(5) + 2;
			if (i == 5) amount = rand.nextInt(5) + 3;
			
			player.getHeldItem(handIn).damageItem(1, player, (onBroken) -> { onBroken.sendBreakAnimation(handIn); });
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SLIME_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, 2));
			
			spawnAsEntity(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), amount));
			
			return ActionResultType.SUCCESS;
		} else  {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {			
			double chance = 0.1;
			
			if (state.get(AGE) == 3) chance = 0.1;
			if (state.get(AGE) == 4) chance = 0.2;
			if (state.get(AGE) == 5) chance = 0.4;
			
			if (!worldIn.isRemote && state.get(AGE) > 2 && Math.random() <= chance) {
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
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block instanceof AridSandBlock;
    }

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		int age = state.get(AGE);
		if (age < 5) world.setBlockState(pos, state.with(AGE, age + 1));
		else if (world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.down()).getBlock() == AtmosphericBlocks.ARID_SAND.get()) {
			placeAt(world, pos, 2);
		}
	}
	
	@SuppressWarnings("deprecation")
    @Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		boolean flag = worldIn.getBlockState(pos.down()).getBlock() == AtmosphericBlocks.RED_ARID_SAND.get();
		int chance = flag ? 8 : 12;
        if (worldIn.getLightSubtracted(pos.up(), 0) >= 12 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(chance) == 0)) {
        	if (state.get(AGE) < 5) {
        		worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
        	} else if (!flag) {
        		AloeVeraTallBlock tallAloe = (AloeVeraTallBlock)(AtmosphericBlocks.TALL_ALOE_VERA.get());
                if (tallAloe.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
                	tallAloe.placeAt(worldIn, pos, 2);
                }
        	}
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
     }
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }
	
	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlockState(pos, AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState().with(AloeVeraTallBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlockState(pos.up(), AtmosphericBlocks.TALL_ALOE_VERA.get().getDefaultState().with(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
}
