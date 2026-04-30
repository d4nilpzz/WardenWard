package dev.d4nilpzz.wardenward.items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WardenPulseBootsItem extends BaseCuriosItem {

    public WardenPulseBootsItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.wardenward.warden_pulse_boots"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.wardenward.default"));
        }
    }
}
