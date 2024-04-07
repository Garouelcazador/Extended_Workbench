package com.garouelcazador.extended_workbench.misc;

import net.enderitemc.enderitemod.materials.EnderiteMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.fml.ModList;

import java.util.function.Supplier;

public final class EWTiers {
    public static final ForgeTier WOOD = upgradeTier(Tiers.WOOD);
    public static final ForgeTier STONE = upgradeTier(Tiers.STONE);
    public static final ForgeTier IRON = upgradeTier(Tiers.IRON);
    public static final ForgeTier DIAMOND = upgradeTier(Tiers.DIAMOND);
    public static final ForgeTier GOLD = upgradeTier(Tiers.GOLD);
    public static final ForgeTier NETHERITE = upgradeTier(Tiers.NETHERITE);
    private static final ForgeTier ENDERITE = ModList.get().isLoaded("enderitemod") ? upgradeTier(EnderiteMaterial.ENDERITE) : null;
    public static final Supplier<ForgeTier> ENDERITE_SUPPLIER = () -> EWTiers.ENDERITE;

    private EWTiers(){}

    private static ForgeTier upgradeTier(Tier tier) {
        return new ForgeTier(tier.getLevel(), Math.round(tier.getUses() * 1.25F), tier.getSpeed() + 0.5F, tier.getAttackDamageBonus(), tier.getEnchantmentValue(), tier.getTag(), () -> tier.getRepairIngredient());
    }
}
