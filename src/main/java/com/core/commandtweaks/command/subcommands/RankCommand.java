package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.player.Rank;
import com.core.commandtweaks.player.RankManager;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankCommand extends SubCommand {

    public RankCommand(){
        this.subCommandName = "rank";
        this.subCommandInfo = "Set a player's rank";
        this.subCommandAliases = new String[0];
        this.adminCommand = true;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 3) {
            Utils.sendError(p, "Incorrect amount of arguments!");

            return;
        }

        Player target = Bukkit.getServer().getPlayer(args[1]);

        if (target == null){
            Utils.sendError(p, "Player '" + args[1] + "' is not online!");
            return;
        }
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(target);

        String rankStr = "";

        for (int i = 2; i < args.length; i++){
            if (i == args.length - 1){
                rankStr += args[i];
            }
            else {
                rankStr += args[i] + " ";
            }
        }

        playerPlus.setRank(CommandTweaks.rankManager.getRank(rankStr));

        Utils.sendMessage(p, "Set " + ChatColor.AQUA + target.getName() + ChatColor.WHITE + "'s rank to " + playerPlus.getRank().getName());

        Utils.sendMessage(target, "Your rank has been set to " + playerPlus.getRank().getName());
    }

    @Override
    public String[] getSubCommandAutoComplete(String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new);
        }
        else if (args.length >= 2){
            return CommandTweaks.rankManager.getAllRanks();
        }
        return new String[0];
    }
}
