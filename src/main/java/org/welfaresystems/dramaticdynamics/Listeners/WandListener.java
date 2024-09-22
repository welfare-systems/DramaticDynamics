package org.welfaresystems.dramaticdynamics.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;
import org.welfaresystems.dramaticdynamics.Managers.SpellManager;
import org.welfaresystems.dramaticdynamics.Utils.Keys;

public class WandListener implements Listener {

    @EventHandler
    public void onPlayerUseWand(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        SpellManager spellManager = DramaticDynamics.getInstance().getSpellManager();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item.getType() == Material.BLAZE_ROD && item.hasItemMeta()) {
                if (item.getItemMeta().getPersistentDataContainer().has(Keys.CUSTOM_WAND, PersistentDataType.BOOLEAN)) {
                    openSpellMenu(player);
                }
            }
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (item.getType() == Material.BLAZE_ROD && item.hasItemMeta()) {
                if (item.getItemMeta().getPersistentDataContainer().has(Keys.CUSTOM_WAND, PersistentDataType.BOOLEAN)) {
                    String selectedSpell = spellManager.getSelectedSpell(player);

                    //Lightning Spell
                    if ("Lightning_Spell".equals(selectedSpell)) {
                        Location targetLocation = player.getTargetBlock(null, 200).getLocation();
                        player.getWorld().strikeLightning(targetLocation);
                    }

                    //Fireball
                    if ("Fireball_Spell".equals(selectedSpell)) {
                        Location targetLocation = player.getTargetBlock(null, 200).getLocation();
                        Vector direction = targetLocation.toVector().subtract(player.getLocation().toVector()).normalize();
                        Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getLocation().add(direction.multiply(2)), EntityType.FIREBALL);
                        fireball.setShooter(player);
                        fireball.setDirection(direction);
                    }

                    //Healing Spell
                    if ("Healing_Spell".equals(selectedSpell)) {
                        player.setHealth(player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue());
                        player.setFoodLevel(20);
                        Location location = player.getLocation();
                        for (int i = 0; i < 100; i++) {
                            location.getWorld().spawnParticle(Particle.HEART, location, 1, 0.5, 0.5, 0.5, 0);
                        }
                    }

                    //Explosion Spell
                    if ("Explosion_Spell".equals(selectedSpell)) {
                        Location targetLocation = player.getTargetBlock(null, 200).getLocation();
                        player.getWorld().createExplosion(targetLocation, 4.0F, false, false);
                    }

                    //Teleportation Spell
                    if ("Teleportation_Spell".equals(selectedSpell)) {
                        Location targetLocation = player.getTargetBlock(null, 200).getLocation();
                        targetLocation.setY(targetLocation.getY() + 1);
                        player.teleport(targetLocation);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    }
                }
            }
        }

    }

    public void openSpellMenu(Player player) {
        SpellMenu menu = new SpellMenu();
        menu.open(player);
    }
}


