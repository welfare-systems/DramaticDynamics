package org.welfaresystems.dramaticdynamics.Commands.Alarms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayAlarm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please, provide a type. Usage: /playalarm <type>");
            return false;
        }

        String type = args[0];
        Sound sound = Sound.MUSIC_DISC_PRECIPICE;

        List<Location> locations = new ArrayList<>();
        List<Map<?, ?>> locs = DramaticDynamics.getInstance().getConfig().getMapList("AlarmLocations");

        for (Map<?, ?> loc : locs) {
            String worldName = (String) loc.get("world");
            int x = (int) loc.get("x");
            int y = (int) loc.get("y");
            int z = (int) loc.get("z");
            Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
            locations.add(location);
        }

        for (Location loc : locations) {
            loc.getWorld().playSound(loc, sound, 10.0f, 1.0f);
        }

        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + " Esto no es un simulacro. Alerta " + type + ". Busque refugio inmediatamente.");
        return true;
    }
}
