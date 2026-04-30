package dev.d4nilpzz.wardenward.items;

import dev.d4nilpzz.wardenward.actions.WardenSonicPulseAction;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WardenSonicPulseItem extends Item {
    private static final int COOLDOWN_TICKS = 100;

    public WardenSonicPulseItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
        }

        WardenSonicPulseAction.execute(player);

        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.wardenward.warden_sonic_pulse"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.wardenward.default"));
        }
    }
}