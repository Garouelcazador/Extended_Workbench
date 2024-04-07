package com.garouelcazador.extended_workbench;

import com.garouelcazador.extended_workbench.config.EWServerConfig;
import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.garouelcazador.extended_workbench.items.EWItems;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteBow;
import com.garouelcazador.extended_workbench.misc.EWCreativeModeTab;
import com.garouelcazador.extended_workbench.misc.EWTiers;
import com.garouelcazador.extended_workbench.screens.inventory.EWMenuTypes;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(ExtendedWorkbench.MOD_ID)
public final class ExtendedWorkbench {
    public static final String MOD_ID = "extended_workbench";
    //private static final Logger LOGGER = LogUtils.getLogger();
    //Extended: Trident, Crossbow, Shield, Casco de tortuga incluso.
    //Compatible con otros mods de mesas de crafteo y enderite (opcionalmente)
    //Mejorar tooltips verdes


    public ExtendedWorkbench() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, EWServerConfig.SPEC);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EWItems.register(modEventBus);
        EWMenuTypes.register(modEventBus);
        EWCreativeModeTab.register(modEventBus);

        if (ModList.get().isLoaded("enderitemod")) {
            EWEnderiteItems.load();
        }

        ShapedRecipe.setCraftingSize(3, 6);
    }

}
