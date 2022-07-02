package com.core.commandtweaks.vanguard;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.nexus.FileIO;
import com.core.commandtweaks.nexus.Nexus;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.UUID;
import java.util.logging.Level;

public class Vanguard {
    public void init() {
        scheduleEvaluationTasks();
        Utils.consoleLog(Level.INFO, "Vanguard (Anti-Cheat) online.");
    }

    public void scheduleEvaluationTasks() {
        BukkitScheduler scheduler = CommandTweaks.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run() {
                evaluateOnlinePlayers(); // save all plugin data files
            }
        }, 0L, 1200L);
    }

    public void evaluateOnlinePlayers() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            isLegitPlayer(online.getUniqueId());
        }
    }

    public boolean isLegitPlayer(UUID uuid) {
        if (!OreRatio.isLegitOreRatio(uuid) && !isFlagged(uuid)){
            flagPlayer(uuid, "Non-legit ore ratio");
            Utils.consoleLog(Level.INFO, "Potential cheater identified with uuid: " + uuid.toString());

            return false;
        }

        return true;
    }


    public void flagPlayer(UUID uuid, String reason) {
        if (Bukkit.getPlayer(uuid) != null){
            Nexus.vanguardDataConfig.set("potential-cheaters." + uuid.toString() + ".name", Bukkit.getPlayer(uuid).getDisplayName());
        }
        Nexus.vanguardDataConfig.set("potential-cheaters." + uuid.toString() + ".reason", reason);
    }

    public boolean isFlagged(UUID uuid) {
        if (Nexus.vanguardDataConfig.getConfigurationSection("potential-cheaters").contains(uuid.toString())){
            return true;
        }
        return false;
    }

    public void getFlaggedPlayers() {

    }
}