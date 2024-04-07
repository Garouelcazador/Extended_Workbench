package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.custom.ExtendedCrossbowItem;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteCrossbowItem;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public final class CrossbowItemMixin {

    private CrossbowItemMixin(){}

    /*@ModifyVariable(
            method = "shootProjectile",
            at = @At(
                    value = "INVOKE",
                    ordinal = 0,
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z",
                    shift = At.Shift.BEFORE
            ),
            argsOnly = true,
            ordinal = 1
    )
    private static float extendedWorkbench$shootProjectile(float value, @Local(argsOnly = true, ordinal = 0) LivingEntity entity, @Local(argsOnly = true, ordinal = 0) ItemStack itemStack) {
        return !(entity instanceof Player) && itemStack.getItem() instanceof ExtendedCrossbowItem && ExtendedCrossbowItem.hasExtendedCharge(itemStack) ? value * 1.5F : value;
    }*/

    @Inject(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/CrossbowItem;setCharged(Lnet/minecraft/world/item/ItemStack;Z)V",
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
                    target = "Lnet/minecraft/world/item/CrossbowItem;performShooting(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;FF)V"
            ),
            index = 4
    )
    private float extendedWorkbench$use(float value, @Local ItemStack crossbow) {
        return ExtendedCrossbowItem.hasExtendedCharge(crossbow) ? value * 1.5F : value;
    }

    @ModifyArg(
            method = "shootProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"
            ),
            index = 0
    )
    private static int extendedWorkbench$shootProjectile(int amount, @Local(argsOnly = true) Level level, @Local(argsOnly = true, ordinal = 0) ItemStack itemStack) {
        Item item = itemStack.getItem();
        return (item instanceof ExtendedCrossbowItem || ModList.get().isLoaded("enderitemod") && item instanceof ExtendedEnderiteCrossbowItem) && ExtendedCrossbowItem.hasExtendedCharge(itemStack) && level.random.nextInt(4) == 0 ? amount + 1 : amount;
    }

    @ModifyExpressionValue(
            method = "getPowerForTime",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=1.0F"
            )
    )
    private static float extendedWorkbench$getPowerForTime(float original, @Local(argsOnly = true) ItemStack crossbowStack) {
        return crossbowStack.getItem() instanceof ExtendedCrossbowItem ? 1.5F : original;
    }
}
