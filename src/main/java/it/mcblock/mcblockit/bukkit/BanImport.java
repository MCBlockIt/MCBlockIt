package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.*;

public class BanImport extends Thread {
    @Override
    public void run () {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) this.interrupt();
        Iterator playerSet = Bukkit.getServer().getBannedPlayers().iterator();
        Integer importedPlayers = 0;
        List<String> importArray = new ArrayList<String>();
        while (playerSet.hasNext()) {
            OfflinePlayer player = (OfflinePlayer)playerSet.next();
            if (!BukkitBlockItAPI.isBanned(player.getName())) {
                importArray.add(player.getName());
                if (importArray.size() == 40) {
                    BukkitBlockItAPI.importBans(importArray);
                    importArray = new ArrayList<String>();
                }
                importedPlayers++;
            }
            player.setBanned(false);
        }
        if (importArray.size() > 0) {
            BukkitBlockItAPI.importBans(importArray);
        }
        Bukkit.getLogger().info(importedPlayers + " players imported from banned-players.txt!");
    }
}
