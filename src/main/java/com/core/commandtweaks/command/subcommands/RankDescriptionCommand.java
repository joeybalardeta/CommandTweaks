package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankDescriptionCommand extends SubCommand {

    public RankDescriptionCommand(){
        this.subCommandName = "rankdesc";
        this.subCommandInfo = "Set a player's rank description";
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

        String rankDescStr = "";

        for (int i = 2; i < args.length; i++){
            if (i == args.length - 1){
                rankDescStr += args[i];
            }
            else {
                rankDescStr += args[i] + " ";
            }
        }

        playerPlus.getRank().setDescription(rankDescStr);

        Utils.sendMessage(p, "Set " + ChatColor.AQUA + target.getName() + ChatColor.WHITE + "'s rank description to " + playerPlus.getRank().getDescription());

        Utils.sendMessage(target, "Your rank description has been set to " + playerPlus.getRank().getDescription());
    }

    @Override
    public String[] getSubCommandAutoComplete(String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new);
        }
        return new String[0];
    }
}
