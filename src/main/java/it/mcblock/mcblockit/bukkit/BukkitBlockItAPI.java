package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.MCBIConfig;
import it.mcblock.mcblockit.api.MCBlockItAPI;

import java.io.File;

import org.bukkit.Bukkit;

/**
 * A Bukkit implementation of the MCBlockIt API
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
public class BukkitBlockItAPI extends MCBlockItAPI {

    private final MCBlockItPlugin plugin;
    private final BukkitConfig config;

    public BukkitBlockItAPI(MCBlockItPlugin plugin, String APIKey, File dataFolder) {
        super(APIKey, dataFolder);
        this.plugin = plugin;
        this.config = new BukkitConfig(plugin.getConfig());
    }

    @Override
    public MCBIConfig getConfig() {
        return this.config;
    }

    @Override
    protected void shutdown() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {

            @Override
            public void run() {
                BukkitBlockItAPI.this.plugin.getLogger().info("Shutting down...");
                Bukkit.getPluginManager().disablePlugin(BukkitBlockItAPI.this.plugin);
            }

        });
    }

}
