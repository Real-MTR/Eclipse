package me.andyreckt.menu;

import java.util.HashMap;
import java.util.Map;
import me.andyreckt.menu.buttons.BackButton;
import me.andyreckt.menu.buttons.Glass;
import org.bukkit.entity.Player;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/GlassMenu.class */
public abstract class GlassMenu extends Menu {
    public abstract int getGlassColor();

    public abstract Map<Integer, Button> getAllButtons(Player player);

    @Override // me.andyreckt.menu.Menu
    public Map<Integer, Button> getButtons(Player paramPlayer) {
        int[] iArr;
        HashMap<Integer, Button> buttons = new HashMap<>(getAllButtons(paramPlayer));
        int size = s(buttons) + 9;
        for (int i : new int[]{0, 1, 7, 8, 9, 17, size - 18, size - 10, size - 9, size - 8, size - 2, size - 1}) {
            buttons.put(Integer.valueOf(i), new Glass(getGlassColor()));
        }
        if (backButton() != null) {
            buttons.put(Integer.valueOf(size - 5), new BackButton(backButton()));
        }
        return buttons;
    }

    private int s(Map<Integer, Button> buttons) {
        int x = 0;
        for (Integer num : buttons.keySet()) {
            int i = num.intValue();
            if (i > x) {
                x = i;
            }
        }
        return ((x / 9) + 1) * 9;
    }

    public Menu backButton() {
        return null;
    }
}
