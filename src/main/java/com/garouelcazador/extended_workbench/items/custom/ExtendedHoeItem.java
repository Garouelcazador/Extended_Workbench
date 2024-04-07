package com.garouelcazador.extended_workbench.items.custom;

import com.garouelcazador.extended_workbench.misc.EWHelper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public class ExtendedHoeItem extends HoeItem {
    private final float toolRange;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ExtendedHoeItem(Tier tier, int attackDamage, float attackSpeed, float toolRange, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.toolRange = toolRange;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
        if (this.toolRange > 0.0F) builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(EWHelper.BASE_BLOCK_REACH_UUID, "Tool modifier", this.toolRange, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public float getToolRange() {
        return this.toolRange;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : ImmutableMultimap.of();
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        BlockPos blockpos = useOnContext.getClickedPos();
        BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(useOnContext, net.minecraftforge.common.ToolActions.HOE_TILL, false);
        Pair<Predicate<UseOnContext>, Function<UseOnContext, Integer>> pair = toolModifiedState == null ? null : Pair.of(ctx -> true, EWchangeIntoState(toolModifiedState));
        if (pair == null) {
            return InteractionResult.PASS;
        } else {
            Predicate<UseOnContext> predicate = pair.getFirst();
            Function<UseOnContext, Integer> consumer = pair.getSecond();
            if (predicate.test(useOnContext)) {
                Player player = useOnContext.getPlayer();
                level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide && player != null) {
                    useOnContext.getItemInHand().hurtAndBreak(consumer.apply(useOnContext), player, (p_150845_) -> {
                        p_150845_.broadcastBreakEvent(useOnContext.getHand());
                    });
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    public static Function<UseOnContext, Integer> EWchangeIntoState(@NotNull BlockState blockState) {
        return (useOnContext) -> {
            Level level = useOnContext.getLevel();
            ItemStack handItem = useOnContext.getItemInHand();
            if (handItem.isEmpty()) return 0;
            BlockPos blockPos = useOnContext.getClickedPos();
            Player player = useOnContext.getPlayer();
            int maxDamage = handItem.getMaxDamage();
            int originalDurability = maxDamage - handItem.getDamageValue();
            int durability = originalDurability;
            level.setBlock(blockPos, blockState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState));
            --durability;

            if (durability > 0) for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && z == 0) continue;
                    BlockPos actualBlock = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                    if (level.getBlockState(actualBlock).getToolModifiedState(useOnContext, net.minecraftforge.common.ToolActions.HOE_TILL, false) != null) {
                        level.setBlock(actualBlock, blockState, 11);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, actualBlock, GameEvent.Context.of(player, blockState));
                        --durability;
                    }
                    if (durability == 0) break;
                }
                if (durability == 0) break;
            }
            return originalDurability - durability;
        };
    }
}
