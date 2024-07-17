package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class CreatorRank extends Rank {
    public CreatorRank() {
        this.name = "Creator";
        this.nameColor = ChatColor.RED;
        this.description = "The creator of this plugin.";
        this.defaultRank = false;
        this.admin = true;
    }
}
