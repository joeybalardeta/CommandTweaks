package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.entity.Player;

public class SaveCommand extends SubCommand {

    public SaveCommand(){
        this.subCommandName = "save";
        this.subCommandInfo = "Save all CommandTweaks data";
        this.subCommandAliases = new String[0];
        this.adminCommand = true;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        CommandTweaks.nexus.fileIO.save();
        Utils.sendMessage(p, "Saved all plugin files!");
    }
}
