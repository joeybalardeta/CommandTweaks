package com.core.commandtweaks.command;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.subcommands.*;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;

public class CommandManager implements CommandExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>();
    private final CommandTweaks instance = CommandTweaks.getInstance();

    private final String rootCommand = "ct";

    public String getRootCommand(){
        return rootCommand;
    }

    public void init(){
        instance.getCommand(this.getRootCommand()).setExecutor(this);
        this.subCommands.add(new TopCommand());
        this.subCommands.add(new RankCommand());
        this.subCommands.add(new SaveCommand());
        this.subCommands.add(new InfoCommand());
        this.subCommands.add(new HelpCommand());
        this.subCommands.add(new MenuCommand());
        Utils.consoleLog(Level.INFO, "CommandManager (Plugin command parsing/routing) online.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can run Commands+ commands!");
            return false;
        }

        Player p = (Player) sender;

        if (!command.getName().equalsIgnoreCase(rootCommand)) {
            return false;
        }

        if (args.length == 0) {
            return false;
        }

        for (SubCommand sc : subCommands) {
            if (sc.getName().equals(args[0])){
                sc.onCommand(p, args);
            }
        }

        return false;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return this.subCommands;
    }
}
