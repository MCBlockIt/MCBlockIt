package it.mcblock.mcblockit.bukkit.listener;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.api.userdata.UserData;
import it.mcblock.mcblockit.bukkit.BukkitPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;

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
    public void onPlayerLogin(PlayerLoginEvent event) {
        BukkitPlayer player = new BukkitPlayer(event.getPlayer());
        if (!MCBlockItAPI.playerJoin(player)) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, MCBlockItAPI.KICK_REASON_BLOCKED);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (MCBlockItAPI.isBanned(event.getName())) {
            event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, MCBlockItAPI.KICK_REASON_BANNED);
        } else {
            String tempBan = MCBlockItAPI.isTempBanned(event.getName());
            if (tempBan != null) event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, MCBlockItAPI.KICK_REASON_TEMP_BANNED + tempBan);
        }
        MCBlockItAPI.addUserIPPair(event.getName(), event.getAddress().getHostAddress());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        MCBlockItAPI.playerQuit(new BukkitPlayer(event.getPlayer()));
    }

}
