package com.core.commandtweaks.player;

import net.md_5.bungee.api.ChatColor;

public class Rank {
    private String rankName;
    private ChatColor rankColor;
    public Rank(String rank) {
        if (rank == null){
            this.rankName = "Member";
        }
        else{
            this.rankName = rank;
        }

        this.setRankColor();
    }

    public void setRankColor() {
        if (this.rankName.equals("Creator")){
            this.rankColor = ChatColor.RED;
        }
        else if (this.rankName.equals("Moderator")){
            this.rankColor = ChatColor.BLUE;
        }
        else if (this.rankName.equals("Streamer")){
            this.rankColor = ChatColor.LIGHT_PURPLE;
        }
        else if (this.rankName.equals("Member")){
            this.rankColor = ChatColor.GREEN;
        }
        else {
            this.rankColor = ChatColor.DARK_GREEN;
        }

    }

    public String toString() {
        return this.rankColor + this.rankName;
    }

    public String toStringNoColor() {
        return this.rankName;
    }

}
