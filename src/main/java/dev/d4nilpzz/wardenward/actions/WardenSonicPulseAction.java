package dev.d4nilpzz.wardenward.actions;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.List;

public class WardenSonicPulseAction {

    public static void execute(Player player) {
        if (player.experienceLevel > 1) {
            player.experienceLevel--;

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
                        e.hurt(player.damageSources().playerAttack(player), 15.0F);
                    }
                }
            }
        }
    }
}