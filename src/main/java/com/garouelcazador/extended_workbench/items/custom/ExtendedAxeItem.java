package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

public class ExtendedAxeItem extends AxeItem {
    private final float toolRange;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ExtendedAxeItem(Tier tier, float attackDamage, float attackSpeed, float toolRange, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.toolRange = toolRange;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
        if (this.toolRange > 0.0F) {
            builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(EWHelper.BASE_BLOCK_REACH_UUID, "Tool modifier", this.toolRange, AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(EWHelper.BASE_ENTITY_REACH_UUID, "Tool modifier", this.toolRange, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
    }

    public float getToolRange() {
        return this.toolRange;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : ImmutableMultimap.of();
    }
}
