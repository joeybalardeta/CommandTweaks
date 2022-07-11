package com.core.commandtweaks.event;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.player.PlayerPlus;
import com.core.commandtweaks.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerEvent implements Listener {
    private final CommandTweaks instance = CommandTweaks.getInstance();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!CommandTweaks.customNameTags.isInitialized()){
            CommandTweaks.customNameTags.init();
        }


        CommandTweaks.nexus.fileIO.loadPlayerPlus(event.getPlayer());

        // delayed event, probably gonna use this to send messages to users on join.
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(instance, new Runnable() {
            public void run() {

            }
        }, 10L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        PlayerPlus.getPlayerPlus(event.getPlayer()).removePlayerPlus();
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player p = event.getPlayer();


        // poison Celi if she eats fish :)
        if (event.getItem().equals(new ItemStack(Material.COD)) || event.getItem().equals(new ItemStack(Material.COOKED_COD))
            || event.getItem().equals(new ItemStack(Material.SALMON)) || event.getItem().equals(new ItemStack(Material.COOKED_SALMON))
            || event.getItem().equals(new ItemStack(Material.TROPICAL_FISH)) || event.getItem().equals(new ItemStack(Material.PUFFERFISH))){
            if (p.getName().equals("smokinganthrax")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 3, true));
            }
        }
    }

    @EventHandler
    public void onPlayerPublicMessage(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player p = event.getPlayer();
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(p);



        for (Player online : Bukkit.getOnlinePlayers()) {
            online.sendMessage(ChatColor.WHITE + "[" + playerPlus.getRank().toString() + ChatColor.WHITE + "] " + ChatColor.AQUA + p.getName() + ChatColor.WHITE + ": " + event.getMessage());
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        CommandTweaks.nexus.fileIO.logCommand(event.getPlayer().getDisplayName(), event.getMessage());
    }

}
