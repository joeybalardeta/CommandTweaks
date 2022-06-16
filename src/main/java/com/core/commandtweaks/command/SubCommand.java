package com.core.commandtweaks.command;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class SubCommand {
    public SubCommand() {

    }

    // subcommand member variables
    public String subCommandName;

    public String subCommandInfo;

    public String[] subCommandAliases;

    // subcommand functions
    public abstract void onCommand(Player p, String[] args);

    public abstract String getName();

    public abstract String getInfo();

    public abstract String[] getAliases();
}
