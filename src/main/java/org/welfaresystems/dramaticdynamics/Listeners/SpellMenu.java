package org.welfaresystems.dramaticdynamics.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;
import org.welfaresystems.dramaticdynamics.Managers.MenuManager;
import org.welfaresystems.dramaticdynamics.Managers.SpellManager;

public class SpellMenu extends MenuManager {
    public SpellMenu() {
        super("Select a Spell", 9);
    }

    @Override
    protected void setup() {
        addItemToMenu("§bLightning Spell", Material.DIAMOND);
        addItemToMenu("§bFireball Spell", Material.FIRE_CHARGE);
        addItemToMenu("§bHealing Spell", Material.GOLDEN_APPLE);
        addItemToMenu("§bExplosion Spell", Material.TNT);
        addItemToMenu("§bTeleportation Spell", Material.ENDER_PEARL);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().equals(this.menu)) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                String spellName = event.getCurrentItem().getItemMeta().getDisplayName();
                SpellManager spellManager = DramaticDynamics.getInstance().getSpellManager();
                switch (spellName) {
                    case "§bFireball Spell":
                        spellManager.clearSelectedSpell(player);
                        spellManager.setSelectedSpell(player, "Fireball_Spell");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Fireball spell selected");
                        break;
                    case "§bLightning Spell":
                        spellManager.clearSelectedSpell(player);
                        spellManager.setSelectedSpell(player, "Lightning_Spell");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Lightning spell selected");
                        break;
                    case "§bHealing Spell":
                        spellManager.clearSelectedSpell(player);
                        spellManager.setSelectedSpell(player, "Healing_Spell");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Healing spell selected");
                        break;
                    case "§bExplosion Spell":
                        spellManager.clearSelectedSpell(player);
                        spellManager.setSelectedSpell(player, "Explosion_Spell");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Explosion spell selected");
                        break;
                    case "§bTeleportation Spell":
                        spellManager.clearSelectedSpell(player);
                        spellManager.setSelectedSpell(player, "Teleportation_Spell");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Teleportation spell selected");
                        break;
                    default:
                        break;
                }
                player.closeInventory();
            }
        }
    }
}
