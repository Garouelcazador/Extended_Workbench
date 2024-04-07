package com.garouelcazador.extended_workbench.mixins.client;

import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.garouelcazador.extended_workbench.items.EWItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SkeletonModel.class)
public final class SkeletonModelMixin {

    private SkeletonModelMixin(){}

    @ModifyExpressionValue(
            method = "prepareMobModel(Lnet/minecraft/world/entity/Mob;FFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean extendedWorkbench$prepareMobModel(boolean original, @Local ItemStack itemStack) {
        return original || itemStack.is(EWItems.EXTENDED_BOW.get()) || ModList.get().isLoaded("enderitemod") && itemStack.is(EWEnderiteItems.EXTENDED_ENDERITE_BOW.get());
    }

    @ModifyExpressionValue(
            method = "setupAnim(Lnet/minecraft/world/entity/Mob;FFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean extendedWorkbench$setupAnim(boolean original, @Local ItemStack itemStack) {
        return original || itemStack.is(EWItems.EXTENDED_BOW.get()) || ModList.get().isLoaded("enderitemod") && itemStack.is(EWEnderiteItems.EXTENDED_ENDERITE_BOW.get());
    }
}
