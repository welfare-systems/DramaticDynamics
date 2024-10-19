package org.welfaresystems.dramaticdynamics.Commands.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DramaticDynamics.getInstance().reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Configuration files reloaded successfully.");
        return true;
    }
}
