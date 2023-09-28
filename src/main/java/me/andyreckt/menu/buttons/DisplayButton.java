package me.andyreckt.menu.buttons;

import me.andyreckt.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/DisplayButton.class */
public class DisplayButton extends Button {
    private ItemStack itemStack;
    private boolean cancel;

    public DisplayButton(ItemStack itemStack, boolean cancel) {
        this.itemStack = itemStack;
        this.cancel = cancel;
    }

    public DisplayButton(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.cancel = true;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player player) {
        if (this.itemStack == null) {
            return new ItemStack(Material.AIR);
        }
        return this.itemStack;
    }

    @Override // me.andyreckt.menu.Button
    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return this.cancel;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public boolean isCancel() {
        return this.cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
