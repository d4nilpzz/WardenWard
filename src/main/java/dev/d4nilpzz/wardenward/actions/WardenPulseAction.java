package dev.d4nilpzz.wardenward.actions;

import dev.d4nilpzz.wardenward.registry.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class WardenPulseAction {

    public static void execute(Player player) {
        boolean hasAmulet = CuriosApi.getCuriosInventory(player)
                .map(inv -> inv.findFirstCurio(ModItems.WARDEN_PULSE_NECKLACE.get()).isPresent())
                .orElse(false);

        if (hasAmulet && player.experienceLevel > 1) {
            player.experienceLevel = player.experienceLevel - 1;

            Vec3 look = player.getLookAngle();
            Vec3 eyePos = player.getEyePosition();

            double range = 20.0;

            for (double i = 0; i < range; i += 0.5) {
                Vec3 pos = eyePos.add(look.scale(i));

                player.level().addParticle(
                        ParticleTypes.SONIC_BOOM,
                        pos.x, pos.y, pos.z,
                        0, 0, 0
                );

                List<Entity> entities = player.level().getEntities(
                        player,
                        new AABB(pos, pos).inflate(2)
                );

                for (Entity e : entities) {
                    if (e != player) {
                        e.hurt(player.damageSources().generic(), 10.0F);
                        e.lavaHurt();
                    }
                }
            }
        }
    }
}
