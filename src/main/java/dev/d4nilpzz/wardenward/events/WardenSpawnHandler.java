package dev.d4nilpzz.wardenward.events;

import dev.d4nilpzz.wardenward.Wardenward;
import dev.d4nilpzz.wardenward.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = Wardenward.MODID)
public class WardenSpawnHandler {

    @SubscribeEvent
    public static void onWardenSpawnCheck(FinalizeSpawnEvent event) {

        if (!(event.getEntity() instanceof Warden)) return;

        Level level = (Level) event.getLevel();
        if (level.isClientSide()) return;

        BlockPos wardenPos = event.getEntity().blockPosition();
        int checkRadius = 64;

        for (Player player : level.players()) {
            if (player.distanceToSqr(Vec3.atCenterOf(wardenPos)) > checkRadius * checkRadius)
                continue;

            boolean hasAmulet = CuriosApi.getCuriosInventory(player)
                    .map(inv -> inv.findFirstCurio(ModItems.WARDEN_WARD.get()).isPresent())
                    .orElse(false);

            if (hasAmulet) {
                event.setSpawnCancelled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onWardenJoin(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Warden warden)) return;

        Level level = event.getLevel();
        if (level.isClientSide()) return;

        BlockPos wardenPos = warden.blockPosition();
        int checkRadius = 64;

        for (Player player : level.players()) {
            if (player.distanceToSqr(Vec3.atCenterOf(wardenPos)) > checkRadius * checkRadius)
                continue;

            boolean hasAmulet = CuriosApi.getCuriosInventory(player)
                    .map(inv -> inv.findFirstCurio(ModItems.WARDEN_WARD.get()).isPresent())
                    .orElse(false);

            if (hasAmulet) {
                event.setCanceled(true);
                return;
            }
        }
    }
}