package org.welfaresystems.welfaresessentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.welfaresystems.welfaresessentials.Commands.GetWandCommand;
import org.welfaresystems.welfaresessentials.Listeners.SpellMenu;
import org.welfaresystems.welfaresessentials.Listeners.WandListener;
import org.welfaresystems.welfaresessentials.Managers.SpellManager;

public final class WelfareSEssentials extends JavaPlugin {

    private SpellManager spellManager;

    @Override
    public void onEnable() {
        spellManager = new SpellManager();
        getServer().getConsoleSender().sendMessage("§a===============================================");
        getServer().getConsoleSender().sendMessage("§a  Plugin: §6WelfareSEssentials");
        getServer().getConsoleSender().sendMessage("§a  Status: §aEnabled Successfully.");
        getServer().getConsoleSender().sendMessage("§a  Versión: §61.0.0");
        getServer().getConsoleSender().sendMessage("§a  Author: §bJonathan V.");
        getServer().getConsoleSender().sendMessage("§a===============================================");
        getCommand("getWand").setExecutor(new GetWandCommand());
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new SpellMenu(), this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§a===============================================");
        getServer().getConsoleSender().sendMessage("§a  Plugin: §6WelfareSEssentials");
        getServer().getConsoleSender().sendMessage("§a  Status: §aDisabled Successfully.");
        getServer().getConsoleSender().sendMessage("§a  Versión: §61.0.0");
        getServer().getConsoleSender().sendMessage("§a  Author: §bJonathan V.");
        getServer().getConsoleSender().sendMessage("§a===============================================");
    }

    public SpellManager getSpellManager() {
        return spellManager;
    }

    public static WelfareSEssentials getInstance() {
        return getPlugin(WelfareSEssentials.class);
    }
}
