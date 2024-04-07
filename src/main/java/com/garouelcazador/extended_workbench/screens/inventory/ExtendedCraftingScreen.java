package com.garouelcazador.extended_workbench.screens.inventory;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ExtendedCraftingScreen extends AbstractContainerScreen<ExtendedCraftingMenu> implements RecipeUpdateListener {
    public static final ResourceLocation CRAFTING_TABLE_LOCATION = new ResourceLocation(ExtendedWorkbench.MOD_ID, "textures/gui/container/extended_crafting_table.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;
    protected int imageWidth = 176;
    protected int imageHeight = 220;

    public ExtendedCraftingScreen(ExtendedCraftingMenu extendedCraftingMenu, Inventory inventory, Component component) {
        super(extendedCraftingMenu, inventory, component);
    }

    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        assert this.minecraft != null;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (p_274679_) -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            p_274679_.setPosition(this.leftPos + 5, this.height / 2 - 49);
        }));
        this.addWidget(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
        this.titleLabelX = 8;
        this.titleLabelY = -21;
        this.inventoryLabelY = this.imageHeight - 121;
    }

    public void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(graphics, delta, mouseX, mouseY);
            this.recipeBookComponent.render(graphics, mouseX, mouseY, delta);
        } else {
            this.recipeBookComponent.render(graphics, mouseX, mouseY, delta);
            super.render(graphics, mouseX, mouseY, delta);
            this.recipeBookComponent.renderGhostRecipe(graphics, this.leftPos, this.topPos, true, delta);
        }

        this.renderTooltip(graphics, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(graphics, this.leftPos, this.topPos, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics graphics, float p_282132_, int p_283078_, int p_283647_) {
        RenderSystem.setShaderTexture(0, CRAFTING_TABLE_LOCATION);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        graphics.blit(CRAFTING_TABLE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected boolean isHovering(int p_98462_, int p_98463_, int p_98464_, int p_98465_, double p_98466_, double p_98467_) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(p_98462_, p_98463_, p_98464_, p_98465_, p_98466_, p_98467_);
    }

    public boolean mouseClicked(double p_98452_, double p_98453_, int p_98454_) {
        if (this.recipeBookComponent.mouseClicked(p_98452_, p_98453_, p_98454_)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(p_98452_, p_98453_, p_98454_);
        }
    }

    protected boolean hasClickedOutside(double p_98456_, double p_98457_, int p_98458_, int p_98459_, int p_98460_) {
        boolean flag = p_98456_ < (double)p_98458_ || p_98457_ < (double)p_98459_ || p_98456_ >= (double)(p_98458_ + this.imageWidth) || p_98457_ >= (double)(p_98459_ + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(p_98456_, p_98457_, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, p_98460_) && flag;
    }

    protected void slotClicked(@NotNull Slot slot, int p_98470_, int p_98471_, @NotNull ClickType p_98472_) {
        super.slotClicked(slot, p_98470_, p_98471_, p_98472_);
        this.recipeBookComponent.slotClicked(slot);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}
