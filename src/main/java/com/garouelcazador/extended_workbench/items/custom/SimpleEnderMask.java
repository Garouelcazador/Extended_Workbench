package com.garouelcazador.extended_workbench.items.custom;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class SimpleEnderMask extends ArmorItem {
    public SimpleEnderMask(ArmorMaterial armorMaterial, Properties properties) {
        super(armorMaterial, Type.HELMET, properties);
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return true;
    }
}
