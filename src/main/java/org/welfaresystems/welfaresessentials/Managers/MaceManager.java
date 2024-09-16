package org.welfaresystems.welfaresessentials.Managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MaceManager {
    private final Map<Player, String> selectedAction = new HashMap<>();

    public void setSelectedAction(Player player, String action) {
        selectedAction.put(player, action);
    }

    public String getSelectedAction(Player player) {
        return selectedAction.get(player);
    }

    public void clearSelectedAction(Player player) {
        selectedAction.remove(player);
    }
}
