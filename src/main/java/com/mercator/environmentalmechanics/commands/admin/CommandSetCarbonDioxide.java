package com.mercator.environmentalmechanics.commands.admin;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandSetCarbonDioxide implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        if (p.hasPermission("envmechanics.set")) {
            try {
                File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
                PluginDataInterpreter.write(carbonDioxideValueF, Double.parseDouble(args[0]), "globalwarming");

                sender.sendMessage(ChatColor.GREEN + "Successfully changed the carbon dioxide level to " + args[0] + "!");

                triggered = true;
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Error processing the command! Either the syntax is incorrect, or something went wrong internally!");
                e.printStackTrace();
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");

            triggered = true;
        }

        return triggered;
    }
}
