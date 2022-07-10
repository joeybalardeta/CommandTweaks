package com.core.commandtweaks.vanillaplus.particles.particleeffects;

import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.vanillaplus.particles.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;

public class FlameEffect extends ParticleEffect {
    public FlameEffect(PlayerPlus playerPlus) {
        super(playerPlus);
        this.start(1);
    }

    @Override
    public void effect(){
        for (int i = 0; i < 1; i++){
            Random rand = new Random();
            double lookDir = this.getPlayerPlus().getPlayerDirectionFloat();
            lookDir = ((lookDir * Math.PI) / 180) + (5 * Math.PI / 6);
            int var = rand.nextInt(240);
            double rotation = ((var * Math.PI) / 180.0) - Math.PI;
            int distanceTemp = rand.nextInt(100) + 50;
            double distance = distanceTemp / 100.0;
            int heightTemp = rand.nextInt(200) + 20;
            double height = heightTemp / 100.0;


            Location loc = this.getPlayerPlus().getPlayer().getLocation();
            Location first = loc.clone().add(Math.cos(rotation + lookDir) * distance, height, Math.sin(rotation + lookDir) * distance);

            this.getPlayerPlus().getPlayer().getWorld().spawnParticle(Particle.FLAME, first, 0);
        }
    }
}
