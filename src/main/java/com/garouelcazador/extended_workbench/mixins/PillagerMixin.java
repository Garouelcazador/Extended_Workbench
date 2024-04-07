package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.garouelcazador.extended_workbench.items.EWItems;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteBow;
import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.garouelcazador.extended_workbench.config.EWServerConfig;
import com.garouelcazador.extended_workbench.items.custom.ExtendedBowItem;
import com.google.common.base.MoreObjects;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Pillager.class)
public final class PillagerMixin {

    private PillagerMixin(){}

    @ModifyArg(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Pillager;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"
            ),
            index = 1
    )
    private ItemStack extendedWorkbench$populateDefaultEquipmentSlots(ItemStack itemStack, @Local(argsOnly = true) RandomSource random) {
        return random.nextFloat() < EWHelper.getReplaceNativeEquipmentChance() ? MoreObjects.firstNonNull(EWHelper.tryToConvertExtendedVersion(itemStack), itemStack) : itemStack;
    }

    @ModifyArg(
            method = "shootCrossbowProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Pillager;shootCrossbowProjectile(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/projectile/Projectile;FF)V"
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
        return original || weaponItem == EWItems.EXTENDED_CROSSBOW.get() || ModList.get().isLoaded("enderitemod") && weaponItem == EWEnderiteItems.EXTENDED_ENDERITE_BOW.get();
    }
}
