package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/Glass.class */
public class Glass extends Button {
    private int data;

    public Glass() {
        this.data = 7;
    }

    public Glass(int data) {
        this.data = data;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) this.data).name(StringUtils.SPACE);
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
    }
}
