package com.garouelcazador.extended_workbench.mixins;

import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteShieldItem;
import com.garouelcazador.extended_workbench.items.custom.ExtendedEnderiteSwordItem;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingMenu.class)
public abstract class SmithingMenuMixin extends ItemCombinerMenu {

    public SmithingMenuMixin(@Nullable MenuType<?> p_39773_, int p_39774_, Inventory p_39775_, ContainerLevelAccess p_39776_) {
        super(p_39773_, p_39774_, p_39775_, p_39776_);
    }

    @ModifyReturnValue(
            at = {@At("TAIL")},
            method = {"mayPickup"}
    )
    private boolean extendedWorkbench$mayPickup(boolean original) {
        Item item = this.resultSlots.getItem(0).getItem();
        return original || ModList.get().isLoaded("enderitemod") && (item instanceof ExtendedEnderiteSwordItem || item instanceof ExtendedEnderiteShieldItem);
    }

    @Inject(
            at = @At("TAIL"),
            method = "createResult"
    )
    private void extendedWorkbench$createResult(CallbackInfo info) {
        if (!ModList.get().isLoaded("enderitemod")) return;
        ItemStack itemStack = this.inputSlots.getItem(1);
        ItemStack pearls1 = this.inputSlots.getItem(0);
        ItemStack pearls2 = this.inputSlots.getItem(2);
        Item item = itemStack.getItem();
        if ((item instanceof ExtendedEnderiteSwordItem || item instanceof ExtendedEnderiteShieldItem) && (pearls1.is(Items.ENDER_PEARL) || pearls1.isEmpty()) && pearls2.is(Items.ENDER_PEARL)) {
            int teleport_charge = pearls1.getCount() + pearls2.getCount();
            if (itemStack.getTag().contains("teleport_charge")) {
                teleport_charge += itemStack.getTag().getInt("teleport_charge");
            }

            if (teleport_charge > 64) {
                teleport_charge = 64;
            }

            ItemStack newSword = itemStack.copy();
            newSword.getTag().putInt("teleport_charge", teleport_charge);
            this.resultSlots.setItem(0, newSword);
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "onTake"
    )
    private void extendedWorkbench$onTakeOutput(Player player, ItemStack ignored, CallbackInfo info) {
        if (!ModList.get().isLoaded("enderitemod")) return;
        ItemStack itemStack = this.inputSlots.getItem(1);
        ItemStack pearls1 = this.inputSlots.getItem(0);
        ItemStack pearls2 = this.inputSlots.getItem(2);
        Item item = itemStack.getItem();
        if ((item instanceof ExtendedEnderiteSwordItem || item instanceof ExtendedEnderiteShieldItem) && (pearls1.is(Items.ENDER_PEARL) || pearls1.isEmpty()) && pearls2.is(Items.ENDER_PEARL)) {
            int amountToSubstract = pearls1.getCount() + pearls2.getCount();
            int subtract1;
            if (itemStack.getTag().contains("teleport_charge")) {
                subtract1 = 64 - itemStack.getTag().getInt("teleport_charge");
                amountToSubstract = Math.min(subtract1, amountToSubstract);
            }

            subtract1 = Math.min(amountToSubstract, pearls1.getCount());
            this.ew$superDecrement(0, subtract1 - 1);
            this.ew$superDecrement(2, amountToSubstract - subtract1 - 1);
        }
    }

    @Unique
    private void ew$superDecrement(int i, int amount) {
        ItemStack itemStack = this.inputSlots.getItem(i);
        if (!itemStack.isEmpty()) {
            itemStack.shrink(amount);
            this.inputSlots.setItem(i, itemStack);
        }
    }
}
