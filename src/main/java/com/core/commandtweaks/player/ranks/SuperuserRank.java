package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class SuperuserRank extends Rank {
    public SuperuserRank() {
        this.name = "Superuser";
        this.nameColor = ChatColor.GOLD;
        this.description = "Has access to all commands.";
        this.defaultRank = false;
        this.admin = true;
    }
}
