package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CoordsCommand extends SubCommand {
    public CoordsCommand() {
        this.subCommandName = "coords";
        this.subCommandInfo = "Send your coordinates to others";
        this.subCommandAliases = new String[0];
        this.adminCommand = false;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(p);

        Location location = playerPlus.getLocation();

        if (args.length == 1) {
            // send a message to all players with the coordinates of the player
            Utils.sendFauxChatMessage(p, ChatColor.GOLD + String.valueOf(location.getBlockX()) + ChatColor.WHITE + ", " + ChatColor.GOLD + String.valueOf(location.getBlockY()) + ChatColor.WHITE + ", " + ChatColor.GOLD + String.valueOf(location.getBlockZ()));
        }
        else if (args.length == 2) {
            // send a message to the specified player with the coordinates of the player
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                Utils.sendError(p, "Player '" + args[1] + "' is not online!");
                return;
            }

            String coordsStr = ChatColor.GOLD + String.valueOf(location.getBlockX()) + ChatColor.WHITE + ", " + ChatColor.GOLD + String.valueOf(location.getBlockY()) + ChatColor.WHITE + ", " + ChatColor.GOLD + String.valueOf(location.getBlockZ());

            if (target == p) {
                Utils.sendMessage(p, ChatColor.WHITE + "Your coords are: " + coordsStr);
                return;
            }

            Utils.sendMessage(p, ChatColor.AQUA + target.getName() + ChatColor.WHITE + " has received your coords: " + coordsStr);
            Utils.sendPrivateFauxChatMessage(p, target, coordsStr);
        }
        else {
            Utils.sendError(p, "Incorrect amount of arguments!");
        }


    }

    @Override
    public String[] getSubCommandAutoComplete(String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new);
        }

        return new String[0];
    }
}
