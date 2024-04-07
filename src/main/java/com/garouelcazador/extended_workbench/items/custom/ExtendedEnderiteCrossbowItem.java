package com.garouelcazador.extended_workbench.items.custom;

import net.enderitemc.enderitemod.tools.EnderiteCrossbow;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class ExtendedEnderiteCrossbowItem extends EnderiteCrossbow {

    public ExtendedEnderiteCrossbowItem(Properties settings) {
        super(settings);
    }

    /*@Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (isCharged(itemstack)) {
            performShooting(level, player, interactionHand, itemstack, getShootingPower(itemstack), 1.0F);
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!player.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
                player.startUsingItem(interactionHand);
            }

            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @Override
    public void releaseUsing(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entity, int useDuration) {
        int i = this.getUseDuration(itemStack) - useDuration;
        float f = getPowerForTime(i, itemStack);
        if (f >= 1.0F && !isCharged(itemStack) && tryLoadProjectiles(entity, itemStack)) {
            setCharged(itemStack, true);
            if (f >= 1.5F) ExtendedCrossbowItem.setExtendedCharge(itemStack, true);
            SoundSource soundsource = entity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }*/

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return (int) (super.getUseDuration(itemStack) * 18.0F / 11.0F);
    }
}
