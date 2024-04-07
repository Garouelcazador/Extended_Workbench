package com.garouelcazador.extended_workbench.misc;

import net.enderitemc.enderitemod.materials.EnderiteArmorMaterial;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraftforge.fml.ModList;

import java.util.function.Supplier;

public final class EWArmorMaterials {
    public static final ArmorMaterial LEATHER = upgradeTier(ArmorMaterials.LEATHER);
    public static final ArmorMaterial CHAIN = upgradeTier(ArmorMaterials.CHAIN);
    public static final ArmorMaterial IRON = upgradeTier(ArmorMaterials.IRON);
    public static final ArmorMaterial GOLD = upgradeTier(ArmorMaterials.GOLD);
    public static final ArmorMaterial DIAMOND = upgradeTier(ArmorMaterials.DIAMOND);
    public static final ArmorMaterial NETHERITE = upgradeTier(ArmorMaterials.NETHERITE);
    public static final ArmorMaterial ENDERITE = ModList.get().isLoaded("enderitemod") ? upgradeTier(EnderiteArmorMaterial.ENDERITE) : null;

    private EWArmorMaterials(){}

    private static ArmorMaterial upgradeTier(ArmorMaterial tier) {
        return new EWArmorMaterial(tier);
    }

}
