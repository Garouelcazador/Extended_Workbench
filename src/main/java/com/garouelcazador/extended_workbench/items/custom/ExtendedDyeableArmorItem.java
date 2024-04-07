package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.items.EWItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

public class ExtendedDyeableArmorItem extends DyeableArmorItem {
    public ExtendedDyeableArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }


    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return super.canWalkOnPowderedSnow(stack, wearer) || stack.is(EWItems.EXTENDED_LEATHER_BOOTS.get());
    }
}
