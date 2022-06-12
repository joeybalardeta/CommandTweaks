package com.core.commandtweaks;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommandTweaks extends JavaPlugin {

    public static String VERSION = "0.1";
    public static String PLUGINNAME = "CommandTweaks";

    private static CommandTweaks instance;

    public static CommandTweaks getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
