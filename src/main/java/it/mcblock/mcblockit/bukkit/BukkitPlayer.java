package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitPlayer implements MCBIPlayer {
    private final Player player;

    public BukkitPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getIP() {
        return this.player.getAddress().getAddress().getHostAddress();
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public void kick(final String reason) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(MCBlockItPlugin.instance, new Runnable() {

            @Override
            public void run() {
                BukkitPlayer.this.player.kickPlayer(reason);
            }

        });
    }

    @Override
    public void messageIfAdmin(final String message) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(MCBlockItPlugin.instance, new Runnable() {

            @Override
            public void run() {
                if (BukkitPlayer.this.player.hasPermission("mcblockit.notifications")) {
                    BukkitPlayer.this.player.sendMessage(message);
                }
            }

        });
    }

}
