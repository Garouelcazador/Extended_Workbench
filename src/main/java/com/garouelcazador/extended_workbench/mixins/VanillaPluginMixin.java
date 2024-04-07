package com.garouelcazador.extended_workbench.mixins;

import mezz.jei.library.plugins.vanilla.VanillaPlugin;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraftforge.common.crafting.IShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = VanillaPlugin.class, remap = false)
public final class VanillaPluginMixin {

    private VanillaPluginMixin(){}

    @ModifyArg(
            method = "registerRecipes",
            at = @At(
                    value = "INVOKE",
                    ordinal = 0,
                    target = "Lmezz/jei/api/registration/IRecipeRegistration;addRecipes(Lmezz/jei/api/recipe/RecipeType;Ljava/util/List;)V"
            ),
            index = 1
    )
    private List<CraftingRecipe> extendedWorkbench$registerRecipes(List<CraftingRecipe> arg) {
        try {
            List<CraftingRecipe> list = new ArrayList<>(arg);
            list.removeIf(recipe -> recipe instanceof IShapedRecipe<?> shapedRecipe && shapedRecipe.getRecipeHeight() > 3);
            return list;
        } catch (Exception ignored) {
            return arg;
        }
    }
}
