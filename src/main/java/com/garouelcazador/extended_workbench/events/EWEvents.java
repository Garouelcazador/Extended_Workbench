package com.garouelcazador.extended_workbench.events;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.garouelcazador.extended_workbench.items.EWItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public final class EWEvents {

    private EWEvents(){}

    @Mod.EventBusSubscriber(modid = ExtendedWorkbench.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static final class ForgeEvents {

        private ForgeEvents(){}

        @SubscribeEvent
        public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
            ItemStack itemStack = event.getItemStack();
            if (itemStack.is(EWItems.EXTENDED_BOW.get()) || itemStack.is(EWItems.EXTENDED_CROSSBOW.get())) {
                event.setBurnTime(450);
            } else if (itemStack.is(EWItems.EXTENDED_WOODEN_SHOVEL.get()) || itemStack.is(EWItems.EXTENDED_WOODEN_SWORD.get()) || itemStack.is(EWItems.EXTENDED_WOODEN_HOE.get()) || itemStack.is(EWItems.EXTENDED_WOODEN_AXE.get()) || itemStack.is(EWItems.EXTENDED_WOODEN_PICKAXE.get())) {
                event.setBurnTime(300);
            }
        }
    }
}
