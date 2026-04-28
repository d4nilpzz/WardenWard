package dev.d4nilpzz.wardenward.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BaseCuriosItem extends Item implements ICurioItem {

    public BaseCuriosItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            boolean equipped = CuriosApi.getCuriosInventory(player).map(inv -> {
                var slots = inv.getCurios();
                for (var entry : slots.entrySet()) {
                    var handler = entry.getValue().getStacks();
                    for (int i = 0; i < handler.getSlots(); i++) {
                        if (handler.getStackInSlot(i).isEmpty()) {
                            handler.setStackInSlot(i, stack.copy());
                            return true;
                        }
                    }
                }
                return false;
            }).orElse(false);

            if (equipped && !player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return stack.isEmpty()
                ? InteractionResultHolder.success(stack)
                : InteractionResultHolder.pass(stack);
    }
}
