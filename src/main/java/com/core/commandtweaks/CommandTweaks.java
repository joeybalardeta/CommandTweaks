package com.core.commandtweaks;


import com.core.commandtweaks.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class CommandTweaks extends JavaPlugin {
    public static String VERSION = "0.1";
    public static String PLUGINNAME = "CommandTweaks";
    private static CommandTweaks instance;

    public EventManager eventManager;

    public static CommandTweaks getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        eventManager = new EventManager();
        eventManager.init();

        getLogger().log(Level.INFO, PLUGINNAME + " v" + VERSION + " is online!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }
}