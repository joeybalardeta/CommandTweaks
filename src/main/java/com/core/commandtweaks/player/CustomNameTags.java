package com.core.commandtweaks.player;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class CustomNameTags {
    private Scoreboard scoreBoard;

    public void init(){
        scoreBoard = Bukkit.getScoreboardManager().getMainScoreboard();

        registerPlayerHealthTag();
    }

    public void registerPlayerHealthTag(){
        if (scoreBoard.getObjective("health") != null){
            scoreBoard.getObjective("health").unregister();
        }

        Objective obj = scoreBoard.registerNewObjective("health", "health", ChatColor.RED  + "❤");
        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);

    }
}
