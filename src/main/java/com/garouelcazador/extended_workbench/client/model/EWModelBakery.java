package com.garouelcazador.extended_workbench.client.model;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class EWModelBakery {
    public static final Material EXTENDED_SHIELD_BASE = new Material(Sheets.BANNER_SHEET, new ResourceLocation(ExtendedWorkbench.MOD_ID, "entity/extended_banner_base"));
    public static final Material NO_PATTERN_EXTENDED_SHIELD = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(ExtendedWorkbench.MOD_ID, "entity/extended_shield_base_nopattern"));

    public static final Material EXTENDED_ENDERITE_SHIELD_BASE = new Material(Sheets.BANNER_SHEET, new ResourceLocation(ExtendedWorkbench.MOD_ID, "entity/extended_enderite_banner_base"));
    public static final Material NO_PATTERN_EXTENDED_ENDERITE_SHIELD = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(ExtendedWorkbench.MOD_ID, "entity/extended_enderite_shield_base_nopattern"));

    private EWModelBakery(){}
}
