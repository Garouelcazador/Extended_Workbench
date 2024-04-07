package com.garouelcazador.extended_workbench.client.model.geom;

import com.garouelcazador.extended_workbench.ExtendedWorkbench;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class EWModelLayers {

    private EWModelLayers(){}

    public static final ModelLayerLocation EXTENDED_SHIELD = new ModelLayerLocation(new ResourceLocation(ExtendedWorkbench.MOD_ID, "extended_shield"), "main");
}
