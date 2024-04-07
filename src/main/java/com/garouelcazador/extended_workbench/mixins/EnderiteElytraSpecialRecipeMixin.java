package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.enderitemc.enderitemod.misc.EnderiteElytraSpecialRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = EnderiteElytraSpecialRecipe.class, remap = false)
public final class EnderiteElytraSpecialRecipeMixin {

    private EnderiteElytraSpecialRecipeMixin(){}

    @ModifyExpressionValue(
            method = {
                    "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z",
                    "craft"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )

    )
    private boolean extendedWorkbench$extendedElytraChestplate(boolean original, @Local(ordinal = 2) ItemStack itemStack) {
        return original || itemStack.is(EWEnderiteItems.EXTENDED_ENDERITE_CHESTPLATE.get());
    }

    @ModifyArg(
            method = "craft",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;)V"
            )
    )
    private ItemLike extendedWorkbench$craft(ItemLike item, @Local(ordinal = 0) ItemStack itemStack) {
        return itemStack.is(EWEnderiteItems.EXTENDED_ENDERITE_CHESTPLATE.get()) ? EWEnderiteItems.EXTENDED_ENDERITE_ELYTRA_CHESTPLATE.get() : item;
    }
}
