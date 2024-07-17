package com.core.commandtweaks.utils;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.player.PlayerPlus;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class Utils {
    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + message);
    }

    public static void sendAdvancedMessage(Player player, TextComponent message) {
        TextComponent prefix = new TextComponent(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] ");
        prefix.addExtra(message);
        player.spigot().sendMessage(prefix);
    }

    public static void sendError(Player player, String message) {
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "Command" + ChatColor.GRAY + "Tweaks" + ChatColor.WHITE + "] " + ChatColor.RED + message);
    }

    public static void sendFauxChatMessage(Player player, String message) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(player);
        TextComponent chatMessage = new TextComponent("");
        TextComponent prefix = new TextComponent(ChatColor.WHITE + "[" + playerPlus.getRank().getName() + ChatColor.WHITE + "] ");
        TextComponent username = new TextComponent(ChatColor.AQUA + player.getName());

        if (PlayerPlus.getPlayerPlus(player).getRank().getDescription() != null) {
            String description = PlayerPlus.getPlayerPlus(player).getRank().getDescription();
            if (Utils.isLink(PlayerPlus.getPlayerPlus(player).getRank().getDescriptionNoColor())) {
                description = ChatColor.UNDERLINE + description;
                prefix.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, PlayerPlus.getPlayerPlus(player).getRank().getDescriptionNoColor()));
            }

            prefix.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(description)));
        }

        username.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, player.getUniqueId().toString()));
        username.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.WHITE + player.getName() + "\n" + player.getUniqueId())));

        chatMessage.addExtra(prefix);
        chatMessage.addExtra(username);
        chatMessage.addExtra(ChatColor.WHITE + ": ");
        chatMessage.addExtra(message);

        for (Player online : Bukkit.getOnlinePlayers()) {
            online.spigot().sendMessage(chatMessage);
        }

    }

    public static void sendPrivateFauxChatMessage(Player player, Player target, String message) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(player);
        TextComponent prefix = new TextComponent(ChatColor.WHITE + "[" + playerPlus.getRank().toString() + ChatColor.WHITE + "] ");
        TextComponent username = new TextComponent(ChatColor.AQUA + "" + ChatColor.ITALIC + player.getName());

        username.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, player.getUniqueId().toString()));
        username.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.WHITE + player.getName() + "\n" + player.getUniqueId())));
        prefix.addExtra(username);
        prefix.addExtra(ChatColor.WHITE + ": ");
        prefix.addExtra(message);

        target.spigot().sendMessage(prefix);

    }

    public static void consoleLog(Level level, String message){
        CommandTweaks.getInstance().getLogger().log(level, message);
    }

    public static boolean isLink(String message) {
        if (message == null) {
            return false;
        }

        return message.matches(".*\\b(?:https?|ftp|file)://\\S+\\b.*");
    }
}
