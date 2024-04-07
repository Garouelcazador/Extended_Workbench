package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.garouelcazador.extended_workbench.screens.inventory.ExtendedCraftingMenu;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingTableBlock.class)
public final class CraftingTableBlockMixin extends Block {
    @Unique private final CraftingTableBlock ew$targetClass = (CraftingTableBlock) (Object) this;

    private CraftingTableBlockMixin(Properties properties) {
        super(properties);
    }


    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void extendedWorkbench$init(BlockBehaviour.Properties p_52225_, CallbackInfo ci) {
        if (this.ew$targetClass.getClass() == CraftingTableBlock.class)
            this.registerDefaultState(this.stateDefinition.any().setValue(EWHelper.EXTENDED, 0));
    }

    @ModifyReturnValue(
            method = "getMenuProvider",
            at = @At("RETURN")
    )
    private MenuProvider extendedWorkbench$getMenuProvider(MenuProvider original, BlockState blockState, Level level, BlockPos blockPos) {
        if (this.ew$targetClass.getClass() == CraftingTableBlock.class) {
            int value = blockState.getValue(EWHelper.EXTENDED);
            return value >= 1 && value <= 4 ? new SimpleMenuProvider((integer, inventory, player) ->
                    new ExtendedCraftingMenu(integer, inventory, ContainerLevelAccess.create(level, blockPos)), EWHelper.CONTAINER_TITLE) : original;
        }
        return original;
    }

    /*@ModifyArg(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;openMenu(Lnet/minecraft/world/MenuProvider;)Ljava/util/OptionalInt;"
            )
    )
    private MenuProvider extendedWorkbench$use(MenuProvider menuProvider, @Local BlockState blockState, @Local Level level, @Local BlockPos blockPos) {
        int value = blockState.getValue(EWConstants.EXTENDED);
        return value >= 1 && value <= 4 ? extendedWorkbench$getMenuProvider(level, blockPos) : menuProvider;
    }

    @Unique
    private static @NotNull MenuProvider extendedWorkbench$getMenuProvider(@NotNull Level level, @NotNull BlockPos blockPos) {
        return new SimpleMenuProvider((integer, inventory, player) ->
                new ExtendedCraftingMenu(integer, inventory, ContainerLevelAccess.create(level, blockPos)), CraftingTableBlock.CONTAINER_TITLE);
    }*/

}
