package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TopCommand extends SubCommand {
    public TopCommand(){
        this.subCommandName = "top";
        this.subCommandInfo = "Teleport player to the top y coordinate at their location";
        this.subCommandAliases = new String[0];
    }


    @Override
    public void onCommand(Player p, String[] args) {
        // teleport player to top y-coordinate in their location(don't use in the nether please)
        Location l;

        if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)){
            return;
        }

        // safeguards so people don't get tp'ed into lava
        Location lCheck = new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation()) - 1, p.getLocation().getZ());
        int xPlus = 0;

        while (lCheck.getBlock().getType() == Material.LAVA) {
            xPlus += 10;
            lCheck = new Location(p.getWorld(), p.getLocation().getX() + xPlus, p.getWorld().getHighestBlockYAt(p.getLocation()) - 1, p.getLocation().getZ());
        }

        l = new Location(p.getWorld(), p.getLocation().getX() + xPlus, p.getWorld().getHighestBlockYAt(p.getLocation()) + 1, p.getLocation().getZ());

        p.teleport(l);
    }
}
