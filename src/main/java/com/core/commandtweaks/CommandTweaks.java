package com.core.commandtweaks;

import lombok.Getter;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CommandTweaks extends JavaPlugin {

    private final PluginDescriptionFile pluginDescriptionFile = this.getDescription();
    private final String VERSION = pluginDescriptionFile.getVersion();
    private final String PLUGIN_NAME = pluginDescriptionFile.getName();
    private static CommandTweaks instance;

    @Override
    public void onLoad() { instance = this; }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
