package it.mcblock.mcblockit.handlers;

import it.mcblock.mcblockit.api.MCBlockItAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;

public class PlayerConnect implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (Bukkit.getServer().getBannedPlayers().contains(Bukkit.getServer().getOfflinePlayer(event.getName()))) {
            event.disallow(Result.KICK_BANNED, MCBlockItAPI.KICK_REASON);
        }
    }

}
