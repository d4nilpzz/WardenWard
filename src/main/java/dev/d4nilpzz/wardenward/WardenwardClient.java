package dev.d4nilpzz.wardenward;

import dev.d4nilpzz.wardenward.actions.WardenPulseAction;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = Wardenward.MODID, value = Dist.CLIENT)
public class WardenwardClient {

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(WardenwardKeyBinds.WARDEN_PULSE_ACTION);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (WardenwardKeyBinds.WARDEN_PULSE_ACTION.consumeClick()) {
            Player player = Minecraft.getInstance().player;

            if (player != null) {
                WardenPulseAction.execute(player);
            }
        }
    }
}
