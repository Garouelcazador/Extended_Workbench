package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.custom.ExtendedShieldItem;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public final class PlayerMixin {

    private PlayerMixin(){}

    @ModifyVariable(
            method = "disableShield",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/player/Player;random:Lnet/minecraft/util/RandomSource;",
                    shift = At.Shift.BEFORE
            )
    )
    private float extendedWorkbench$disableShield(float value) {
        return ((Player)(Object)this).getUseItem().getItem() instanceof ExtendedShieldItem ? value - 0.25F : value;
    }
}
