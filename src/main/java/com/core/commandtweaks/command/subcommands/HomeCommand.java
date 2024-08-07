package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HomeCommand extends SubCommand {

    public HomeCommand() {
        this.subCommandName = "Home";
        this.subCommandInfo = "Teleport player to their spawn point";
        this.subCommandAliases = new String[0];
        this.adminCommand = true;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Location home = p.getBedSpawnLocation();
        if (home == null) {
            home = p.getWorld().getSpawnLocation();
        }
        p.teleport(home);
    }
}
