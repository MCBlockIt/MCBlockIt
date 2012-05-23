package it.mcblock.mcblockit.bukkit.command;

import it.mcblock.mcblockit.api.BanType;
import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.api.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Ban command
 * 
 * @author Matt Baxter
 * 
 *         Copyright 2012 Matt Baxter
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 * 
 */
public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return false;
        }
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (label.equalsIgnoreCase("tb") || label.equalsIgnoreCase("tempban")) {
            if(!MCBlockItAPI.tempBan(args[0], player == null ? "[CONSOLE]" : player.getName(), args[1])) return false;
        } else {
            final String reason = Utils.combineSplit(args, " ", 1, args.length - 1);
            BanType type;
            if (label.equalsIgnoreCase("gban") || label.equalsIgnoreCase("gb")) {
                type = BanType.GLOBAL;
            } else {
                type = BanType.LOCAL;
            }
            MCBlockItAPI.ban(args[0], player == null ? "[CONSOLE]" : player.getName(), type, reason);
        }
        return true;
    }

}
