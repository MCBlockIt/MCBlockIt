package it.mcblock.mcblockit.bukkit.command;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.api.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return false;
        }
        final String reason = Utils.combineSplit(args, " ", 1, args.length - 1);
        BanType type;
        if (label.equals("BAN")) {
            type = BanType.GLOBAL;
        } else {
            type = BanType.LOCAL;
        }
        MCBlockItAPI.ban(args[0], sender.getName(), type, reason);
        return true;
    }

}
