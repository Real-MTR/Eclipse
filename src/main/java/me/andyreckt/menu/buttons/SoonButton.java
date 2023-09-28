package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/SoonButton.class */
public class SoonButton extends Button {
    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player p0) {
        return new ItemBuilder(Material.BARRIER).name("&7&lSOON").lore("&7This feature is coming soon!").build();
    }
}
