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

        UserData userData = MCBlockItAPI.getUserData(args[0]);
        sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.WHITE + "User data for: " + ChatColor.AQUA + userData.getUsername());
        sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.DARK_RED + userData.getBans().length + ChatColor.WHITE + " ban(s), " + ChatColor.YELLOW + userData.getReputation() + ChatColor.WHITE + " reputation");
        BanData[] banData = userData.getBans();
        for (BanData ban : banData) {
            sender.sendMessage(ChatColor.RED + "[MCBlockIt] " + ChatColor.AQUA + ban.getServer() + ChatColor.WHITE + ": " + ban.getReason());
        }

        return true;
    }
}
