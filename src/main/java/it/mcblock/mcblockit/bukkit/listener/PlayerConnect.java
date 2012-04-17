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

/**
 * Listener for player connection based events
 * 
 * @author Matt Baxter
 * 
 *         Copyright 2012 Matt Baxter
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 *
 */
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
