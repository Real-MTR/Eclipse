package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import me.andyreckt.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/BackButton.class */
public class BackButton extends Button {
    private final Menu back;

    public BackButton(Menu back) {
        this.back = back;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.ARROW).name(ChatColor.translateAlternateColorCodes('&', "&8« &7Go back"));
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        Button.playNeutral(player);
        this.back.openMenu(player);
    }
}
