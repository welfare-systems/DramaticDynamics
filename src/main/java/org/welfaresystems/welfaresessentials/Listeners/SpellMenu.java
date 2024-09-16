package org.welfaresystems.welfaresessentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.welfaresystems.welfaresessentials.Managers.SpellManager;
import org.welfaresystems.welfaresessentials.WelfareSEssentials;

public class SpellMenu implements Listener {
    private final Inventory menu = Bukkit.createInventory(null, 9, "Select a spell");

    public SpellMenu() {
        addItemToMenu("§bLightning Spell", Material.DIAMOND);
        addItemToMenu("§bFireball Spell", Material.FIRE_CHARGE);
        addItemToMenu("§bHealing Spell", Material.GOLDEN_APPLE);
        addItemToMenu("§bExplosion Spell", Material.TNT);
        addItemToMenu("§bTeleportation Spell", Material.ENDER_PEARL);
        WelfareSEssentials.getInstance().getServer().getPluginManager().registerEvents(this, WelfareSEssentials.getInstance());
    }

    public void open(Player player) {
        player.openInventory(this.menu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().equals(menu)) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                String spellName = event.getCurrentItem().getItemMeta().getDisplayName();
                SpellManager spellManager = WelfareSEssentials.getInstance().getSpellManager();
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

    private void addItemToMenu(String name, Material material) {
        ItemStack spellItem = new ItemStack(material);
        ItemMeta meta = spellItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.addEnchant(Enchantment.UNBREAKING, 999, true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            spellItem.setItemMeta(meta);
        }
        menu.addItem(spellItem);
    }
}
