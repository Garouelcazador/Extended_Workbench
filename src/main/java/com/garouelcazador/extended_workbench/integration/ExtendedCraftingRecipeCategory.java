package com.garouelcazador.extended_workbench.integration;

import com.garouelcazador.extended_workbench.items.EWItems;
import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.garouelcazador.extended_workbench.screens.inventory.ExtendedCraftingScreen;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraftforge.common.crafting.IShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class ExtendedCraftingRecipeCategory implements IRecipeCategory<CraftingRecipe>  /*IExtendableRecipeCategory<ExtendedShapedRecipe, ICraftingCategoryExtension>*/ {
    private final IDrawable background;
    private final IDrawable icon;

    public ExtendedCraftingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ExtendedCraftingScreen.CRAFTING_TABLE_LOCATION, 29, 16, 116, 108);
        this.icon = helper.createDrawableItemStack(new ItemStack(EWItems.EXTENDED_NETHERITE_SWORD.get()));
    }

    @Override
    public @NotNull RecipeType<CraftingRecipe> getRecipeType() {
        return JeiEWPlugin.EXTENDED_SHAPED_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return EWHelper.CONTAINER_TITLE;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull CraftingRecipe recipe, @NotNull IFocusGroup focuses) {
        if (!(recipe instanceof IShapedRecipe<?> shapedRecipe)) return;
        int width = shapedRecipe.getRecipeWidth();
        int height = shapedRecipe.getRecipeHeight();
        int index = 0;
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        for(int i = 0; i < 6; ++i) {
            for(int j = 0; j < 3; ++j) {
                int x = (30 + j * 18) - 29;
                int y = (-10 + i * 18) + 11;
                IRecipeSlotBuilder recipeSlot = builder.addSlot(RecipeIngredientRole.INPUT, x, y);
                boolean flag = j == 1 && i + 1 <= height;
                boolean flag2 = width > 1 && j + 1 <= width && i + 1 <= height;
                if (index >= ingredients.size()) continue;
                if (flag || flag2)
                    recipeSlot.addIngredients(ingredients.get(index++));
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 47).addItemStack(RecipeUtil.getResultItem(recipe));
    }
}
