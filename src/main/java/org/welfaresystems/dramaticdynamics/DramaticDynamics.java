package org.welfaresystems.dramaticdynamics;

import org.bukkit.plugin.java.JavaPlugin;
import org.welfaresystems.dramaticdynamics.Commands.GetMaceCommand;
import org.welfaresystems.dramaticdynamics.Commands.GetWandCommand;
import org.welfaresystems.dramaticdynamics.Listeners.MaceListener;
import org.welfaresystems.dramaticdynamics.Listeners.WandListener;
import org.welfaresystems.dramaticdynamics.Managers.MaceManager;
import org.welfaresystems.dramaticdynamics.Managers.SpellManager;

public final class DramaticDynamics extends JavaPlugin {

    private SpellManager spellManager;
    private MaceManager maceManager;

    @Override
    public void onEnable() {
        spellManager = new SpellManager();
        maceManager = new MaceManager();
        getServer().getConsoleSender().sendMessage("§a===============================================");
        getServer().getConsoleSender().sendMessage("§a  Plugin: §DramaticDynamics");
        getServer().getConsoleSender().sendMessage("§a  Status: §aEnabled Successfully.");
        getServer().getConsoleSender().sendMessage("§a  Versión: §61.0.0");
        getServer().getConsoleSender().sendMessage("§a  Author: §bJonathan V.");
        getServer().getConsoleSender().sendMessage("§a===============================================");

        //Commands
        getCommand("getWand").setExecutor(new GetWandCommand());
        getCommand("getMace").setExecutor(new GetMaceCommand());

        //Listeners
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new MaceListener(), this);
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

    public MaceManager getMaceManager() {
        return maceManager;
    }

    public static DramaticDynamics getInstance() {
        return getPlugin(DramaticDynamics.class);
    }
}
