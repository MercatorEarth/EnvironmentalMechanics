package com.mercator.environmentalmechanics.commands.general;

import com.mercator.environmentalmechanics.datamanagement.BorderIntegration;
import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import org.apache.commons.math3.util.Precision;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLatitude implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        if (p.hasPermission("envmechanics.get")) {
            ClimateEngine climateEngine = new ClimateEngine();
            BorderIntegration borderIntegration = climateEngine.borderIntegration;

            try {
                double latitude = borderIntegration.getLatitude(p.getLocation());
                sender.sendMessage(ChatColor.YELLOW + "The latitude at your location is " + Precision.round(latitude, 2) + " degrees.");
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
