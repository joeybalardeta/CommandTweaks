package com.core.commandtweaks.player;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.gui.GUI;
import com.core.commandtweaks.nexus.Nexus;
import com.core.commandtweaks.vanillaplus.particles.ParticleEffect;
import org.bukkit.entity.Player;
import org.bukkit.Location;

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
    }

    public void removePlayerPlus(){
        bindedPlayerPluses.remove(this.getPlayer());

        if (this.particleEffect != null) {
            this.particleEffect.end();
        }

        this.save();
    }

    public static PlayerPlus load(Player p){
        PlayerPlus playerPlus = new PlayerPlus(p);

        Rank rank = CommandTweaks.rankManager.getRank(Nexus.playerDataConfig.getString("users." + p.getUniqueId() + ".rank"));
        playerPlus.setRank(rank);

        String description = Nexus.playerDataConfig.getString("users." + p.getUniqueId() + ".rankdesc");
        playerPlus.getRank().setDescription(description);

        return playerPlus;
    }

    public void save(){
        Nexus.playerDataConfig.set("users." + this.getPlayer().getUniqueId() + ".name", this.getPlayer().getDisplayName());

        Nexus.playerDataConfig.set("users." + this.getPlayer().getUniqueId() + ".rank", this.getRank().getNameNoColor());
        Nexus.playerDataConfig.set("users." + this.getPlayer().getUniqueId() + ".rankdesc", this.getRank().getDescriptionNoColor());
    }

    public void setRank(Rank rank){
        this.rank = rank;
        this.save();
    }

    public void setFaction(Faction faction){
        this.faction = faction;
    }

    public void setParticleEffect(ParticleEffect particleEffect) {
        if (this.particleEffect != null){
            this.particleEffect.end();
        }

        this.particleEffect = particleEffect;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
        if (this.gui != null) {
            this.getPlayer().openInventory(this.gui.getInventory());
        }
    }

    public GUI getGUI() {
        return this.gui;
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
    public Location getLocation() {
        return this.player.getLocation();
    }
}
