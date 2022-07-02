package com.core.commandtweaks.nexus;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.logging.Level;

public class Nexus {
    // initialize plugin data files
    public static File settings;
    public static FileConfiguration settingsConfig;
    public static File playerData;
    public static FileConfiguration playerDataConfig;
    public static File factionData;
    public static FileConfiguration factionDataConfig;
    public static File vanguardData;
    public static FileConfiguration vanguardDataConfig;

    public FileIO fileIO;

    public void init() {
        fileIO = new FileIO();
        fileIO.openFiles();
        fileIO.scheduleFileTasks();

        for (Player online : Bukkit.getOnlinePlayers()) {
            fileIO.loadPlayerPlus(online);
        }

        Utils.consoleLog(Level.INFO, "Nexus (Data Storage/Management) online.");
    }


}
