package com.garouelcazador.extended_workbench.misc;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.garouelcazador.extended_workbench.items.EWItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class EWCreativeModeTab {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtendedWorkbench.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EXTENDED_WORKBENCH_TAB = CREATIVE_MODE_TAB.register(ExtendedWorkbench.MOD_ID, () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(EWItems.EXTENDED_NETHERITE_SWORD.get()))
            .title(Component.translatable("itemGroup." + ExtendedWorkbench.MOD_ID))
            .displayItems((parameters, output) -> EWItems.getEntries().forEach(registry -> output.accept(registry.get()))).build()
    );

    private EWCreativeModeTab(){}

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TAB.register(modEventBus);
    }


}
