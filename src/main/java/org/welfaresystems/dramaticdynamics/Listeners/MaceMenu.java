package org.welfaresystems.dramaticdynamics.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;
import org.welfaresystems.dramaticdynamics.Managers.MaceManager;
import org.welfaresystems.dramaticdynamics.Managers.MenuManager;

public class MaceMenu extends MenuManager {
    public MaceMenu() {
        super("Select an Action", 9);
    }

    @Override
    protected void setup() {
        addItemToMenu("§bBan", Material.BARRIER);
        addItemToMenu("§bKick", Material.COMMAND_BLOCK);
        addItemToMenu("§bWarn", Material.REPEATING_COMMAND_BLOCK);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().equals(this.menu)) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                String actionName = event.getCurrentItem().getItemMeta().getDisplayName();
                MaceManager maceManager = DramaticDynamics.getInstance().getMaceManager();
                switch (actionName) {
                    case "§bBan":
                        maceManager.clearSelectedAction(player);
                        maceManager.setSelectedAction(player, "Ban");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Ban action selected");
                        break;
                    case "§bKick":
                        maceManager.clearSelectedAction(player);
                        maceManager.setSelectedAction(player, "Kick");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Kick action selected");
                        break;
                    case "§bWarn":
                        maceManager.clearSelectedAction(player);
                        maceManager.setSelectedAction(player, "Warn");
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Warn action selected");
                        break;
                    default:
                        break;
                }
                player.closeInventory();
            }
        }
    }
}
