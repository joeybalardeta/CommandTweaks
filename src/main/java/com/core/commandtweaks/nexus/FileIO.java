package com.core.commandtweaks.nexus;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.player.Faction;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.player.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
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

        // create/open player count file
        Nexus.playerCount = new File(instance.getDataFolder(), "playerCount.yml");
        if (!Nexus.playerCount.exists()) {
            Nexus.playerCount.getParentFile().mkdirs();
            try {
                Nexus.playerCount.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.playerCountConfig = YamlConfiguration.loadConfiguration(Nexus.playerCount);

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

        // create/open vanguard (anti-cheat) data file
        Nexus.vanguardData = new File(instance.getDataFolder(), "vanguardData.yml");
        if (!Nexus.vanguardData.exists()) {
            Nexus.vanguardData.getParentFile().mkdirs();
            try {
                Nexus.vanguardData.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.vanguardDataConfig = YamlConfiguration.loadConfiguration(Nexus.vanguardData);

        // create/open faction data file
        Nexus.modLog = new File(instance.getDataFolder(), "modLog.yml");
        if (!Nexus.modLog.exists()) {
            Nexus.modLog.getParentFile().mkdirs();
            try {
                Nexus.modLog.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.modLogConfig = YamlConfiguration.loadConfiguration(Nexus.modLog);

        Nexus.pluginFiles.put(Nexus.settings, Nexus.settingsConfig);
        Nexus.pluginFiles.put(Nexus.playerCount, Nexus.playerCountConfig);
        Nexus.pluginFiles.put(Nexus.playerData, Nexus.playerDataConfig);
        Nexus.pluginFiles.put(Nexus.factionData, Nexus.factionDataConfig);
        Nexus.pluginFiles.put(Nexus.vanguardData, Nexus.vanguardDataConfig);
        Nexus.pluginFiles.put(Nexus.modLog, Nexus.modLogConfig);
    }

    // save all plugin data files
    public void save(){
        for (Map.Entry<File, FileConfiguration> entry : Nexus.pluginFiles.entrySet()){
            try {
                entry.getValue().save(entry.getKey());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // CommandTweaks.getInstance().getLogger().log(Level.INFO, "Nexus | Writing data to storage.");
    }



    public void writePlayerPlus(PlayerPlus playerPlus){
        Nexus.playerDataConfig.set("users." + playerPlus.getPlayer().getUniqueId() + ".rank", playerPlus.getRank().toStringNoColor());
        Nexus.playerDataConfig.set("users." + playerPlus.getPlayer().getUniqueId() + ".name", playerPlus.getPlayer().getDisplayName());
    }

    public void loadPlayerPlus(Player p){
        PlayerPlus playerPlus = new PlayerPlus(p);

        Rank rank = new Rank(Nexus.playerDataConfig.getString("users." + p.getUniqueId() + ".rank"));

        playerPlus.setRank(rank);

        Faction faction = null;

        playerPlus.setFaction(faction);
    }

    public void logPlayerCount(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm z");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = new Date(System.currentTimeMillis());
        Nexus.playerCountConfig.set("player-count." + formatter.format(date), Bukkit.getOnlinePlayers().size());
    }

    public void logCommand(String playerName, String command){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = new Date(System.currentTimeMillis());
        Nexus.modLogConfig.set("mod-log." + formatter.format(date), playerName + " issued server command: " + command);
    }

    public void scheduleFileTasks(){
        BukkitScheduler scheduler = CommandTweaks.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run() {
                CommandTweaks.nexus.fileIO.save(); // save all plugin data files
            }
        }, 0L, 1200L);

        scheduler.scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run() {
                CommandTweaks.nexus.fileIO.logPlayerCount();
            }
        }, 0L, 12000L);
    }
}