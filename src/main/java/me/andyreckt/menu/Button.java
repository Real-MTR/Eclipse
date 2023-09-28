package me.andyreckt.menu;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/Button.class */
public abstract class Button {
    public abstract ItemStack getButtonItem(Player player);

    public static Button placeholder(final Material material, final byte data, final String... title) {
        return new Button() { // from class: me.andyreckt.menu.Button.1
            @Override // me.andyreckt.menu.Button
            public ItemStack getButtonItem(Player player) {
                ItemStack it = new ItemStack(material, 1, data);
                ItemMeta meta = it.getItemMeta();
                meta.setDisplayName(StringUtils.join(title));
                it.setItemMeta(meta);
                return it;
            }
        };
    }

    public static void playFail(Player player) {
        player.playSound(player.getLocation(), Sound.DIG_GRASS, 20.0f, 0.1f);
    }

    public static void playSuccess(Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0f, 15.0f);
    }

    public static void playNeutral(Player player) {
        player.playSound(player.getLocation(), Sound.CLICK, 20.0f, 1.0f);
    }

    public static void playSound(Player player, ButtonSound buttonSound) {
        player.playSound(player.getLocation(), buttonSound.getSound(), 20.0f, 1.0f);
    }

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }
}
