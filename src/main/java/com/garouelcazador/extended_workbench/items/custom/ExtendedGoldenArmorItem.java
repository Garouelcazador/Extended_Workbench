package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.misc.EWArmorMaterials;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class ExtendedGoldenArmorItem extends ArmorItem {
    public ExtendedGoldenArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return super.makesPiglinsNeutral(stack, wearer) || stack.getItem() instanceof ArmorItem armorItem && armorItem.getMaterial() == EWArmorMaterials.GOLD;
    }
}
