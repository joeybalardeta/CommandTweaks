package com.core.commandtweaks.player;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.gui.GUI;
import com.core.commandtweaks.vanillaplus.particles.ParticleEffect;
import com.core.commandtweaks.vanillaplus.particles.particleeffects.FlameEffect;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerPlus {
    // the player object linked to the PlayerPlus object
    private Player player;

    // the rank of the user (admin, owner, etc.)
    private Rank rank;

    // the faction of the user, basically like clubs on hypixel
    private Faction faction;

    // gui in player class is so their copy of the gui that they are viewing doesn't get replaced by another one
    private GUI gui;

    private ParticleEffect particleEffect;

    private static HashMap<Player, PlayerPlus> bindedPlayerPluses = new HashMap<Player, PlayerPlus>();

    public PlayerPlus(Player player){
        this.player = player;
        bindedPlayerPluses.put(player, this);
        //this.particleEffect = new FlameEffect(PlayerPlus.getPlayerPlus(player));
        //this.particleEffect.start(1);
    }

    public void removePlayerPlus(){
        bindedPlayerPluses.remove(this.getPlayer());

        if (this.particleEffect != null) {
            this.particleEffect.end();
        }

        CommandTweaks.nexus.fileIO.writePlayerPlus(this);
    }

    public void setRank(Rank rank){
        this.rank = rank;
        CommandTweaks.nexus.fileIO.writePlayerPlus(this);
    }

    public void setFaction(Faction faction){
        this.faction = faction;
    }

    public void setParticleEffect(ParticleEffect particleEffect){
        this.particleEffect = particleEffect;
    }

    public Rank getRank(){
        return this.rank;
    }

    public Faction getFaction(){
        return this.faction;
    }

    public ParticleEffect getParticleEffect(){
        return particleEffect;
    }

    public void endParticleEffect(){
        if (particleEffect == null){
            return;
        }

        particleEffect.end();
        particleEffect = null;
    }

    public static PlayerPlus getPlayerPlus(Player player){
        return bindedPlayerPluses.get(player);
    }

    public Player getPlayer(){
        return this.player;
    }

    public double getPlayerDirectionFloat() {
        double rotation = this.player.getLocation().getYaw() - 180;
        if (rotation < 0) {
            rotation += 360.0;
        }
        return rotation;
    }
}
