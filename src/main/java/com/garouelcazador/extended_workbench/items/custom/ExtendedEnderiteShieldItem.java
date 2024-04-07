package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.client.ExtendedEnderiteShieldRenderer;
import net.enderitemc.enderitemod.forge.item.EnderiteShieldForge;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ExtendedEnderiteShieldItem extends EnderiteShieldForge {
    public ExtendedEnderiteShieldItem(Properties settings) {
        super(settings);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ExtendedEnderiteShieldRenderer.INSTANCE;
            }
        });
    }
}
