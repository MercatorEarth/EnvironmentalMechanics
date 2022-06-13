package com.mercator.environmentalmechanics.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPollution implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Block reference = p.getLocation().getBlock();

        boolean triggered = false;

        if (p.hasPermission("envmechanics.getraw")) {
            try {
                boolean polluted = reference.hasMetadata("polluted");
                boolean softPolluted = reference.hasMetadata("softpolluted");

                if (polluted) {
                    sender.sendMessage(ChatColor.YELLOW + "The block you are in is currently polluted.");
                }
                else if (softPolluted) {
                    sender.sendMessage(ChatColor.YELLOW + "The block you are in is currently soft-polluted.");
                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "The block you are in is currently unpolluted.");
                }

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
