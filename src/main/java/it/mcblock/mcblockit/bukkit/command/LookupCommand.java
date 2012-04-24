package it.mcblock.mcblockit.bukkit.command;

import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.api.userdata.UserData;
import it.mcblock.mcblockit.api.userdata.UserData.BanData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LookupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 1) {
            return false;
        }

        UserData userData = MCBlockItAPI.getFreshUserData(args[0]);
        BanData[] banData = userData.getBans();

        sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.WHITE + "User " + ChatColor.AQUA + userData.getUsername() + ChatColor.WHITE + " has " + ChatColor.YELLOW + userData.getReputation() + ChatColor.WHITE + " reputation.");

        if (userData.isStaff()) {
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.WHITE + userData.getUsername() + " is MCBlockIt Staff!");
        }

        if (banData.length > 0) {
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.DARK_RED + "Global Bans:");
            for (BanData ban : banData) {
                sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.AQUA + ban.getServer() + ChatColor.WHITE + ": " + ban.getReason());
            }
        }

        return true;
    }
}
