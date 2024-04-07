package com.garouelcazador.extended_workbench.screens.inventory;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EWMenuTypes {
    private EWMenuTypes(){}

    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ExtendedWorkbench.MOD_ID);

    public static final RegistryObject<MenuType<ExtendedCraftingMenu>> EXTENDED_WORKBENCH_MENU =
            MENUS.register("extended_workbench_menu", () -> IForgeMenuType.create((windowId, inv, ignored) -> new ExtendedCraftingMenu(windowId, inv)));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
