package com.mercator.environmentalmechanics.commands.admin;

import com.mercator.environmentalmechanics.climateengine.ClimateEngine;
import org.apache.commons.math3.util.Precision;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRawTemperature implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        ClimateEngine climateEngine = new ClimateEngine();

        try {
            double temperature = climateEngine.rawTemperature(p.getLocation());
            sender.sendMessage(ChatColor.YELLOW + "The current raw temperature at your location is " + Precision.round(temperature, 2) + ".");
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
