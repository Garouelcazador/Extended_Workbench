package com.garouelcazador.extended_workbench.client;

import com.garouelcazador.extended_workbench.client.model.EWModelBakery;
import com.garouelcazador.extended_workbench.client.model.ExtendedShieldModel;
import com.garouelcazador.extended_workbench.client.model.geom.EWModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExtendedShieldRenderer extends BlockEntityWithoutLevelRenderer {
    public static final ExtendedShieldRenderer INSTANCE = new ExtendedShieldRenderer();
    protected ExtendedShieldModel extendedShieldModel;

    public ExtendedShieldRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.extendedShieldModel = new ExtendedShieldModel(this.entityModelSet.bakeLayer(EWModelLayers.EXTENDED_SHIELD));
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        this.extendedShieldModel = new ExtendedShieldModel(this.entityModelSet.bakeLayer(EWModelLayers.EXTENDED_SHIELD));
    }

    @Override
    public void renderByItem(@NotNull ItemStack itemStack, @NotNull ItemDisplayContext itemDisplayContext, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int light, int overlay) {
        boolean flag = BlockItem.getBlockEntityData(itemStack) != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? EWModelBakery.EXTENDED_SHIELD_BASE : EWModelBakery.NO_PATTERN_EXTENDED_SHIELD;
        VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(bufferSource, this.extendedShieldModel.renderType(material.atlasLocation()), true, itemStack.hasFoil()));
        this.extendedShieldModel.handle().render(poseStack, vertexconsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        if (flag) {
            List<Pair<Holder<BannerPattern>, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(itemStack), BannerBlockEntity.getItemPatterns(itemStack));
            BannerRenderer.renderPatterns(poseStack, bufferSource, light, overlay, this.extendedShieldModel.plate(), material, false, list, itemStack.hasFoil());
        } else {
            this.extendedShieldModel.plate().render(poseStack, vertexconsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        poseStack.popPose();
    }
}
