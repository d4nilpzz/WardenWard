package dev.d4nilpzz.wardenward.events;

import dev.d4nilpzz.wardenward.Wardenward;
import dev.d4nilpzz.wardenward.registry.ModItems;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = Wardenward.MODID)
public class WardenXpNecklaceHandler {

    @SubscribeEvent
    public static void onPlayerPickUpExperience(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();

        boolean hasAmulet = CuriosApi.getCuriosInventory(player)
                .map(inv -> inv.findFirstCurio(ModItems.WARDEN_XP_NECKLACE.get()).isPresent())
                .orElse(false);

        if (hasAmulet) {
            int originalXp = event.getOrb().getValue();
            float absorption = player.getAbsorptionAmount();
            int add = (int)(originalXp * (originalXp * 0.1f));
            event.getOrb().value = originalXp + add;
        }
    }
}
