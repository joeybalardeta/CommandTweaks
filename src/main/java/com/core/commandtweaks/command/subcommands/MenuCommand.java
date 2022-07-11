package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.gui.customguis.MainGUI;
import com.core.commandtweaks.player.PlayerPlus;
import org.bukkit.entity.Player;

public class MenuCommand extends SubCommand {
    public MenuCommand(){
        this.subCommandName = "menu";
        this.subCommandInfo = "Open the menu for the CommandTweaks plugin";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        PlayerPlus.getPlayerPlus(p).setGUI(new MainGUI(PlayerPlus.getPlayerPlus(p)));
    }
}
