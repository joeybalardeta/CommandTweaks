package com.core.commandtweaks.event;

import com.core.commandtweaks.CommandTweaks;

public class EventManager {
    private CommandTweaks instance;

    public void init(){
        instance = CommandTweaks.getInstance();

        instance.getServer().getPluginManager().registerEvents(new PlayerEvent(), instance);
        instance.getServer().getPluginManager().registerEvents(new GUIEvent(), instance);
    }
}
