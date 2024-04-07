package com.garouelcazador.extended_workbench.events;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.garouelcazador.extended_workbench.client.model.ExtendedShieldModel;
import com.garouelcazador.extended_workbench.client.model.geom.EWModelLayers;
import com.garouelcazador.extended_workbench.items.EWEnderiteItems;
import com.garouelcazador.extended_workbench.items.EWItems;
import com.garouelcazador.extended_workbench.items.custom.ExtendedBowItem;
import com.garouelcazador.extended_workbench.items.custom.ExtendedCrossbowItem;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteBow;
import com.garouelcazador.extended_workbench.screens.inventory.EWMenuTypes;
import com.garouelcazador.extended_workbench.screens.inventory.ExtendedCraftingScreen;
import net.enderitemc.enderitemod.tools.EnderiteCrossbow;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class EWClientEvents {

    private EWClientEvents(){}

    @Mod.EventBusSubscriber(modid = ExtendedWorkbench.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static final class ClientForgeEvents {

        private ClientForgeEvents(){}

        @SubscribeEvent
        public static void onFOVUpdate(ComputeFovModifierEvent event) {
            Player entity = event.getPlayer();
            Item item = entity.getUseItem().getItem();
            if (!(item instanceof ExtendedBowItem || ModList.get().isLoaded("enderitemod") && item instanceof ExtendedEnderiteBow)) return;

            float fovModifier = (int)(entity.getTicksUsingItem() * 0.8F) / (float) ExtendedBowItem.MAX_DRAW_DURATION;
            if (fovModifier > 1.0F) {
                fovModifier = 1.0F;
            } else {
                fovModifier *= fovModifier;
            }

            event.setNewFovModifier(event.getNewFovModifier() * (1.0F - fovModifier * 0.15F));
        }
    }

    @Mod.EventBusSubscriber(modid = ExtendedWorkbench.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static final class ClientModEvents {

        private ClientModEvents(){}

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(EWMenuTypes.EXTENDED_WORKBENCH_MENU.get(), ExtendedCraftingScreen::new);

            event.enqueueWork(() -> {

                //Bow
                ItemProperties.register(EWItems.EXTENDED_BOW.get(), new ResourceLocation("pull"), ((pStack, pLevel, pEntity, pSeed) -> {
                    if (pEntity == null) {
                        return 0.0F;
                    } else {
                        return pEntity.getUseItem() != pStack ? 0.0F : (float) (pStack.getUseDuration() - pEntity.getUseItemRemainingTicks()) / 20.0F;
                    }
                }));
                ItemProperties.register(EWItems.EXTENDED_BOW.get(), new ResourceLocation("pulling"), ((pStack, pLevel, pEntity, pSeed) -> pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0.0F));


                //Crossbow
                ItemProperties.register(EWItems.EXTENDED_CROSSBOW.get(), new ResourceLocation("pull"), (p_174610_, p_174611_, p_174612_, p_174613_) -> {
                    if (p_174612_ == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(p_174610_) ? 0.0F : (float) (p_174610_.getUseDuration() - p_174612_.getUseItemRemainingTicks()) / (float) CrossbowItem.getChargeDuration(p_174610_);
                    }
                });
                ItemProperties.register(EWItems.EXTENDED_CROSSBOW.get(), new ResourceLocation("pulling"), (p_174605_, p_174606_, p_174607_, p_174608_) -> {
                    return p_174607_ != null && p_174607_.isUsingItem() && p_174607_.getUseItem() == p_174605_ && !CrossbowItem.isCharged(p_174605_) ? 1.0F : 0.0F;
                });
                ItemProperties.register(EWItems.EXTENDED_CROSSBOW.get(), new ResourceLocation("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> {
                    return CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F;
                });
                ItemProperties.register(EWItems.EXTENDED_CROSSBOW.get(), new ResourceLocation("firework"), (p_275887_, p_275888_, p_275889_, p_275890_) -> {
                    return CrossbowItem.isCharged(p_275887_) && CrossbowItem.containsChargedProjectile(p_275887_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                });

                //Shield
                ItemProperties.register(EWItems.EXTENDED_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F);

                if (ModList.get().isLoaded("enderitemod")) {
                    //Elytra chestplate
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_ELYTRA_CHESTPLATE.get(), new ResourceLocation("broken"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
                        return ElytraItem.isFlyEnabled(p_174590_) ? 0.0F : 1.0F;
                    });

                    //Enderite bow
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_BOW.get(), new ResourceLocation("pull"), ((pStack, pLevel, pEntity, pSeed) -> {
                        if (pEntity == null) {
                            return 0.0F;
                        } else {
                            return pEntity.getUseItem() != pStack ? 0.0F : (float) (pStack.getUseDuration() - pEntity.getUseItemRemainingTicks()) / 20.0F;
                        }
                    }));
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_BOW.get(), new ResourceLocation("pulling"), ((pStack, pLevel, pEntity, pSeed) -> pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0.0F));

                    //Enderite crossbow
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_CROSSBOW.get(), new ResourceLocation("pull"), (p_174610_, p_174611_, p_174612_, p_174613_) -> {
                        if (p_174612_ == null) {
                            return 0.0F;
                        } else {
                            return EnderiteCrossbow.isCharged(p_174610_) ? 0.0F : (float) (p_174610_.getUseDuration() - p_174612_.getUseItemRemainingTicks()) / (float) EnderiteCrossbow.getPullTime(p_174610_);
                        }
                    });
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_CROSSBOW.get(), new ResourceLocation("pulling"), (p_174605_, p_174606_, p_174607_, p_174608_) -> {
                        return p_174607_ != null && p_174607_.isUsingItem() && p_174607_.getUseItem() == p_174605_ && !EnderiteCrossbow.isCharged(p_174605_) ? 1.0F : 0.0F;
                    });
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_CROSSBOW.get(), new ResourceLocation("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> {
                        return CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F;
                    });
                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_CROSSBOW.get(), new ResourceLocation("firework"), (p_275887_, p_275888_, p_275889_, p_275890_) -> {
                        return CrossbowItem.isCharged(p_275887_) && CrossbowItem.containsChargedProjectile(p_275887_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                    });

                    ItemProperties.register(EWEnderiteItems.EXTENDED_ENDERITE_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F);

                }
            });
        }

        @SubscribeEvent
        public static void registerColorHandlersEvent(RegisterColorHandlersEvent.Item event) {
            event.register((itemStack, color) -> color > 0 ? -1 : ((DyeableLeatherItem)itemStack.getItem()).getColor(itemStack), EWItems.EXTENDED_LEATHER_HELMET.get(), EWItems.EXTENDED_LEATHER_CHESTPLATE.get(), EWItems.EXTENDED_LEATHER_LEGGINGS.get(), EWItems.EXTENDED_LEATHER_BOOTS.get());
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(EWModelLayers.EXTENDED_SHIELD, ExtendedShieldModel::createLayer);
        }

    }
}
