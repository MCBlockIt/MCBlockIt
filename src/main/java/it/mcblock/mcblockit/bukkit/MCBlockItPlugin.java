package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.bukkit.command.BanCommand;
import it.mcblock.mcblockit.bukkit.command.KickCommand;
import it.mcblock.mcblockit.bukkit.command.UnbanCommand;
import it.mcblock.mcblockit.bukkit.listener.PlayerConnect;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

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
        final String apikey = this.getConfig().getString("APIKEY");
        if ((apikey == null) || (apikey.length() != 32)) {
            this.getLogger().warning("Set up your config and restart");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerConnect(), this);
        this.getCommand("ban").setExecutor(new BanCommand());
        this.getCommand("kick").setExecutor(new KickCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());

        MCBlockItAPI.initialize(new BukkitBlockItAPI(this, apikey, this.getDataFolder()));

        MCBlockItPlugin.instance = this;

        final PluginDescriptionFile description = this.getDescription();
        this.getLogger().info(description.getName() + " v" + description.getVersion() + " enabled.");
    }
}
