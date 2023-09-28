package me.andyreckt.menu.pagination;

import java.util.HashMap;
import java.util.Map;
import me.andyreckt.menu.Button;
import me.andyreckt.menu.Menu;
import me.andyreckt.menu.buttons.BackButton;
import me.andyreckt.menu.buttons.DisplayButton;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/ConfirmationMenu.class */
public class ConfirmationMenu extends Menu {
    private final Runnable runnable;
    private final ItemStack is;
    private final Menu oldMenu;

    public ConfirmationMenu(Runnable runnable, ItemStack is, Menu oldMenu) {
        this.runnable = runnable;
        this.is = is;
        this.oldMenu = oldMenu;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public ItemStack getIs() {
        return this.is;
    }

    public Menu getOldMenu() {
        return this.oldMenu;
    }

    @Override // me.andyreckt.menu.Menu
    public String getTitle(Player paramPlayer) {
        return "Confirm";
    }

    @Override // me.andyreckt.menu.Menu
    public Map<Integer, Button> getButtons(Player paramPlayer) {
        int[] iArr;
        int[] iArr2;
        HashMap<Integer, Button> buttons = new HashMap<>();
        for (int i : new int[]{1, 2, 3, 10, 11, 12, 19, 20, 21}) {
            buttons.put(Integer.valueOf(i), new DisplayButton(new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 5).name(StringUtils.SPACE).build()));
        }
        buttons.put(13, new DisplayButton(this.is));
        for (int i2 : new int[]{5, 6, 7, 14, 15, 16, 23, 24, 25}) {
            buttons.put(Integer.valueOf(i2), new DisplayButton(new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 14).name(StringUtils.SPACE).build()));
        }
        buttons.put(11, new ConfirmationButton());
        buttons.put(15, new CancelButton());
        if (this.oldMenu != null) {
            buttons.put(22, new BackButton(this.oldMenu));
        }
        return buttons;
    }

    /* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/ConfirmationMenu$ConfirmationButton.class */
    public class ConfirmationButton extends Button {
        public ConfirmationButton() {
        }

        @Override // me.andyreckt.menu.Button
        public ItemStack getButtonItem(Player p0) {
            return new ItemBuilder(Material.SLIME_BALL).name("&a&lConfirm").build();
        }

        @Override // me.andyreckt.menu.Button
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            ConfirmationMenu.this.runnable.run();
            player.closeInventory();
        }
    }

    /* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/ConfirmationMenu$CancelButton.class */
    public class CancelButton extends Button {
        public CancelButton() {
        }

        @Override // me.andyreckt.menu.Button
        public ItemStack getButtonItem(Player p0) {
            return new ItemBuilder(Material.INK_SACK).durability((short) 1).name("&c&lCancel").build();
        }

        @Override // me.andyreckt.menu.Button
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            if (ConfirmationMenu.this.oldMenu != null) {
                ConfirmationMenu.this.oldMenu.openMenu(player);
            } else {
                player.closeInventory();
            }
        }
    }
}
