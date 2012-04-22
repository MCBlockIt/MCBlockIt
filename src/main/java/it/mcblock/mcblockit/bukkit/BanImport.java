package it.mcblock.mcblockit.bukkit;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Set;

public class BanImport extends Thread {
    @Override
    public void run () {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) this.interrupt();
        Set<OfflinePlayer> playerSet = Bukkit.getServer().getBannedPlayers();
        Integer importedPlayers = 0;
        for (OfflinePlayer player : playerSet) {
            if (!BukkitBlockItAPI.isBanned(player.getName())) {
                MCBlockItAPI.ban(player.getName(), "[Import]", BanType.LOCAL, "Imported from banned-players.txt");
                importedPlayers++;
            }
            Bukkit.getServer().getBannedPlayers().remove(player);
        }
        Bukkit.getLogger().info(importedPlayers + " players imported from banned-players.txt!");
    }
}
