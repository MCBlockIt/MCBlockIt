package it.mcblock.mcblockit.bukkit.command;

import java.util.List;

import it.mcblock.mcblockit.api.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 1) {
            return false;
        }
        final String reason = Utils.combineSplit(args, " ", 1, args.length - 1);
        final List<Player> playerMatch = Bukkit.matchPlayer(args[0]);
        if (playerMatch.size() == 1) {
            final Player target = playerMatch.get(0);
            String message;
            if (reason.length() > 0) {
                message = "      " + ChatColor.RED + "Kicked: " + ChatColor.WHITE + reason;
            } else {
                message =  "   " + ChatColor.RED + "Kicked.";
            }
            if (commandLabel.equals("KICK")) {
                target.getWorld().strikeLightningEffect(target.getLocation()); //BOOM
            }
            target.kickPlayer(message);
        }
        return true;
    }

}
