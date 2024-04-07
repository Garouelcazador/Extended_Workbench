package com.garouelcazador.extended_workbench.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class EWServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue EXTENDED_EQUIPMENT_PROBABILITY;
    public static final ForgeConfigSpec.DoubleValue NATIVE_EXTENDED_EQUIPMENT_PROBABILITY;

    static {
        BUILDER.push("Configs for Extended Workbench");



        /*MOBCAP_MULTIPLIER = BUILDER.comment("This will multiply the mobcap by this value")
                .define("Mobcap multiplier", 1.0F);

        DEATH_STORM_MOBCAP_MULTIPLIER = BUILDER.comment("During the deathstorm the mobcap will be multiplied by this value")
                .define("Mobcap multiplier", 2.0F);*/

        /*ENCHANTMENTS = BUILDER.comment("Activate enchantments?")
                .define("Activar encantamientos", false);*/

        /*EXTENDED_EQUIPMENT_PROBABILITY = BUILDER.comment("Chance of replacing mob equipment with its extended version, by default is 25%.")
                .define("ReplaceEquipmentChance", "0.25");

        NATIVE_EXTENDED_EQUIPMENT_PROBABILITY = BUILDER.comment("Chance of replacing equipment for mobs that always carry a weapon (skeletons, pillagers, vindicators, etc.), by default is 15%.")
                .define("ReplaceNativeEquipmentChance", "0.15");*/

        EXTENDED_EQUIPMENT_PROBABILITY = BUILDER.comment("Chance of replacing mob equipment with its extended version.")
                .defineInRange("replaceEquipmentChance", 0.2D, 0.0D, 1.0D);

        NATIVE_EXTENDED_EQUIPMENT_PROBABILITY = BUILDER.comment("Chance of replacing equipment for mobs that always carry a weapon (skeletons, pillagers, vindicators, etc).")
                .defineInRange("replaceNativeEquipmentChance", 0.1D, 0.0D, 1.0D);





        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private EWServerConfig(){}
}
