package com.core.commandtweaks.nexus;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.player.Faction;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.player.Rank;
import jdk.jfr.internal.LogLevel;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FileIO {
    public CommandTweaks instance = CommandTweaks.getInstance();
    public void openFiles() {
        // create/open plugin settings file
        Nexus.settings = new File(instance.getDataFolder(), "settings.yml");
        if (!Nexus.settings.exists()) {
            Nexus.settings.getParentFile().mkdirs();
            try {
                Nexus.settings.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.settingsConfig = YamlConfiguration.loadConfiguration(Nexus.settings);

        // create/open player data file
        Nexus.playerData = new File(instance.getDataFolder(), "playerData.yml");
        if (!Nexus.playerData.exists()) {
            Nexus.playerData.getParentFile().mkdirs();
            try {
                Nexus.playerData.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.playerDataConfig = YamlConfiguration.loadConfiguration(Nexus.playerData);

        // create/open faction data file
        Nexus.factionData = new File(instance.getDataFolder(), "factionData.yml");
        if (!Nexus.factionData.exists()) {
            Nexus.factionData.getParentFile().mkdirs();
            try {
                Nexus.factionData.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.factionDataConfig = YamlConfiguration.loadConfiguration(Nexus.factionData);
    }

    // save all plugin data files
    public void save(){
        try {
            Nexus.settingsConfig.save(Nexus.settings);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Nexus.playerDataConfig.save(Nexus.playerData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Nexus.factionDataConfig.save(Nexus.factionData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CommandTweaks.getInstance().getLogger().log(Level.INFO, "Nexus | Writing data to storage.");
    }

    // save all plugin data files
    public static void saveStatic(){
        try {
            Nexus.settingsConfig.save(Nexus.settings);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Nexus.playerDataConfig.save(Nexus.playerData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Nexus.factionDataConfig.save(Nexus.factionData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CommandTweaks.getInstance().getLogger().log(Level.INFO, "Nexus | Writing data to storage.");
    }



    public void writePlayerPlus(PlayerPlus playerPlus){
        Nexus.playerDataConfig.set("users." + playerPlus.getPlayer().getUniqueId() + ".rank", playerPlus.getRank().toStringNoColor());
    }

    public void loadPlayerPlus(Player p){
        PlayerPlus playerPlus = new PlayerPlus(p);

        Rank rank = new Rank(Nexus.playerDataConfig.getString("users." + p.getUniqueId() + ".rank"));

        playerPlus.setRank(rank);

        Faction faction = null;

        playerPlus.setFaction(faction);
    }

    public void scheduleFileTasks(){
        BukkitScheduler scheduler = CommandTweaks.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run() {
                FileIO.saveStatic(); // save all plugin data files
            }
        }, 0L, 1200L);
    }
}