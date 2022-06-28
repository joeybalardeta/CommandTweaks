package com.core.commandtweaks.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + message);
    }

    public static void sendError(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + ChatColor.RED + message);
    }
}
