package com.core.commandtweaks.command;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SubCommand {
    public SubCommand() {

    }

    // subcommand member variables
    public String subCommandName;

    public String subCommandInfo;

    public String[] subCommandAliases;

    // subcommand functions
    public void onCommand(Player p, String[] args){

    }

    public String getName(){
        return subCommandName;
    }

    public String getInfo(){
        return subCommandInfo;
    }

    public String[] getAliases(){
        return subCommandAliases;
    }

    public String[] getSubCommandAutoComplete(String args[]){
        return new String[0];
    }
}
