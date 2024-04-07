package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(BlockBehaviour.class)
public final class BlockBehaviourMixin {

    private BlockBehaviourMixin(){}

    @ModifyReturnValue(
            method = "updateShape",
            at = @At("RETURN")
    )
    //Se actualiza cada que cambia un bloque al lado suyo ejemplo si el cactus tiene un bloque al lado se rompe
    //Basicamente si hay una mesa de crafteo solitaria al lado de *esto* se actualizara hacia la direccion de la mesa al lado suyo
    private BlockState extendedWorkbench$updateShape(BlockState original, BlockState mainBlock, Direction neighborBlockDirection, BlockState neighborBlock, LevelAccessor levelAccessor, BlockPos mainPos, BlockPos neighborPos) {
        BlockBehaviour targetClass = (BlockBehaviour)(Object)this;
        if (targetClass.getClass() != CraftingTableBlock.class || !neighborBlockDirection.getAxis().isHorizontal())
            return original;

        CraftingTableBlock instance = (CraftingTableBlock)targetClass;

        int type = original.getValue(EWHelper.EXTENDED);
        Direction direction = EWHelper.typeToDirection(type);

        if (type == 0 && neighborBlock.is(instance) && neighborBlock.getValue(EWHelper.EXTENDED) == EWHelper.directionToType(neighborBlockDirection.getOpposite())) {
            return original.setValue(EWHelper.EXTENDED, EWHelper.directionToType(neighborBlockDirection));
        } else if (direction != null) {
            BlockState candidateBlock = levelAccessor.getBlockState(mainPos.relative(direction));

            if (type != 0 && candidateBlock.is(instance) && candidateBlock.getValue(EWHelper.EXTENDED) != 0)
                return original;
        }

        return original.setValue(EWHelper.EXTENDED, 0);
    }

}
