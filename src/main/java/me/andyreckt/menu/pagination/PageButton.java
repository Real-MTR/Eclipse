package me.andyreckt.menu.pagination;

import me.andyreckt.menu.Button;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/PageButton.class */
public class PageButton extends Button {
    private final int mod;
    private final PaginatedMenu menu;

    public PageButton(int mod, PaginatedMenu menu) {
        this.mod = mod;
        this.menu = menu;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder builder = new ItemBuilder(Material.ARROW).name(ChatColor.translateAlternateColorCodes('&', this.mod > 0 ? "&8» &7Next Page" : "&8« &7Previous Page"));
        return hasNext(player) ? builder.build() : builder.lore("&cNo more pages.").build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        if (hasNext(player)) {
            this.menu.modPage(player, this.mod);
            Button.playNeutral(player);
            player.updateInventory();
        }
    }

    public boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }
}
