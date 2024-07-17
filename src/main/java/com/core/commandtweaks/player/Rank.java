package com.core.commandtweaks.player;

import net.md_5.bungee.api.ChatColor;

public class Rank {
    protected String name;
    protected ChatColor nameColor = ChatColor.DARK_GREEN;
    protected String description = null;
    protected String descriptionColor = "" + ChatColor.YELLOW + ChatColor.ITALIC;
    protected boolean defaultRank = false;
    protected boolean admin = false;

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNoColor() {
        return this.name;
    }

    public String getName() {
        return this.nameColor + this.name;
    }

    public void setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public ChatColor getNameColor() {
        return this.nameColor;
    }

    public void setDescription(String description) {
        if (description == null) {
            return;
        }
        this.description = description;
    }

    public String getDescriptionNoColor() {
        return this.description;
    }

    public String getDescription() {
        return this.descriptionColor + this.description;
    }

    public void setDescriptionColor(String descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public String getDescriptionColor() {
        return this.descriptionColor;
    }

    public void setDefaultRank(boolean defaultRank) {
        this.defaultRank = defaultRank;
    }

    public boolean isDefaultRank() {
        return this.defaultRank;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Rank clone() {
        Rank rank = new Rank();
        rank.setName(this.name);
        rank.setNameColor(this.nameColor);
        rank.setDescription(this.description);
        rank.setDescriptionColor(this.descriptionColor);
        rank.setDefaultRank(this.defaultRank);
        rank.setAdmin(this.admin);
        return rank;
    }

    /*
    public void setRankColor() {
        switch (this.rankName) {
            case "Creator" -> this.rankColor = ChatColor.RED;
            case "Superuser" -> this.rankColor = ChatColor.GOLD;
            case "Moderator" -> this.rankColor = ChatColor.BLUE;
            case "Master Builder" -> this.rankColor = ChatColor.DARK_PURPLE;
            case "Streamer" -> this.rankColor = ChatColor.LIGHT_PURPLE;
            case "Cove Veteran" -> this.rankColor = ChatColor.DARK_AQUA;
            case "Member" -> this.rankColor = ChatColor.GREEN;
            default -> this.rankColor = ChatColor.DARK_GREEN;
        }

    }
     */

    /*
    public static String[] getAllRanks() {
        return new String[]{"Creator", "Superuser", "Moderator", "Master Builder", "Streamer", "Cove Veteran", "Member"};
    }
     */



}
