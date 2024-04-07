package com.garouelcazador.extended_workbench.mixins;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerEntity.class)
public final class ServerEntityMixin {
    @Shadow @Final private Entity entity;

    private ServerEntityMixin(){}

    @Expression("this.tickCount % this.updateInterval == 0")
    @Definition(id = "tickCount", field = "Lnet/minecraft/server/level/ServerEntity;tickCount:I")
    @Definition(id = "updateInterval", field = "Lnet/minecraft/server/level/ServerEntity;updateInterval:I")
    @WrapOperation(
            method = "sendChanges",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private boolean extendedWorkbench$sendChanges(int left, int right, Operation<Boolean> original) {
        return original.call(left, right) || this.entity instanceof AbstractArrow arrow && arrow.getDeltaMovement().lengthSqr() > 16;
    }
}
