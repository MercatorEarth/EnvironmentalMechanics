package com.mercator.environmentalmechanics.climate.commands;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
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

        try {
            File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
            String carbonDioxideValueR = PluginDataInterpreter.read(carbonDioxideValueF);

            sender.sendMessage(ChatColor.YELLOW + "The current carbon dioxide level is " + carbonDioxideValueR + ".");

            triggered = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "An internal error has occurred!");

            triggered = false;
        }

        return triggered;
    }
}
