package com.garouelcazador.extended_workbench.screens.inventory;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ExtendedCraftingMenu extends RecipeBookMenu<CraftingContainer> {
    private final TransientCraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 6);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public ExtendedCraftingMenu(int containerID, Inventory inventory) {
        this(containerID, inventory, ContainerLevelAccess.NULL);
    }

    public ExtendedCraftingMenu(int containerID, Inventory inventory, ContainerLevelAccess p_39358_) {
        super(EWMenuTypes.EXTENDED_WORKBENCH_MENU.get(), containerID);
        this.access = p_39358_;
        this.player = inventory.player;

        //Result Slot
        this.addSlot(new ResultSlot(inventory.player, this.craftSlots, this.resultSlots, 0, 124, 36));

        //Crafting Slots
        for(int i = 0; i < 6; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, -10 + i * 18));
            }
        }

        //Inventory Slots
        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 111 + k * 18));
            }
        }

        //Hotbar Slots
        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 169));
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu containerMenu, Level level, Player player, CraftingContainer craftingContainer, ResultContainer resultContainer) {
        if (level.isClientSide)
            return;

        ServerPlayer serverplayer = (ServerPlayer)player;
        ItemStack itemstack = ItemStack.EMPTY;
        if (level.getServer() != null) {
            Optional<CraftingRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingContainer, level);
            if (optional.isPresent()) {
                CraftingRecipe craftingrecipe = optional.get();
                if (resultContainer.setRecipeUsed(level, serverplayer, craftingrecipe)) {
                    ItemStack itemstack1 = craftingrecipe.assemble(craftingContainer, level.registryAccess());
                    if (itemstack1.isItemEnabled(level.enabledFeatures())) {
                        itemstack = itemstack1;
                    }
                }
            }
        }

        resultContainer.setItem(0, itemstack);
        containerMenu.setRemoteSlot(0, itemstack);
        serverplayer.connection.send(new ClientboundContainerSetSlotPacket(containerMenu.containerId, containerMenu.incrementStateId(), 0, itemstack));
    }

    public void slotsChanged(@NotNull Container container) {
        this.access.execute((p_39386_, p_39387_) -> slotChangedCraftingGrid(this, p_39386_, this.player, this.craftSlots, this.resultSlots));
    }

    public void fillCraftSlotsStackedContents(@NotNull StackedContents stackedContents) {
        this.craftSlots.fillStackedContents(stackedContents);
    }

    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultSlots.clearContent();
    }

    public boolean recipeMatches(Recipe<? super CraftingContainer> p_39384_) {
        return p_39384_.matches(this.craftSlots, this.player.level());
    }

    public void removed(@NotNull Player player) {
        super.removed(player);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(player, this.craftSlots);
        });
    }

    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player);
    }

    protected static boolean stillValid(ContainerLevelAccess levelAccess, @NotNull Player player) {
        return levelAccess.evaluate((p_38916_, p_38917_) -> p_38916_.getBlockState(p_38917_).getBlock() instanceof CraftingTableBlock && player.distanceToSqr((double) p_38917_.getX() + 0.5D, (double) p_38917_.getY() + 0.5D, (double) p_38917_.getZ() + 0.5D) <= 64.0D, true);
    }

    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                this.access.execute((p_39378_, p_39379_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, player);
                });
                if (!this.moveItemStackTo(itemstack1, 19, 55, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 19 && index < 55) {
                if (!this.moveItemStackTo(itemstack1, 1, 19, false)) {
                    if (index < 46) {
                        if (!this.moveItemStackTo(itemstack1, 46, 55, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 19, 46, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 19, 55, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    public boolean canTakeItemForPickAll(@NotNull ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    public int getSize() {
        return 19;
    }

    public @NotNull RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    public boolean shouldMoveToInventory(int p_150553_) {
        return p_150553_ != this.getResultSlotIndex();
    }
}