package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    public HelpCommand() {
        this.subCommandName = "help";
        this.subCommandInfo = "List all the commands in the CommandTweaks plugin";
        this.subCommandAliases = new String[0];
        this.adminCommand = false;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Utils.sendMessage(p, ChatColor.YELLOW + "List of commands: ");
        for (SubCommand subCommand : CommandTweaks.commandManager.getSubCommands()) {
            if (subCommand.checkPermission(p)) {
                Utils.sendMessage(p, ChatColor.AQUA + subCommand.subCommandName + ChatColor.WHITE + " - " + ChatColor.GOLD + subCommand.subCommandInfo);
            }
        }
    }
}
