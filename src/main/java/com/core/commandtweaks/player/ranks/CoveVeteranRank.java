package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class CoveVeteranRank extends Rank {
    public CoveVeteranRank() {
        this.name = "Cove Veteran";
        this.nameColor = ChatColor.DARK_AQUA;
        this.description = "Played on past servers.";
        this.defaultRank = false;
        this.admin = false;
    }
}
