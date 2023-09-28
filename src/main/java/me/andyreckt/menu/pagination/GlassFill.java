package me.andyreckt.menu.pagination;

import me.andyreckt.menu.Button;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/pagination/GlassFill.class */
public class GlassFill extends Button {
    private final PaginatedMenu menu;

    public GlassFill(PaginatedMenu menu) {
        this.menu = menu;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.STAINED_GLASS_PANE);
        item.durability((short) 15);
        item.name(StringUtils.SPACE);
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {}
}
