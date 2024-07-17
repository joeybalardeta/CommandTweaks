package com.core.commandtweaks.player.ranks;

import com.core.commandtweaks.player.Rank;
import net.md_5.bungee.api.ChatColor;

public class MemberRank extends Rank {
    public MemberRank() {
        this.name = "Member";
        this.nameColor = ChatColor.GREEN;
        this.defaultRank = true;
        this.admin = false;
    }
}
