package com.core.commandtweaks;


import com.core.commandtweaks.command.CommandManager;
import com.core.commandtweaks.event.EventManager;
import com.core.commandtweaks.nexus.Nexus;
import com.core.commandtweaks.player.CustomNameTags;
import com.core.commandtweaks.player.RankManager;
import com.core.commandtweaks.vanguard.Vanguard;
import com.core.commandtweaks.vanillaplus.singleplayersleep.SinglePlayerSleep;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class CommandTweaks extends JavaPlugin {
    public static String VERSION = "0.4.0";
    public static String PLUGINNAME = "CommandTweaks";
    private static CommandTweaks instance;

    public static EventManager eventManager;

    public static CommandManager commandManager;

    public static RankManager rankManager;

    public static CustomNameTags customNameTags;

    public static Nexus nexus;

    public static Vanguard vanguard;

    public static SinglePlayerSleep singlePlayerSleep;

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

        rankManager = new RankManager();
        rankManager.init();

        customNameTags = new CustomNameTags();
        customNameTags.init();

        nexus = new Nexus();
        nexus.init();

        vanguard = new Vanguard();
        vanguard.init();

        singlePlayerSleep = new SinglePlayerSleep();
        singlePlayerSleep.init();

        /*
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run(){
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (online.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                        online.setHealth(0.0);
                    }
                }
            }
        }, 0, 10L);
         */


        getLogger().log(Level.INFO, PLUGINNAME + " v" + VERSION + " is online!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CommandTweaks.nexus.fileIO.saveNoTask();
        instance = null;
    }
}