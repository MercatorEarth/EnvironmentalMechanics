package com.mercator.environmentalmechanics.commands;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandGetMethane implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        try {
            File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
            Double methaneValue = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));

            sender.sendMessage(ChatColor.YELLOW + "The current methane level is " + Math.round(methaneValue) + ".");

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
