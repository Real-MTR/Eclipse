package me.andyreckt.menu.pagination;

import me.andyreckt.menu.Button;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/JumpToPageButton.class */
public class JumpToPageButton extends Button {
    private final int page;
    private final PaginatedMenu menu;
    private final boolean current;

    public JumpToPageButton(int page, PaginatedMenu menu, boolean current) {
        this.page = page;
        this.menu = menu;
        this.current = current;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(this.current ? Material.EMPTY_MAP : Material.PAPER).amount(this.page);
        item.name(ChatColor.translateAlternateColorCodes('&', "&7Page " + this.page));
        if (this.current) {
            item.lore("&7Current Page");
        }
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        this.menu.modPage(player, this.page - this.menu.getPage());
        Button.playNeutral(player);
    }
}
