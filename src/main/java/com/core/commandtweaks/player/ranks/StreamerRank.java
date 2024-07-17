package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class StreamerRank extends Rank {
    public StreamerRank() {
        this.name = "Streamer";
        this.nameColor = ChatColor.LIGHT_PURPLE;
        this.description = "Put stream link here!";
        this.descriptionColor = "" + ChatColor.LIGHT_PURPLE + ChatColor.ITALIC;
        this.defaultRank = false;
        this.admin = false;
    }
}