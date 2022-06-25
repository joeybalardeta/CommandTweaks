package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HomeCommand extends SubCommand {

    public HomeCommand() {
        this.subCommandName = "Home";
        this.subCommandInfo = "Teleports player to their spawn point";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Location home = p.getBedSpawnLocation();
        if (home == null) {
            home = p.getWorld().getSpawnLocation();
        }
        p.teleport(home);
    }

    @Override
    public String getName() {
        return this.subCommandName;
    }

    @Override
    public String getInfo() {
        return this.subCommandInfo;
    }

    @Override
    public String[] getAliases() {
        return this.subCommandAliases;
    }
}
