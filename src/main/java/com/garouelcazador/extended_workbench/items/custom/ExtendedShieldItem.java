package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.client.ExtendedShieldRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ExtendedShieldItem extends ShieldItem {
    public ExtendedShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ExtendedShieldRenderer.INSTANCE;
            }
        });
    }
}
