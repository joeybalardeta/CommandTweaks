package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class InfoCommand extends SubCommand {
    public InfoCommand(){
        this.subCommandName = "info";
        this.subCommandInfo = "Prints out info about the CommandTweaks plugin.";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        TextComponent message = new TextComponent("GitHub");
        Utils.sendAdvancedMessage(p, message);
    }

    @Override
    public String getName() {
        return subCommandName;
    }

    @Override
    public String getInfo() {
        return subCommandInfo;
    }

    @Override
    public String[] getAliases() {
        return subCommandAliases;
    }
}
