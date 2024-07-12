package com.core.commandtweaks.player;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class CustomNameTags {
    private Scoreboard scoreBoard;

    private boolean initialized = false;

    public void init(){
        if (Bukkit.getOnlinePlayers().size() == 0){
            return;
        }
        scoreBoard = Bukkit.getScoreboardManager().getMainScoreboard();

        registerPlayerDeathsTag();

        initialized = true;
    }

    public void registerPlayerHealthTag(){
        if (scoreBoard.getObjective("health") != null){
            scoreBoard.getObjective("health").unregister();
        }

        Objective obj = scoreBoard.registerNewObjective("health", "health", ChatColor.RED  + "❤");
        // Objective obj2 = scoreBoard.registerNewObjective("deaths", "deaths", ChatColor.WHITE  + " Deaths");
        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        // obj2.setDisplaySlot(DisplaySlot.BELOW_NAME);

    }

    public void registerPlayerDeathsTag(){
        if (scoreBoard.getObjective("death_count") != null){
            scoreBoard.getObjective("death_count").unregister();
        }

        Objective obj = scoreBoard.registerNewObjective("death_count", "death_count", ChatColor.WHITE + " Deaths");
        // Objective obj2 = scoreBoard.registerNewObjective("deaths", "deaths", ChatColor.WHITE  + " Deaths");
        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        // obj2.setDisplaySlot(DisplaySlot.BELOW_NAME);

    }


    public boolean isInitialized(){
        return initialized;
    }
}
