package com.core.commandtweaks.player;

import com.core.commandtweaks.player.ranks.*;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.logging.Level;

public class RankManager {
    private ArrayList<Rank> ranks = new ArrayList<Rank>();

    public RankManager() {
    }

    public void init() {
        this.loadRankList();

        Utils.consoleLog(Level.INFO, "RankManager online.");
    }

    private void loadRankList() {
        // load an instance of every rank (from ranks package) into the ranks list
        this.ranks.add(new CreatorRank());
        this.ranks.add(new SuperuserRank());
        this.ranks.add(new MasterBuilderRank());
        this.ranks.add(new CoveVeteranRank());
        this.ranks.add(new StreamerRank());
        this.ranks.add(new MemberRank());
    }

    public Rank getRank(String rankName) {
        // if rankName is an empty string, return the default rank
        if (rankName == null) {
            return this.defaultRank().clone();
        }

        for (Rank rank : this.ranks) {
            if (rank.getNameNoColor().equalsIgnoreCase(rankName)) {
                return rank.clone();
            }
        }

        Rank rank = new Rank();
        rank.setName(rankName);
        rank.setNameColor(this.defaultRank().getNameColor());

        return rank;
    }

    public Rank defaultRank() {
        for (Rank rank : this.ranks) {
            if (rank.isDefaultRank()) {
                return rank;
            }
        }

        Rank rank = new Rank();
        rank.setName("Default Rank");

        return rank;
    }

    public String[] getAllRanks() {
        String[] rankNames = new String[this.ranks.size()];
        for (int i = 0; i < this.ranks.size(); i++) {
            rankNames[i] = this.ranks.get(i).getNameNoColor();
        }
        return rankNames;
    }
}
