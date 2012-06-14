package it.mcblock.mcblockit.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import it.mcblock.mcblockit.api.MCBlockItAPI;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class BanImport extends Thread {
    @Override
    public void run() {
        if (Bukkit.getServer().getBannedPlayers().isEmpty()) {
            this.interrupt();
        }
        try{
            File f1 = new File("banned-players.txt");
            File f2 = new File("banned-players." + (new Date()).getTime() + ".bak");
            InputStream in = new FileInputStream(f1);

            //For Append the file.
            //  OutputStream out = new FileOutputStream(f2,true);

            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

        }
        catch(FileNotFoundException ex){
            MCBlockItAPI.logAdd(Level.WARNING, "[MCBlockIt] banned-players.txt could not be found? Unable to backup!");
        }
        catch(IOException e){
            MCBlockItAPI.logAdd(Level.WARNING, "[MCBlockIt] Unknown I/O error during backup of banned-players.txt: " + e.getMessage());
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
        Bukkit.getLogger().info("[MCBlockIt] " + importedPlayers + " players imported from banned-players.txt!");
    }
}
