package com.mercator.environmentalmechanics.commands.general;

import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandGetCarbonDioxide implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        if (p.hasPermission("envmechanics.get")) {
            try {
                File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
                Double carbonDioxideValue = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));

                sender.sendMessage(ChatColor.YELLOW + "The current carbon dioxide level is " + Math.round(carbonDioxideValue) + ".");

                triggered = true;
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "An internal error has occurred!");

                triggered = false;
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");

            triggered = true;
        }

        return triggered;
    }
}
