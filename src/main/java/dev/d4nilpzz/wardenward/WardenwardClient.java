package dev.d4nilpzz.wardenward;

import dev.d4nilpzz.wardenward.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import top.theillusivec4.curios.api.CuriosApi;

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

            boolean hasAmulet = CuriosApi.getCuriosInventory(player)
                    .map(inv -> inv.findFirstCurio(ModItems.WARDEN_PULSE_BOOTS.get()).isPresent())
                    .orElse(false);

            if (player != null && hasAmulet && player.onGround()) {
                if (player.experienceLevel < 1) return;

                player.giveExperienceLevels(-1);

                player.setDeltaMovement(
                        player.getDeltaMovement().x,
                        1.2,
                        player.getDeltaMovement().z
                );

                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        100,
                        20,
                        false,
                        false
                ));

                player.fallDistance = 0;
            }
        }
    }
}
