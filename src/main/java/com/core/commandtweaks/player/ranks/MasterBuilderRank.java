package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class MasterBuilderRank extends Rank {
    public MasterBuilderRank() {
        this.name = "Master Builder";
        this.nameColor = ChatColor.DARK_AQUA;
        this.description = "They're one of the best builders on the server!";
        this.defaultRank = false;
        this.admin = false;
    }
}
