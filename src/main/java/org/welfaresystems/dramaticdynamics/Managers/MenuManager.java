package org.welfaresystems.dramaticdynamics.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.welfaresystems.dramaticdynamics.DramaticDynamics;

public abstract class MenuManager implements Listener {
    public final Inventory menu;

    public MenuManager(String title, Integer space) {
        this.menu = Bukkit.createInventory(null, space, title);
        this.setup();
        DramaticDynamics.getInstance().getServer().getPluginManager().registerEvents(this, DramaticDynamics.getInstance());
    }

    //Here is expected to have addItemToMenu method
    protected abstract void setup();

    public void addItemToMenu(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.addEnchant(Enchantment.UNBREAKING, 999, true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        menu.addItem(item);
    }

    public void open(Player player) {
        player.openInventory(this.menu);
    }
}
