package xyz.kiradev.eclipse.kit.menu;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import me.andyreckt.menu.Button;
import me.andyreckt.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class KitsMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player player) {
        return "&bKits Selector Menu";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();
        return buttons;
    }
}
