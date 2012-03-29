package it.mcblock.mcblockit.bukkit.listener;

import it.mcblock.mcblockit.bukkit.BukkitPlayer;
import it.mcblock.mcblockit.bukkit.BukkitBlockItAPI;

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
            event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, BukkitBlockItAPI.KICK_REASON_BANNED);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event){
        if(!BukkitBlockItAPI.playerJoin(new BukkitPlayer(event.getPlayer()))){
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, BukkitBlockItAPI.KICK_REASON_BLOCKED);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event){
        BukkitBlockItAPI.playerQuit(new BukkitPlayer(event.getPlayer()));
    }
       

}
