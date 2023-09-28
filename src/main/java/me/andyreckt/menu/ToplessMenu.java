package me.andyreckt.menu;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/ToplessMenu.class */
public abstract class ToplessMenu extends Menu {
    public abstract Map<Integer, Button> getAllButtons(Player player);

    @Override // me.andyreckt.menu.Menu
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (Map.Entry<Integer, Button> entry : getAllButtons(player).entrySet()) {
            int index = entry.getKey().intValue() + 8;
            if (index > 8) {
                buttons.put(Integer.valueOf(index), entry.getValue());
            }
        }
        Map<Integer, Button> topLevel = getTopLevelButtons(player);
        if (topLevel != null) {
            for (Map.Entry<Integer, Button> entry2 : topLevel.entrySet()) {
                int index2 = entry2.getKey().intValue();
                if (index2 <= 8) {
                    buttons.put(Integer.valueOf(index2), entry2.getValue());
                }
            }
        }
        return buttons;
    }

    public Map<Integer, Button> getTopLevelButtons(Player player) {
        return null;
    }
}
