package it.mcblock.mcblockit.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import it.mcblock.mcblockit.api.MCBlockItAPI;

import java.util.*;

public class BanImport extends Thread {
    @Override
    public void run() {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) {
            this.interrupt();
        }
        final Iterator<OfflinePlayer> playerSet = Bukkit.getServer().getBannedPlayers().iterator();
        Integer importedPlayers = 0;
        List<String> importArray = new ArrayList<String>();
        while (playerSet.hasNext()) {
            final OfflinePlayer player = playerSet.next();
            if (!MCBlockItAPI.isBanned(player.getName())) {
                importArray.add(player.getName());
                if (importArray.size() == 40) {
                    MCBlockItAPI.importBans(importArray);
                    importArray = new ArrayList<String>();
                }
                importedPlayers++;
            }
            player.setBanned(false);
        }
        if (importArray.size() > 0) {
            MCBlockItAPI.importBans(importArray);
        }
        Bukkit.getLogger().info(importedPlayers + " players imported from banned-players.txt!");
    }
}
