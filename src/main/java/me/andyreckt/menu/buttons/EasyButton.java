package me.andyreckt.menu.buttons;

import java.util.function.Consumer;
import me.andyreckt.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/EasyButton.class */
public class EasyButton extends Button {
    final ItemStack itemStack;
    final Consumer<Player> consumer;
    final boolean update;

    public EasyButton(ItemStack itemStack, Consumer<Player> consumer, boolean update) {
        this.itemStack = itemStack;
        this.consumer = consumer;
        this.update = update;
    }

    public EasyButton(ItemStack itemStack, Consumer<Player> consumer) {
        this.itemStack = itemStack;
        this.consumer = consumer;
        this.update = false;
    }

    @Override // me.andyreckt.menu.Button
    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return this.update;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player p0) {
        return this.itemStack;
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        this.consumer.accept(player);
    }
}
