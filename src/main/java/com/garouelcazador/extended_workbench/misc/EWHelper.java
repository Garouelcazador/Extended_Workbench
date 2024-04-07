package com.garouelcazador.extended_workbench.misc;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.garouelcazador.extended_workbench.config.EWServerConfig;
import com.garouelcazador.extended_workbench.items.EWItems;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public final class EWHelper {
    public static final IntegerProperty EXTENDED = IntegerProperty.create("extended", 0, 4);
    public static final Component CONTAINER_TITLE = Component.translatable("container." + ExtendedWorkbench.MOD_ID + ".extended_crafting");
    public static final UUID BASE_ENTITY_REACH_UUID = UUID.fromString("BDB63367-5830-46B0-B974-DC6DA3DEDC26");
    public static final UUID BASE_BLOCK_REACH_UUID = UUID.fromString("1E1B9A06-440F-4344-A35D-4B644C2A030E");

    private EWHelper(){}

    public static double getReplaceEquipmentChance() {
        try {
            return EWServerConfig.EXTENDED_EQUIPMENT_PROBABILITY.get();
        } catch (Exception ignored) {
            return EWServerConfig.EXTENDED_EQUIPMENT_PROBABILITY.getDefault();
        }
    }

    public static double getReplaceNativeEquipmentChance() {
        try {
            return EWServerConfig.NATIVE_EXTENDED_EQUIPMENT_PROBABILITY.get();
        } catch (Exception ignored) {
            return EWServerConfig.NATIVE_EXTENDED_EQUIPMENT_PROBABILITY.getDefault();
        }
    }

    public static Item tryToConvertExtendedVersion(Item originalItem) {
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(originalItem);
        if (itemKey == null) return originalItem;
        for (RegistryObject<Item> item1 : EWItems.getEntries()) {
            if (item1.getId().getPath().equals("extended_" + itemKey.getPath())) {
                return item1.orElse(originalItem);
            }
        }
        return originalItem;
    }

    public static ItemStack tryToConvertExtendedVersion(ItemStack originalStack) {
        Item item = originalStack.getItem();
        Item extendedVersion = EWHelper.tryToConvertExtendedVersion(item);
        if (extendedVersion == item)
            return originalStack;
        ItemStack extendedStack = new ItemStack(extendedVersion);
        extendedStack.getOrCreateTag().merge(originalStack.getOrCreateTag());
        return extendedStack;
    }

    public static int directionToType(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return 4;
            }
            case EAST -> {
                return 1;
            }
            case SOUTH -> {
                return 2;
            }
            case WEST -> {
                return 3;
            }
            default -> throw new IllegalStateException("Invalid cardinal point from: " + ExtendedWorkbench.MOD_ID);
        }
    }

    public static Direction typeToDirection(int type) {
        switch (type) {
            case 4 -> {
                return Direction.NORTH;
            }
            case 1 -> {
                return Direction.EAST;
            }
            case 2 -> {
                return Direction.SOUTH;
            }
            case 3 -> {
                return Direction.WEST;
            }
            default -> {
                return null;
            }
        }
    }
}
