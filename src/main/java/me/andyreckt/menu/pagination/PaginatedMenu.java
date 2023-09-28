package me.andyreckt.menu.pagination;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import me.andyreckt.menu.Button;
import me.andyreckt.menu.Menu;
import me.andyreckt.menu.buttons.BackButton;
import me.andyreckt.menu.buttons.Glass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/PaginatedMenu.class */
public abstract class PaginatedMenu extends Menu {
    private int page = 1;
    private int glassColor;

    public abstract String getPrePaginatedTitle(Player player);

    public abstract Map<Integer, Button> getAllPagesButtons(Player player);

    public PaginatedMenu() {
        setUpdateAfterClick(false);
    }

    public int getGlassColor() {
        return this.glassColor;
    }

    public void setGlassColor(int glassColor) {
        this.glassColor = glassColor;
    }

    @Override // me.andyreckt.menu.Menu
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('&', getPrePaginatedTitle(player) + " &8(&7" + this.page + "/" + getPages(player) + "&8)");
    }

    public final void modPage(Player player, int mod) {
        this.page += mod;
        getButtons().clear();
        openMenu(player);
    }

    public final int getPages(Player player) {
        int buttonAmount = getAllPagesButtons(player).size();
        if (buttonAmount == 0) {
            return 1;
        }
        return (int) Math.ceil(buttonAmount / getMaxItemsPerPage(player));
    }

    @Override // me.andyreckt.menu.Menu
    public final Map<Integer, Button> getButtons(Player player) {
        int[] iArr;
        int minIndex = (int) ((this.page - 1) * getMaxItemsPerPage(player));
        int maxIndex = (int) (this.page * getMaxItemsPerPage(player));
        HashMap<Integer, Button> buttons = new HashMap<>();
        for (int i : new int[]{0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44}) {
            buttons.put(Integer.valueOf(i), new Glass(getGlassColor()));
        }
        PageButton prevPage = new PageButton(-1, this);
        PageButton nextPage = new PageButton(1, this);
        if (prevPage.hasNext(player)) {
            buttons.put(39, prevPage);
        }
        if (nextPage.hasNext(player)) {
            buttons.put(41, nextPage);
        }
        for (Map.Entry<Integer, Button> entry : getAllPagesButtons(player).entrySet()) {
            int ind = entry.getKey().intValue();
            if (ind >= minIndex && ind < maxIndex) {
                int ind2 = ind - ((this.page - 1) * 21);
                switch (ind2 / 7) {
                    case 0:
                        ind2 += 10;
                        break;
                    case 1:
                        ind2 += 12;
                        break;
                    case 2:
                        ind2 += 14;
                        break;
                }
                buttons.put(Integer.valueOf(ind2), entry.getValue());
            }
        }
        Map<Integer, Button> global = getGlobalButtons(player);
        if (global != null) {
            buttons.putAll(global);
        }
        if (backButton() != null) {
            buttons.put(40, new BackButton(backButton()));
        }
        return buttons;
    }

    public int getMaxItemsPerPage(Player player) {
        return 21;
    }

    public Map<Integer, Button> getGlobalButtons(Player player) {
        return null;
    }

    public Menu backButton() {
        return null;
    }

    @Override // me.andyreckt.menu.Menu
    protected void surroundButtons(boolean full, Map<Integer, Button> buttons, ItemStack itemStack) {
        IntStream.range(0, getSize()).filter(slot -> {
            return buttons.get(Integer.valueOf(slot)) == null;
        }).forEach(slot2 -> {
            if (slot2 >= 9 && slot2 <= getSize() - 10) {
                if (!full) {
                    return;
                }
                if (slot2 % 9 != 0 && (slot2 + 1) % 9 != 0) {
                    return;
                }
            }
            buttons.put(Integer.valueOf(slot2), new Button() { // from class: me.andyreckt.menu.pagination.PaginatedMenu.1
                @Override // me.andyreckt.menu.Button
                public ItemStack getButtonItem(Player player) {
                    return itemStack;
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {}
            });
        });
    }

    public int getPage() {
        return this.page;
    }
}
