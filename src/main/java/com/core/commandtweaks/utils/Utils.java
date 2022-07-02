package com.core.commandtweaks.utils;

import com.core.commandtweaks.CommandTweaks;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class Utils {
    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + message);
    }

    public static void sendAdvancedMessage(Player player, TextComponent message){
        player.spigot().sendMessage(message);
    }

    public static void sendError(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + ChatColor.RED + message);
    }

    public static void consoleLog(Level level, String message){
        CommandTweaks.getInstance().getLogger().log(level, message);
    }
}
