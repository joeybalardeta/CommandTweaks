package com.core.commandtweaks.command;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.command.subcommands.*;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>();
    private final CommandTweaks instance = CommandTweaks.getInstance();

    private final String rootCommand = "ct";

    public String getRootCommand(){
        return rootCommand;
    }

    public void init(){
        instance.getCommand(this.getRootCommand()).setExecutor(this);
        instance.getCommand(this.getRootCommand()).setTabCompleter(this);
        this.subCommands.add(new TopCommand());
        this.subCommands.add(new RankCommand());
        this.subCommands.add(new SaveCommand());
        this.subCommands.add(new InfoCommand());
        this.subCommands.add(new HelpCommand());
        this.subCommands.add(new MenuCommand());
        this.subCommands.add(new ParticlesCommand());
        this.subCommands.add(new FauxChatStatusCommand());
        Utils.consoleLog(Level.INFO, "CommandManager (Plugin command parsing/routing) online.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can run CommandTweaks commands!");
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

    // write a method that autocompletes the command
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)){
            return null;
        }

        Player p = (Player) sender;

        if (!command.getName().equalsIgnoreCase(rootCommand)) {
            return null;
        }

        if (args.length == 0) {
            return null;
        }

        List<String> autoCompleteStrs = new ArrayList<String>();
        if (args.length == 1) {
            for (SubCommand sc : this.subCommands) {
                if (sc.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    autoCompleteStrs.add(sc.getName());
                }
            }
        }
        else {
            int argIndex = args.length - 1;

            // get the args minus the first one
            String autocompleteArgs[] = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                autocompleteArgs[i - 1] = args[i];
            }


            for (SubCommand sc : this.subCommands) {
                if (sc.getName().equalsIgnoreCase(args[0])) {
                    for (String str : sc.getSubCommandAutoComplete(autocompleteArgs)) {
                        if (str.toLowerCase().startsWith(args[argIndex].toLowerCase())) {
                            autoCompleteStrs.add(str);
                        }
                    }
                }
            }
        }

        return autoCompleteStrs;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return this.subCommands;
    }
}
