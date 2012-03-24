package it.mcblock.mcblockit;

import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.commands.BanCommand;
import it.mcblock.mcblockit.commands.KickCommand;
import it.mcblock.mcblockit.commands.UnbanCommand;
import it.mcblock.mcblockit.handlers.PlayerConnect;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCBlockItPlugin extends JavaPlugin {

    private Thread APIThread;

    @Override
    public void onDisable() {
        if (this.APIThread != null) {
            this.APIThread.interrupt();
        }
        final PluginDescriptionFile description = this.getDescription();
        this.getLogger().info(description.getName() + " v" + description.getVersion() + " disabled.");
    }

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerConnect(), this);
        this.getCommand("ban").setExecutor(new BanCommand());
        this.getCommand("kick").setExecutor(new KickCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());

        final MCBlockItAPI api = new MCBlockItAPI(this, "");
        this.APIThread = new Thread(api);
        this.APIThread.start();

        final PluginDescriptionFile description = this.getDescription();
        this.getLogger().info(description.getName() + " v" + description.getVersion() + " enabled.");
    }
}
