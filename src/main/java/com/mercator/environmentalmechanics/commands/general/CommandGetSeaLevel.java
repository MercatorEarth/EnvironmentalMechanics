package com.mercator.environmentalmechanics.commands.general;

import com.mercator.environmentalmechanics.climateeffects.EffectsEngine;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGetSeaLevel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        boolean triggered = false;

        if (p.hasPermission("envmechanics.get")) {

            EffectsEngine effectsEngine = new EffectsEngine();

            try {
                int seaLevel = effectsEngine.raisedSeaLevelModule.getSeaLevel();
                sender.sendMessage(ChatColor.YELLOW + "The current sea level is " + seaLevel + ".");
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
