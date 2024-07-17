package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoCommand extends SubCommand {
    public InfoCommand(){
        this.subCommandName = "info";
        this.subCommandInfo = "Print out info about the CommandTweaks plugin";
        this.subCommandAliases = new String[0];
        this.adminCommand = false;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Utils.sendMessage(p, ChatColor.GOLD + "Plugin Name: " + ChatColor.WHITE + CommandTweaks.PLUGINNAME);
        Utils.sendMessage(p, ChatColor.GOLD + "Authors: " + ChatColor.AQUA + "Joey Balardeta");
        Utils.sendMessage(p, ChatColor.GOLD + "Version: " + ChatColor.AQUA + CommandTweaks.VERSION);

        TextComponent message = new TextComponent(ChatColor.DARK_GRAY + "GitHub Repository");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/joeybalardeta/CommandTweaks"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "" + ChatColor.ITALIC + "Visit the GitHub repository!")));
        Utils.sendAdvancedMessage(p, message);
    }
}
