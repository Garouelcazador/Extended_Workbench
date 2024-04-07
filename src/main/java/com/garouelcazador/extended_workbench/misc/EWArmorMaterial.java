package com.garouelcazador.extended_workbench.misc;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public final class EWArmorMaterial implements ArmorMaterial {
    private final ArmorMaterial tier;

    EWArmorMaterial(ArmorMaterial tier) {
        this.tier = tier;
    }

    @Override
    public int getDurabilityForType(ArmorItem.@NotNull Type type) {
        return Math.round(this.tier.getDurabilityForType(type) * 1.25F);
    }

    @Override
    public int getDefenseForType(ArmorItem.@NotNull Type type) {
        return this.tier.getDefenseForType(type);
    }

    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.tier.getEquipSound();
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.tier.getRepairIngredient();
    }

    @Override
    public @NotNull String getName() {
        return ExtendedWorkbench.MOD_ID + ":extended_" + this.tier.getName();
    }

    @Override
    public float getToughness() {
        return this.tier.getToughness() + 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return this.tier.getKnockbackResistance();
    }
}
