package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.enderitemc.enderitemod.tools.EnderiteSword;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class ExtendedEnderiteSwordItem extends EnderiteSword {
    private final float attackRange;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ExtendedEnderiteSwordItem(Tier tier, int attackDamage, float attackSpeed, float attackRange, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.attackRange = attackRange;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
        if (this.attackRange > 0.0F) builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(EWHelper.BASE_ENTITY_REACH_UUID, "Weapon modifier", this.attackRange, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public float getAttackRange() {
        return this.attackRange;
    }

    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return target.getBoundingBox().inflate(1.25D, 0.25D, 1.25D);
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : ImmutableMultimap.of();
    }
}
