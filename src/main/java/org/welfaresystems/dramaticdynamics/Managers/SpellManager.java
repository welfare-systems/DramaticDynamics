package org.welfaresystems.dramaticdynamics.Managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SpellManager {
    private final Map<Player, String> selectedSpells = new HashMap<>();

    public void setSelectedSpell(Player player, String spell) {
        selectedSpells.put(player, spell);
    }

    public String getSelectedSpell(Player player) {
        return selectedSpells.get(player);
    }

    public void clearSelectedSpell(Player player) {
        selectedSpells.remove(player);
    }
}
