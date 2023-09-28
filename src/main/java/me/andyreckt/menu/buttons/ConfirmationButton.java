package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import me.andyreckt.menu.TypeCallback;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/ConfirmationButton.class */
public class ConfirmationButton extends Button {
    private final boolean confirm;
    private final TypeCallback<Boolean> callback;
    private final boolean closeAfterResponse;

    public ConfirmationButton(boolean confirm, TypeCallback<Boolean> callback, boolean closeAfterResponse) {
        this.confirm = confirm;
        this.callback = callback;
        this.closeAfterResponse = closeAfterResponse;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.WOOL);
        item.durability((short) (this.confirm ? 5 : 14));
        item.name(ChatColor.translateAlternateColorCodes('*', this.confirm ? "&aConfirm" : "&cCancel"));
        return item.build();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        if (this.confirm) {
            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0f, 0.1f);
        } else {
            player.playSound(player.getLocation(), Sound.DIG_GRAVEL, 20.0f, 0.1f);
        }
        if (this.closeAfterResponse) {
            player.closeInventory();
        }
        this.callback.callback(Boolean.valueOf(this.confirm));
    }
}
