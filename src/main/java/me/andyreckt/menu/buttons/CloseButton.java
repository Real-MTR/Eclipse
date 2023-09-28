package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/CloseButton.class */
public class CloseButton extends Button {
    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.INK_SACK).durability((short) 1).name(ChatColor.translateAlternateColorCodes('&', "&cClose"));
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        Button.playNeutral(player);
        player.closeInventory();
    }
}
