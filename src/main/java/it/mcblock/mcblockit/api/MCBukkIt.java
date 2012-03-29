package it.mcblock.mcblockit.api;

import it.mcblock.mcblockit.MCBlockItPlugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;

public class MCBukkIt extends MCBlockItAPI {

    private MCBlockItPlugin plugin;
    private Bukkonfig config;

    public MCBukkIt(MCBlockItPlugin plugin,String APIKey, File dataFolder) {
        super(APIKey, dataFolder);
        this.plugin=plugin;
        this.config=new Bukkonfig(plugin.getConfig());
    }

    @Override
    public MCBIConfig getConfig() {
        return this.config;
    }

    @Override
    protected void banName(final String name) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){

            @Override
            public void run() {
                ((CraftServer)Bukkit.getServer()).getHandle().addUserBan(name.toLowerCase());
            }
           
        });
    }

    @Override
    protected void unbanName(final String name) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){

            @Override
            public void run() {
                ((CraftServer)Bukkit.getServer()).getHandle().removeUserBan(name.toLowerCase());
            }
           
        });
    }
}
