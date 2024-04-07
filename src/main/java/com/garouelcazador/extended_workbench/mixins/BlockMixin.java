package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public final class BlockMixin {
    @Unique private static final Direction[] EW$HORIZONTAL = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
    @Unique private final Block ew$targetClass = (Block) (Object) this;

    private BlockMixin(){}

    @Inject(
            method = "createBlockStateDefinition",
            at = @At("TAIL")
    )
    private void extendedWorkbench$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder, CallbackInfo ci) {
        if (this.ew$targetClass.getClass() == CraftingTableBlock.class)
            blockBlockStateBuilder.add(EWHelper.EXTENDED);
    }

    @ModifyReturnValue(
            method = "getStateForPlacement",
            at = @At("RETURN")
    )
    //Se actualiza cada que colocas un bloque al lado de otro ejemplo pones caña sin agua no la coloca y ya
    //Por si acaso esto se ejecuta primero que *updateShape*
    private BlockState extendedWorkbench$getStateForPlacement(BlockState original, BlockPlaceContext blockPlaceContext) {
        if (this.ew$targetClass.getClass() != CraftingTableBlock.class)
            return original;

        CraftingTableBlock instance = (CraftingTableBlock)this.ew$targetClass;

        int craftingTableType = 0;
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        Direction getDirectionFace = blockPlaceContext.getClickedFace();

        Direction directionToBaseBlock = null;

        for (Direction blockDirection : EW$HORIZONTAL) {
            blockDirection = blockDirection.getOpposite();
            BlockState candidateBlock = blockPlaceContext.getLevel().getBlockState(blockPos.relative(blockDirection));
            if (candidateBlock.is(instance) && candidateBlock.getValue(EWHelper.EXTENDED) == 0) {
                directionToBaseBlock = blockDirection;
                break;
            }
        }


        boolean flag = blockPlaceContext.isSecondaryUseActive();

        //Si el jugador hace shift click derecho sobre una mesa de crafteo
        if (getDirectionFace.getAxis().isHorizontal() && flag) {
            BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPos.relative(getDirectionFace.getOpposite()));

            if (blockState.is(instance) && blockState.getValue(EWHelper.EXTENDED) == 0)
                craftingTableType = EWHelper.directionToType(getDirectionFace.getOpposite());
        }


        if (craftingTableType == 0 && !flag && directionToBaseBlock != null)
            craftingTableType = EWHelper.directionToType(directionToBaseBlock);

        return original.setValue(EWHelper.EXTENDED, craftingTableType);
    }

}
