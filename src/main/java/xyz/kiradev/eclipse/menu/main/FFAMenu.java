package xyz.kiradev.eclipse.menu.main;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import me.andyreckt.menu.Button;
import me.andyreckt.menu.buttons.Glass;
import me.andyreckt.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FFAMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "&bFFA Menu";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();
        buttons.put(13, new JoinFFAButton());
        for(int i = 0; i < 27; i++) {
            if(buttons.get(i) == null) buttons.put(i, new Glass());
        }
        return buttons;
    }
}