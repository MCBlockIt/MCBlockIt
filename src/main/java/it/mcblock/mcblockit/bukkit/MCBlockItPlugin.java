package it.mcblock.mcblockit.bukkit;

import java.io.File;

import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.bukkit.command.BanCommand;
import it.mcblock.mcblockit.bukkit.command.KickCommand;
import it.mcblock.mcblockit.bukkit.command.LookupCommand;
import it.mcblock.mcblockit.bukkit.command.UnbanCommand;
import it.mcblock.mcblockit.bukkit.listener.PlayerConnect;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A Bukkit plugin for MCBlockIt
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
public class MCBlockItPlugin extends JavaPlugin {

    public static MCBlockItPlugin instance;

    @Override
    public void onDisable() {
        if (MCBlockItAPI.enabled()) {
            MCBlockItAPI.stop();
        }
        MCBlockItPlugin.instance = null;
        final PluginDescriptionFile description = this.getDescription();
        this.getLogger().info(description.getName() + " v" + description.getVersion() + " disabled.");
    }

    @Override
    public void onEnable() {
        final File configFile=new File(this.getDataFolder(),"config.yml");
        if(!configFile.exists()){
            this.saveDefaultConfig();
            this.getLogger().info("Creating empty config.yml");
            this.getLogger().info("You need to set up your config and restart");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        final String apikey = this.getConfig().getString("APIKEY");
        if ((apikey == null) || (apikey.length() != 36)) {
            this.getLogger().warning("Set up your config and restart");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getServer().getPluginManager().registerEvents(new PlayerConnect(), this);
        this.getCommand("ban").setExecutor(new BanCommand());
        this.getCommand("gban").setExecutor(new BanCommand());
        this.getCommand("kick").setExecutor(new KickCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());
        this.getCommand("lookup").setExecutor(new LookupCommand());

        MCBlockItAPI.initialize(new BukkitBlockItAPI(this, apikey, this.getDataFolder()));

        MCBlockItPlugin.instance = this;

        new BanImport().start();

        final PluginDescriptionFile description = this.getDescription();
        this.getLogger().info(description.getName() + " v" + description.getVersion() + " enabled.");
    }
}
