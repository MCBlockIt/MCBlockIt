package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIConfig;
import it.mcblock.mcblockit.api.MCBlockItAPI;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;

public class BukkitBlockItAPI extends MCBlockItAPI {

    private MCBlockItPlugin plugin;
    private BukkitConfig config;

    public BukkitBlockItAPI(MCBlockItPlugin plugin,String APIKey, File dataFolder) {
        super(APIKey, dataFolder);
        this.plugin=plugin;
        this.config=new BukkitConfig(plugin.getConfig());
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
