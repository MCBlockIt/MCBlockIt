package it.mcblock.mcblockit;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCBlockItPlugin extends JavaPlugin {
    @Override
    public void onEnable(){
        PluginDescriptionFile description=this.getDescription();
        this.getLogger().info(description.getName()+" v"+description.getVersion()+" enabled.");
    }
    
    @Override
    public void onDisable(){
        PluginDescriptionFile description=this.getDescription();
        this.getLogger().info(description.getName()+" v"+description.getVersion()+" disabled.");
    }
}
