package org.welfaresystems.welfaresessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.welfaresystems.welfaresessentials.Utils.Keys;

import java.util.Arrays;


public class GetWandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lOnly players can use this command");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("getWand.wand")) {
            player.sendMessage("You do not have permission to execute this command");
            return true;
        }

        ItemStack wand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = wand.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Administrator Wand");
        meta.setLore(Arrays.asList(
                ChatColor.GOLD + "The powerful Administrator Wand",
                ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to switch between spells"
        ));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.UNBREAKING, 999, true);
        meta.getPersistentDataContainer().set(Keys.CUSTOM_WAND, PersistentDataType.BOOLEAN, true);

        wand.setItemMeta(meta);

        player.getInventory().addItem(wand);
        return true;
    }
}
