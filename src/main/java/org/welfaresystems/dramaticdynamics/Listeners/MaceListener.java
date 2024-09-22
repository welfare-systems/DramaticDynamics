package org.welfaresystems.dramaticdynamics.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;
import org.welfaresystems.dramaticdynamics.Managers.MaceManager;
import org.welfaresystems.dramaticdynamics.Utils.Keys;

import java.time.Duration;

public class MaceListener implements Listener {
    @EventHandler
    public void onPlayerUseMace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        MaceManager maceManager = DramaticDynamics.getInstance().getMaceManager();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item.getType() == Material.MACE && item.hasItemMeta()) {
                if (item.getItemMeta().getPersistentDataContainer().has(Keys.CUSTOM_MACE, PersistentDataType.BOOLEAN)) {
                    openMaceMenu(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerHitWithMace(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();
        Player target = (Player) event.getEntity();
        ItemStack item = attacker.getInventory().getItemInMainHand();
        MaceManager maceManager = DramaticDynamics.getInstance().getMaceManager();
        String selectedAction = maceManager.getSelectedAction(attacker);

        if (item.getType() == Material.MACE && item.hasItemMeta()) {
            if (item.getItemMeta().getPersistentDataContainer().has(Keys.CUSTOM_MACE, PersistentDataType.BOOLEAN)) {

                //Ban Action
                if ("Ban".equals(selectedAction)) {
                    target.getWorld().strikeLightning(target.getLocation());

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            target.setHealth(0);
                            target.ban(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You have been banned: You have suffered the wrath of the Administrator through The Mace of Justice", (Duration) null, attacker.getName(), true);
                            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + target.getName() + " has been banned by " + attacker.getName() + " using The Mace of Justice.");
                        }
                    }.runTaskLater(DramaticDynamics.getInstance(), 20L);
                }

                //Kick Action
                if ("Kick".equals(selectedAction)) {
                    target.getWorld().strikeLightning(target.getLocation());

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            target.setHealth(0);
                            target.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You have suffered the wrath of the Administrator through The Mace of Justice");
                            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + target.getName() + " has been kicked from this server by " + attacker.getName() + " using The Mace of Justice.");
                        }
                    }.runTaskLater(DramaticDynamics.getInstance(), 20L);
                }

                //Warn Action
                if ("Warn".equals(selectedAction)) {
                    target.sendTitle(ChatColor.RED + "WARNING!", "", 10, 70, 20);
                    target.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Watch your actions! An administrator of this server gave you a warning using The Mace of Justice on you!");

                    new BukkitRunnable() {
                        int ticks = 0;

                        @Override
                        public void run() {
                            if (!target.isOnline() || target.isDead() || ticks >= 200) {
                                cancel();
                                return;
                            }

                            Location targetLocation = target.getLocation();
                            target.getWorld().strikeLightning(targetLocation);

                            ticks += 10;
                        }
                    }.runTaskTimer(DramaticDynamics.getInstance(), 0, 10);

                    Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.ITALIC + target.getName() + " has been obliterated by " + attacker.getName() + " using The Mace of Justice.");
                }

            }
        }
    }

    public void openMaceMenu(Player player) {
        MaceMenu menu = new MaceMenu();
        menu.open(player);
    }
}
