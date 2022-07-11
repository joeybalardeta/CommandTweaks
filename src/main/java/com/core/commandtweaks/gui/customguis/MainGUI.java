package com.core.commandtweaks.gui.customguis;

import com.core.commandtweaks.gui.GUI;
import com.core.commandtweaks.player.PlayerPlus;
import net.md_5.bungee.api.ChatColor;


public class MainGUI extends GUI {
    public MainGUI(PlayerPlus playerPlus) {
        super(6, ChatColor.AQUA + "Main Menu");
    }
}
