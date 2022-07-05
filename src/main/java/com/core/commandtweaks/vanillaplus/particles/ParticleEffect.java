package com.core.commandtweaks.vanillaplus.particles;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.player.PlayerPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParticleEffect {
    private int taskID;

    private PlayerPlus playerPlus;

    private Location location;

    public ParticleEffect(PlayerPlus playerPlus) {
        this.playerPlus = playerPlus;
    }

    public ParticleEffect(Location location) {
        this.location = location;
    }

    public PlayerPlus getPlayerPlus(){
        return playerPlus;
    }

    public void end() {

        Bukkit.getScheduler().cancelTask(taskID);
    }

    public void start(int delay) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run(){
                effect();
            }
        }, 0, delay);
    }

    public void effect() {

    }
}
