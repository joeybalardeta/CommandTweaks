package com.core.commandtweaks;


import com.core.commandtweaks.command.CommandManager;
import com.core.commandtweaks.event.EventManager;
import com.core.commandtweaks.nexus.Nexus;
import com.core.commandtweaks.player.CustomNameTags;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class CommandTweaks extends JavaPlugin {
    public static String VERSION = "0.1";
    public static String PLUGINNAME = "CommandTweaks";
    private static CommandTweaks instance;

    public static EventManager eventManager;

    public static CommandManager commandManager;

    public static CustomNameTags customNameTags;

    public static Nexus nexus;

    public static CommandTweaks getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        eventManager = new EventManager();
        eventManager.init();

        commandManager = new CommandManager();
        commandManager.init();

        customNameTags = new CustomNameTags();
        customNameTags.init();

        nexus = new Nexus();
        nexus.init();

        for (Player online : Bukkit.getOnlinePlayers()) {
            nexus.fileIO.loadPlayerPlus(online);
        }

        getLogger().log(Level.INFO, PLUGINNAME + " v" + VERSION + " is online!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }
}