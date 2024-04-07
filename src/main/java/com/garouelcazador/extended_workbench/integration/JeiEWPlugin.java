package com.garouelcazador.extended_workbench.integration;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JeiPlugin
public class JeiEWPlugin implements IModPlugin {
    public static final RecipeType<CraftingRecipe> EXTENDED_SHAPED_RECIPE_TYPE = RecipeType.create(ExtendedWorkbench.MOD_ID, "extended_crafting", CraftingRecipe.class); //new RecipeType<>(new ResourceLocation(ExtendedWorkbench.MOD_ID, "extended_crafting_shaped"), CraftingRecipe.class);

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(ExtendedWorkbench.MOD_ID, "recipes");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ExtendedCraftingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        ClientLevel lvl = Minecraft.getInstance().level;
        if (lvl == null) return;
        RecipeManager rm = lvl.getRecipeManager();
        List<CraftingRecipe> recipes = new ArrayList<>();
        for (CraftingRecipe recipe : rm.getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.CRAFTING))
            if (recipe instanceof IShapedRecipe<?> shapedRecipe && (shapedRecipe.getRecipeWidth() <= 3 && shapedRecipe.getRecipeHeight() > 3 && shapedRecipe.getRecipeHeight() <= 6 || Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(RecipeUtil.getResultItem(shapedRecipe).getItem())).getNamespace().equals(ExtendedWorkbench.MOD_ID)))
                recipes.add(recipe);

        registration.addRecipes(EXTENDED_SHAPED_RECIPE_TYPE, recipes);
    }
}
