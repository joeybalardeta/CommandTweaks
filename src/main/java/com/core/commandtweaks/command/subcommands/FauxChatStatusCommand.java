package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.player.Rank;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FauxChatStatusCommand extends SubCommand {

    public FauxChatStatusCommand(){
        this.subCommandName = "fauxchat";
        this.subCommandInfo = "Set the status of Faux Chat";
        this.subCommandAliases = new String[0];
        this.adminCommand = true;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2) {
            Utils.sendError(p, "Incorrect amount of arguments!");
            return;
        }

        String statusStr = args[1];
        boolean status = false;

        if (statusStr.equals("enable")) {
            status = true;
            Utils.sendMessage(p, "Enabled Faux Chat!");
        }
        else if (statusStr.equals("disable")) {
            status = false;
            Utils.sendMessage(p, "Disabled Faux Chat!");
        }
        else {
            Utils.sendError(p, "Incorrect argument!");
            return;
        }

        CommandTweaks.nexus.fileIO.setFauxChatStatus(status);
    }

    @Override
    public String[] getSubCommandAutoComplete(String[] args) {
        if (args.length == 1) {
            return new String[]{"enable", "disable"};
        }
        return new String[0];
    }
}
