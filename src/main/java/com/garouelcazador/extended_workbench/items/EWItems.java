package com.garouelcazador.extended_workbench.items;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.garouelcazador.extended_workbench.items.custom.*;
import com.garouelcazador.extended_workbench.misc.EWArmorMaterials;
import com.garouelcazador.extended_workbench.misc.EWTiers;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public final class EWItems {

    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExtendedWorkbench.MOD_ID);

    public static final RegistryObject<Item> EXTENDED_WOODEN_SWORD = ITEMS.register("extended_wooden_sword", () -> new ExtendedSwordItem(EWTiers.WOOD, 4, -2.7F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_WOODEN_SHOVEL = ITEMS.register("extended_wooden_shovel", () -> new ExtendedShovelItem(EWTiers.WOOD, 1.5F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_WOODEN_PICKAXE = ITEMS.register("extended_wooden_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.WOOD, 1, -3.0F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_WOODEN_AXE = ITEMS.register("extended_wooden_axe", () -> new ExtendedAxeItem(EWTiers.WOOD, 7.0F, -3.3F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_WOODEN_HOE = ITEMS.register("extended_wooden_hoe", () -> new ExtendedHoeItem(EWTiers.WOOD, 0, 0.0F, 1.5F, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_STONE_SWORD = ITEMS.register("extended_stone_sword", () -> new ExtendedSwordItem(EWTiers.STONE, 4, -2.7F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_STONE_SHOVEL = ITEMS.register("extended_stone_shovel", () -> new ExtendedShovelItem(EWTiers.STONE, 1.5F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_STONE_PICKAXE = ITEMS.register("extended_stone_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.STONE, 1, -3.0F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_STONE_AXE = ITEMS.register("extended_stone_axe", () -> new ExtendedAxeItem(EWTiers.STONE, 8.0F, -3.3F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_STONE_HOE = ITEMS.register("extended_stone_hoe", () -> new ExtendedHoeItem(EWTiers.STONE, -1, 0.0F, 1.5F, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_GOLDEN_SWORD = ITEMS.register("extended_golden_sword", () -> new ExtendedSwordItem(EWTiers.GOLD, 4, -2.7F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_SHOVEL = ITEMS.register("extended_golden_shovel", () -> new ExtendedShovelItem(EWTiers.GOLD, 1.5F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_PICKAXE = ITEMS.register("extended_golden_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.GOLD, 1, -3.0F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_AXE = ITEMS.register("extended_golden_axe", () -> new ExtendedAxeItem(EWTiers.GOLD, 7.0F, -3.3F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_HOE = ITEMS.register("extended_golden_hoe", () -> new ExtendedHoeItem(EWTiers.GOLD, 0, 0.0F, 1.5F, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_IRON_SWORD = ITEMS.register("extended_iron_sword", () -> new ExtendedSwordItem(EWTiers.IRON, 4, -2.6F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_SHOVEL = ITEMS.register("extended_iron_shovel", () -> new ExtendedShovelItem(EWTiers.IRON, 1.5F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_PICKAXE = ITEMS.register("extended_iron_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.IRON, 1, -3.0F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_AXE = ITEMS.register("extended_iron_axe", () -> new ExtendedAxeItem(EWTiers.IRON, 7.0F, -3.2F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_HOE = ITEMS.register("extended_iron_hoe", () -> new ExtendedHoeItem(EWTiers.IRON, -2, 0.0F, 1.5F, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_DIAMOND_SWORD = ITEMS.register("extended_diamond_sword", () -> new ExtendedSwordItem(EWTiers.DIAMOND, 4, -2.6F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_SHOVEL = ITEMS.register("extended_diamond_shovel", () -> new ExtendedShovelItem(EWTiers.DIAMOND, 1.5F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_PICKAXE = ITEMS.register("extended_diamond_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.DIAMOND, 1, -3.0F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_AXE = ITEMS.register("extended_diamond_axe", () -> new ExtendedAxeItem(EWTiers.DIAMOND, 6.0F, -3.1F, 1.5F, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_HOE = ITEMS.register("extended_diamond_hoe", () -> new ExtendedHoeItem(EWTiers.DIAMOND, -3, 0.0F, 1.5F, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_NETHERITE_SWORD = ITEMS.register("extended_netherite_sword", () -> new ExtendedSwordItem(EWTiers.NETHERITE, 4, -2.6F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_SHOVEL = ITEMS.register("extended_netherite_shovel", () -> new ExtendedShovelItem(EWTiers.NETHERITE, 1.5F, -3.1F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_PICKAXE = ITEMS.register("extended_netherite_pickaxe", () -> new ExtendedPickaxeItem(EWTiers.NETHERITE, 1, -3.0F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_AXE = ITEMS.register("extended_netherite_axe", () -> new ExtendedAxeItem(EWTiers.NETHERITE, 6.0F, -3.1F, 1.5F, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_HOE = ITEMS.register("extended_netherite_hoe", () -> new ExtendedHoeItem(EWTiers.NETHERITE, -4, 0.0F, 1.5F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> EXTENDED_LEATHER_HELMET = ITEMS.register("extended_leather_helmet", () -> new ExtendedDyeableArmorItem(EWArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_LEATHER_CHESTPLATE = ITEMS.register("extended_leather_chestplate", () -> new ExtendedDyeableArmorItem(EWArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_LEATHER_LEGGINGS = ITEMS.register("extended_leather_leggings", () -> new ExtendedDyeableArmorItem(EWArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_LEATHER_BOOTS = ITEMS.register("extended_leather_boots", () -> new ExtendedDyeableArmorItem(EWArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_CHAINMAIL_HELMET = ITEMS.register("extended_chainmail_helmet", () -> new ArmorItem(EWArmorMaterials.CHAIN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_CHAINMAIL_CHESTPLATE = ITEMS.register("extended_chainmail_chestplate", () -> new ArmorItem(EWArmorMaterials.CHAIN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_CHAINMAIL_LEGGINGS = ITEMS.register("extended_chainmail_leggings", () -> new ArmorItem(EWArmorMaterials.CHAIN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_CHAINMAIL_BOOTS = ITEMS.register("extended_chainmail_boots", () -> new ArmorItem(EWArmorMaterials.CHAIN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_IRON_HELMET = ITEMS.register("extended_iron_helmet", () -> new ArmorItem(EWArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_CHESTPLATE = ITEMS.register("extended_iron_chestplate", () -> new ArmorItem(EWArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_LEGGINGS = ITEMS.register("extended_iron_leggings", () -> new ArmorItem(EWArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_IRON_BOOTS = ITEMS.register("extended_iron_boots", () -> new ArmorItem(EWArmorMaterials.IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_DIAMOND_HELMET = ITEMS.register("extended_diamond_helmet", () -> new ArmorItem(EWArmorMaterials.DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_CHESTPLATE = ITEMS.register("extended_diamond_chestplate", () -> new ArmorItem(EWArmorMaterials.DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_LEGGINGS = ITEMS.register("extended_diamond_leggings", () -> new ArmorItem(EWArmorMaterials.DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_DIAMOND_BOOTS = ITEMS.register("extended_diamond_boots", () -> new ArmorItem(EWArmorMaterials.DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_GOLDEN_HELMET = ITEMS.register("extended_golden_helmet", () -> new ExtendedGoldenArmorItem(EWArmorMaterials.GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_CHESTPLATE = ITEMS.register("extended_golden_chestplate", () -> new ExtendedGoldenArmorItem(EWArmorMaterials.GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_LEGGINGS = ITEMS.register("extended_golden_leggings", () -> new ExtendedGoldenArmorItem(EWArmorMaterials.GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> EXTENDED_GOLDEN_BOOTS = ITEMS.register("extended_golden_boots", () -> new ExtendedGoldenArmorItem(EWArmorMaterials.GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXTENDED_NETHERITE_HELMET = ITEMS.register("extended_netherite_helmet", () -> new ArmorItem(EWArmorMaterials.NETHERITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_CHESTPLATE = ITEMS.register("extended_netherite_chestplate", () -> new ArmorItem(EWArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_LEGGINGS = ITEMS.register("extended_netherite_leggings", () -> new ArmorItem(EWArmorMaterials.NETHERITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> EXTENDED_NETHERITE_BOOTS = ITEMS.register("extended_netherite_boots", () -> new ArmorItem(EWArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> EXTENDED_BOW = ITEMS.register("extended_bow", () -> new ExtendedBowItem(new Item.Properties().durability(480)));
    public static final RegistryObject<Item> EXTENDED_CROSSBOW = ITEMS.register("extended_crossbow", () -> new ExtendedCrossbowItem(new Item.Properties().durability(581)));

    public static final RegistryObject<Item> EXTENDED_SHIELD = ITEMS.register("extended_shield", () -> new ExtendedShieldItem(new Item.Properties().durability(504)));

    private EWItems(){}

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static Collection<RegistryObject<Item>> getEntries() {
        return ITEMS.getEntries();
    }
}
