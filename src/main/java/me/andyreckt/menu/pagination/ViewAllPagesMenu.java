package me.andyreckt.menu.pagination;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import me.andyreckt.menu.Button;
import me.andyreckt.menu.Menu;
import me.andyreckt.menu.buttons.BackButton;
import org.bukkit.entity.Player;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/ViewAllPagesMenu.class */
public class ViewAllPagesMenu extends Menu {
    @NonNull
    PaginatedMenu menu;

    public ViewAllPagesMenu(@NonNull PaginatedMenu menu) {
        if (menu == null) {
            throw new NullPointerException("menu is marked non-null but is null");
        }
        this.menu = menu;
    }

    @Override // me.andyreckt.menu.Menu
    public String getTitle(Player player) {
        return "&cJump to page";
    }

    @Override // me.andyreckt.menu.Menu
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();
        buttons.put(0, new BackButton(this.menu));
        int index = 10;
        int i = 1;
        while (i <= this.menu.getPages(player)) {
            int i2 = index;
            index++;
            buttons.put(Integer.valueOf(i2), new JumpToPageButton(i, this.menu, this.menu.getPage() == i));
            if ((index - 8) % 9 == 0) {
                index += 2;
            }
            i++;
        }
        return buttons;
    }

    @Override // me.andyreckt.menu.Menu
    public boolean isAutoUpdate() {
        return false;
    }

    @NonNull
    public PaginatedMenu getMenu() {
        return this.menu;
    }
}
