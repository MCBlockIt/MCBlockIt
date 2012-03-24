package it.mcblock.mcblockit.commands;

import it.mcblock.mcblockit.api.MCBlockItAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 1) {
            return false;
        }
        MCBlockItAPI.unban(args[0]);
        return true;
    }

}
