package it.mcblock.mcblockit.bukkit.command;

import it.mcblock.mcblockit.api.Utils;
import it.mcblock.mcblockit.bukkit.BukkitBlockItAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 2) {
            return false;
        }
        final String reason = Utils.combineSplit(args, " ", 1, args.length - 1);
        BukkitBlockItAPI.ban(args[0], sender.getName(), reason);
        return true;
    }

}
