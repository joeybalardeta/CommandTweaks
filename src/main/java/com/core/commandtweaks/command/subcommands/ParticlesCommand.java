package com.core.commandtweaks.command.subcommands;

import com.core.commandtweaks.command.SubCommand;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.utils.Utils;
import com.core.commandtweaks.vanillaplus.particles.particleeffects.FlameEffect;
import org.bukkit.entity.Player;

public class ParticlesCommand extends SubCommand {
    public ParticlesCommand(){
        this.subCommandName = "particles";
        this.subCommandInfo = "Set custom particle effect";
        this.subCommandAliases = new String[0];
        this.adminCommand = true;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length != 2){
            Utils.sendError(p, "Incorrect amount of arguments!");
            return;
        }

        if (args[1].equalsIgnoreCase("flames")){
            PlayerPlus.getPlayerPlus(p).setParticleEffect(new FlameEffect(PlayerPlus.getPlayerPlus(p)));

            Utils.sendMessage(p, "Set new player particles to flames!");
        }
        else if (args[1].equalsIgnoreCase("none")){
            PlayerPlus.getPlayerPlus(p).setParticleEffect(null);
        }
    }

    @Override
    public String[] getSubCommandAutoComplete(String[] args) {
        if (args.length == 1) {
            return new String[]{"Flames", "None"};
        }
        return new String[0];
    }
}
