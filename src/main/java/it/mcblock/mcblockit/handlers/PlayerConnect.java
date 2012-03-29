package it.mcblock.mcblockit.handlers;

import it.mcblock.mcblockit.api.BukkitPlayer;
import it.mcblock.mcblockit.api.MCBukkIt;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnect implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (Bukkit.getServer().getBannedPlayers().contains(Bukkit.getServer().getOfflinePlayer(event.getName()))) {
            event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, MCBukkIt.KICK_REASON_BANNED);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event){
        if(!MCBukkIt.playerJoin(new BukkitPlayer(event.getPlayer()))){
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, MCBukkIt.KICK_REASON_BLOCKED);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event){
        MCBukkIt.playerQuit(new BukkitPlayer(event.getPlayer()));
    }
       

}
