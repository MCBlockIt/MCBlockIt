package it.mcblock.mcblockit.commands;

import it.mcblock.mcblockit.Utils;
import it.mcblock.mcblockit.api.MCBukkIt;

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
        MCBukkIt.ban(args[0], sender.getName(), reason);
        return true;
    }

}
