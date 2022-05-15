package com.mercator.environmentalmechanics.commands;

import com.mercator.environmentalmechanics.greenhouseengine.WarmingEffects;
import org.apache.commons.math3.util.Precision;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTemperature implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        try {
            double temperature = WarmingEffects.getTemperature();
            sender.sendMessage(ChatColor.YELLOW + "The current temperature is " + Precision.round(temperature, 2) + ".");
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
