package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitPlayer implements MCBIPlayer{
    private Player player;

    public BukkitPlayer(Player player){
        this.player=player;
    }

    @Override
    public String getIP() {
        return player.getAddress().getAddress().getHostAddress();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void kick(final String reason) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(MCBlockItPlugin.instance, new Runnable(){
            @Override
            public void run() {
                player.kickPlayer(reason);
            }
        });
    }

}
