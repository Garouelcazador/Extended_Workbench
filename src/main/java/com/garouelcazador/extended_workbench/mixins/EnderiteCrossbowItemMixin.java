package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.garouelcazador.extended_workbench.items.custom.ExtendedCrossbowItem;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteCrossbowItem;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.enderitemc.enderitemod.tools.EnderiteCrossbow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EnderiteCrossbow.class, remap = false)
public final class EnderiteCrossbowItemMixin {

    private EnderiteCrossbowItemMixin(){}

    @Inject(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/enderitemc/enderitemod/tools/EnderiteCrossbow;setCharged(Lnet/minecraft/world/item/ItemStack;Z)V",
                    shift = At.Shift.AFTER
            )
    )
    private void extendedWorkbench$releaseUsing(ItemStack crossbowStack, Level p_40876_, LivingEntity p_40877_, int p_40878_, CallbackInfo ci, @Local float f) {
        if (f >= 1.5F) ExtendedCrossbowItem.setExtendedCharge(crossbowStack, true);
    }

    @ModifyArg(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/enderitemc/enderitemod/tools/EnderiteCrossbow;shootAll(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;FF)V"
            ),
            index = 4
    )
    private float extendedWorkbench$use(float value, @Local ItemStack crossbow) {
        return ExtendedCrossbowItem.hasExtendedCharge(crossbow) ? value * 1.5F : value;
    }

    @ModifyExpressionValue(
            method = "getSpeed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private static boolean extendedWorkbench$getSpeed(boolean original, ItemStack stack) {
        return original || stack.is(EWEnderiteItems.EXTENDED_ENDERITE_CROSSBOW.get());
    }

    @ModifyExpressionValue(
            method = "getPullProgress",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=1.0F"
            ),
            remap = false
    )
    private static float extendedWorkbench$getPowerForTime(float original, @Local(argsOnly = true) ItemStack crossbowStack) {
        return crossbowStack.getItem() instanceof ExtendedEnderiteCrossbowItem ? 1.5F : original;
    }
}
