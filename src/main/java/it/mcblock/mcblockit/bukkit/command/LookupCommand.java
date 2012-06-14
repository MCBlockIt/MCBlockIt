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

        final UserData userData = MCBlockItAPI.getFreshUserData(args[0]);
        final BanData[] banData = userData.getBans();
        final String[] altAccounts = userData.getAltAccounts();

        sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.WHITE + "User " + ChatColor.AQUA + userData.getUsername() + ChatColor.WHITE + " has " + ChatColor.YELLOW + userData.getReputation() + ChatColor.WHITE + " reputation.");

        if (banData.length > 0) {
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.DARK_RED + "Global Bans:");
            for (final BanData ban : banData) {
                sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.AQUA + ban.getServer() + ChatColor.WHITE + ": " + ban.getReason());
            }
        }

        if (altAccounts.length > 0) {
            String tempList = "";
            int listCount = 0;
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.LIGHT_PURPLE + "Alternate Accounts:");
            for (final String user : altAccounts) {
                if (listCount == 0) {
                    tempList += user;
                } else {
                    tempList += ", " + user;
                }

                listCount++;

                if (listCount == 4) {
                    sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.AQUA + tempList);
                    listCount = 0;
                    tempList = "";
                }
            }
            if (tempList.length() > 0) {
                sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.AQUA + tempList);
            }
        }

        if (userData.isStaff()) {
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.WHITE + userData.getUsername() + ChatColor.AQUA + " is MCBlockIt Staff!");
        }

        return true;
    }
}
