package it.mcblock.mcblockit.bukkit.command;

import it.mcblock.mcblockit.bukkit.BukkitBlockItAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 1) {
            return false;
        }
        BukkitBlockItAPI.unban(args[0]);
        return true;
    }

}
