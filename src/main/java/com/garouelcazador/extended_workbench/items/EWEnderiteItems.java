package com.garouelcazador.extended_workbench.items;

import com.garouelcazador.extended_workbench.items.custom.*;
import com.garouelcazador.extended_workbench.misc.EWArmorMaterials;
import com.garouelcazador.extended_workbench.misc.EWTiers;
import net.enderitemc.enderitemod.forge.tools.EnderiteElytraChestplate;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;

import static com.garouelcazador.extended_workbench.items.EWItems.ITEMS;
import static net.enderitemc.enderitemod.EnderiteMod.CONFIG;

public final class EWEnderiteItems {

    private EWEnderiteItems(){}

    public static final RegistryObject<Item> EXTENDED_ENDERITE_SWORD = ITEMS.register("extended_enderite_sword", () -> new ExtendedEnderiteSwordItem(EWTiers.ENDERITE_SUPPLIER.get(), CONFIG.tools.enderiteSwordAD + 1, -2.6F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_SHOVEL = ITEMS.register("extended_enderite_shovel", () -> new ExtendedShovelItem(EWTiers.ENDERITE_SUPPLIER.get(), CONFIG.tools.enderiteShovelAD, -3.1F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_PICKAXE = ITEMS.register("extended_enderite_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.ENDERITE_SUPPLIER.get(), CONFIG.tools.enderitePickaxeAD, -3.0F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_AXE = ITEMS.register("extended_enderite_axe", () -> new ExtendedAxeItem(EWTiers.ENDERITE_SUPPLIER.get(), CONFIG.tools.enderiteAxeAD + 1, -3.1F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_HOE = ITEMS.register("extended_enderite_hoe", () -> new ExtendedHoeItem(EWTiers.ENDERITE_SUPPLIER.get(), CONFIG.tools.enderiteHoeAD, 0.0F, 1.5F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> EXTENDED_ENDERITE_HELMET = ITEMS.register("extended_enderite_helmet", () -> new SimpleEnderMask(EWArmorMaterials.ENDERITE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_CHESTPLATE = ITEMS.register("extended_enderite_chestplate", () -> new ArmorItem(EWArmorMaterials.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_LEGGINGS = ITEMS.register("extended_enderite_leggings", () -> new ArmorItem(EWArmorMaterials.ENDERITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_BOOTS = ITEMS.register("extended_enderite_boots", () -> new ArmorItem(EWArmorMaterials.ENDERITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> EXTENDED_ENDERITE_BOW = ITEMS.register("extended_enderite_bow", () -> new ExtendedEnderiteBow(new Item.Properties().fireResistant().durability(960)));
    public static final RegistryObject<Item> EXTENDED_ENDERITE_CROSSBOW = ITEMS.register("extended_enderite_crossbow", () -> new ExtendedEnderiteCrossbowItem(new Item.Properties().fireResistant().durability(960)));

    public static final RegistryObject<Item> EXTENDED_ENDERITE_SHIELD = ITEMS.register("extended_enderite_shield", () -> new ExtendedEnderiteShieldItem(new Item.Properties().fireResistant().durability(960)));

    public static final RegistryObject<Item> EXTENDED_ENDERITE_ELYTRA_CHESTPLATE = ITEMS.register("extended_enderite_elytra_chestplate", () -> new EnderiteElytraChestplate(EWArmorMaterials.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().durability(1280).rarity(Rarity.EPIC)));

    public static void load(){}
}
