package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.garouelcazador.extended_workbench.config.EWServerConfig;
import com.google.common.base.MoreObjects;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Mob.class)
public final class MobMixin {

    private MobMixin(){}

    @ModifyVariable(
            method = "populateDefaultEquipmentSlots",
            at = @At("STORE"),
            ordinal = 0
    )
    private Item extendedWorkbench$populateDefaultEquipmentSlots(Item item, RandomSource random) {
        return item != null && random.nextFloat() < EWHelper.getReplaceEquipmentChance() ? MoreObjects.firstNonNull(EWHelper.tryToConvertExtendedVersion(item), item) : item;
    }
}
