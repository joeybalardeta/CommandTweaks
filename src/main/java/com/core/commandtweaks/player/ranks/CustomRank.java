package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class CustomRank extends Rank {
    public CustomRank() {
        this.name = "Custom Rank";
        this.nameColor = ChatColor.DARK_GREEN;
        this.defaultRank = false;
        this.admin = false;
    }
}
