package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.ForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.UUID;

@Mixin(ItemStack.class)
public final class ItemStackMixin {

    private ItemStackMixin(){}

    @Inject(
            method = "getTooltipLines",
            at = @At(
                    value = "INVOKE",
                    ordinal = 0,
                    target = "Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;getId()Ljava/util/UUID;",
                    shift = At.Shift.BEFORE
            )
    )
    private void extendedWorkbench$getTooltipLines(Player player, TooltipFlag pIsAdvanced, CallbackInfoReturnable<List<Component>> cir, @Local AttributeModifier attributemodifierRef, @Local LocalDoubleRef floatRef, @Local LocalBooleanRef booleanRef) {
        UUID id = attributemodifierRef.getId();
        if (id == EWHelper.BASE_ENTITY_REACH_UUID) {
            floatRef.set(floatRef.get() + player.getAttributeBaseValue(ForgeMod.ENTITY_REACH.get()));
            booleanRef.set(true);
        } else if (id == EWHelper.BASE_BLOCK_REACH_UUID) {
            floatRef.set(floatRef.get() + player.getAttributeBaseValue(ForgeMod.BLOCK_REACH.get()));
            booleanRef.set(true);
        }
    }
}
