package com.core.commandtweaks.nexus;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Nexus {
    // initialize plugin data files
    public static File settings;
    public static FileConfiguration settingsConfig;
    public static File playerData;
    public static FileConfiguration playerDataConfig;
    public static File factionData;
    public static FileConfiguration factionDataConfig;

    public FileIO fileIO;

    public void init(){
        fileIO = new FileIO();
        fileIO.openFiles();
        fileIO.scheduleFileTasks();
    }


}
