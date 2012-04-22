package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BanImport extends Thread {
    @Override
    public void run () {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) this.interrupt();
        Set<OfflinePlayer> playerSet = Bukkit.getServer().getBannedPlayers();
        Integer importedPlayers = 0;
        Integer totalImported   = 0;
        String[] importArray = new String[40];
        for (Iterator<OfflinePlayer> i = playerSet.iterator(); i.hasNext();) {
            OfflinePlayer player = i.next();
            if (!BukkitBlockItAPI.isBanned(player.getName())) {
                importArray[importedPlayers] = player.getName();
                importedPlayers++;
                if (importArray.length == 40) {
                    BukkitBlockItAPI.importBans(importArray);
                    importArray = new String[40];
                    importedPlayers = 0;
                }
                totalImported++;
            }
            Bukkit.getServer().getBannedPlayers().remove(player);
        }
        if (importArray.length > 0) {
            BukkitBlockItAPI.importBans(importArray);
        }
        Bukkit.getLogger().info("[MCBlockIt] " + totalImported + " players imported from banned-players.txt!");
    }
}
