package com.core.commandtweaks.vanguard;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.UUID;

public class OreRatio {
    // calculates the ratio of valuable blocks mined to non-valuable blocks mined
    // returns 0 if the player's ratio is abnormal, returns 1 if the player looks normal
    public static boolean isLegitOreRatio(UUID uuid){
        OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);

        int netherrackMined = target.getStatistic(Statistic.MINE_BLOCK, Material.NETHERRACK);
        int ancientDebrisMined = target.getStatistic(Statistic.MINE_BLOCK, Material.ANCIENT_DEBRIS);

        if (netherrackMined < 200 || ancientDebrisMined < 8){
            return true;
        }

        if (!(netherrackMined < 200 || ancientDebrisMined < 8) && (netherrackMined / ancientDebrisMined < 500)){
            return false;
        }


        return true;
    }
}
