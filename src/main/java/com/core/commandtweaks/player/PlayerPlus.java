package com.core.commandtweaks.player;

import com.core.commandtweaks.gui.GUI;

public class PlayerPlus {
    // the rank of the user (admin, owner, etc.)
    private Rank rank;

    // the faction of the user, basically like clubs on hypixel
    private Faction faction;

    // gui in player class is so their copy of the gui that they are viewing doesn't get replaced by another one
    private GUI gui;

    public PlayerPlus(){

    }
}
