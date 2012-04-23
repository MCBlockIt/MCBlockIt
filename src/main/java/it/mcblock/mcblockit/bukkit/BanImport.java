package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

public class BanImport extends Thread {
    @Override
    public void run () {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) this.interrupt();
        Iterator playerSet = Bukkit.getServer().getBannedPlayers().iterator();
        Integer importedPlayers = 0;
        String[] importArray = new String[40];
        while (playerSet.hasNext()) {
            OfflinePlayer player = (OfflinePlayer)playerSet.next();
            if (!BukkitBlockItAPI.isBanned(player.getName())) {
                importArray[importedPlayers] = player.getName();
                if (importArray.length == 40) {
                    BukkitBlockItAPI.importBans(importArray);
                    importArray = new String[40];
                }
                importedPlayers++;
            }
            Bukkit.getServer().getBannedPlayers().remove(player);
        }
        if (importArray.length > 0) {
            BukkitBlockItAPI.importBans(importArray);
        }
        Bukkit.getLogger().info(importedPlayers + " players imported from banned-players.txt!");
    }
}
