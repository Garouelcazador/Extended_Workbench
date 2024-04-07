package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.EWItems;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteBow;
import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.garouelcazador.extended_workbench.config.EWServerConfig;
import com.garouelcazador.extended_workbench.items.custom.ExtendedBowItem;
import com.google.common.base.MoreObjects;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Piglin.class)
public final class PiglinMixin {

    private PiglinMixin(){}

    @ModifyArg(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/piglin/Piglin;maybeWearArmor(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/util/RandomSource;)V"
            ),
            index = 1
    )
    private ItemStack extendedWorkbench$populateDefaultEquipmentSlots(ItemStack itemStack, @Local(argsOnly = true) RandomSource random) {
        return random.nextFloat() < EWHelper.getReplaceEquipmentChance() ? MoreObjects.firstNonNull(EWHelper.tryToConvertExtendedVersion(itemStack), itemStack) : itemStack;
    }

    @ModifyArg(
            method = "shootCrossbowProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/piglin/Piglin;shootCrossbowProjectile(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/projectile/Projectile;FF)V"
            ),
            index = 4
    )
    private float extendedWorkbench$populateDefaultEquipmentSlots(float force, @Local(argsOnly = true) ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof ExtendedBowItem || ModList.get().isLoaded("enderitemod") && item instanceof ExtendedEnderiteBow ? force * 1.5F : force;
    }

    @ModifyReturnValue(
            method = "canFireProjectileWeapon",
            at = @At("RETURN")
    )
    private boolean extendedWorkbench$canFireProjectileWeapon(boolean original, ProjectileWeaponItem weaponItem) {
        return original || weaponItem == EWItems.EXTENDED_CROSSBOW.get();
    }
}
