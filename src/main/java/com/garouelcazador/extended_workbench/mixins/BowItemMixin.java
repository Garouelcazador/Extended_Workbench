package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.custom.ExtendedBowItem;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BowItem.class)
public final class BowItemMixin {

    private BowItemMixin(){}

    @WrapOperation(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"
            )
    )
    private float extendedWorkbench$releaseUsing(int i, Operation<Float> original, ItemStack bowStack) {
        Item item = bowStack.getItem();
        return item instanceof ExtendedBowItem ? ExtendedBowItem.getPowerForTime(i) : original.call(i);
    }

    @ModifyArg(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"
            )
    )
    private int extendedWorkbench$releaseUsing(int arg, @Local(argsOnly = true) Level level, @Local float f) {
        return f >= 1.3F && level.random.nextInt(4) == 0 ? arg + 1 : arg;
    }

    /*@Expression("f == 1.0")
    @Definition(id = "f", local = @Local(type = float.class))
    @WrapOperation(
            method = "releaseUsing",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private boolean extendedWorkbench$releaseUsing(float left, float right, Operation<Boolean> original, ItemStack bowStack) {
        Item item = bowStack.getItem();
        return original.call(left, right) || item instanceof ExtendedBowItem && left > right;
    }*/
}
