package me.andyreckt.menu.buttons;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import me.andyreckt.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/NumberButton.class */
public final class NumberButton<T> extends Button {
    private final T target;
    private final Material material;
    private final String trait;
    private final String description;
    private final BiConsumer<T, Integer> writeFunction;
    private final Function<T, Integer> readFunction;
    private final Consumer<T> saveFunction;

    public NumberButton(T target, Material material, String trait, String description, BiConsumer<T, Integer> writeFunction, Function<T, Integer> readFunction) {
        this(target, material, trait, description, writeFunction, readFunction, i -> {
        });
    }

    public NumberButton(T target, Material material, String trait, String description, BiConsumer<T, Integer> writeFunction, Function<T, Integer> readFunction, Consumer<T> saveFunction) {
        this.target = target;
        this.trait = trait;
        this.material = material;
        this.description = description;
        this.writeFunction = writeFunction;
        this.readFunction = readFunction;
        this.saveFunction = saveFunction;
    }

    public String getName(Player player) {
        return this.trait;
    }

    public List<String> getDescription(Player player) {
        return ImmutableList.of(ChatColor.GRAY + this.description, "", ChatColor.AQUA + "Current: " + ChatColor.YELLOW + getAmount(player), "", ChatColor.GRAY + "Left click to increase.", ChatColor.GRAY + "Right click to decrease.", ChatColor.GRAY + "Shift click to change by 10.");
    }

    public Material getMaterial(Player player) {
        return this.material;
    }

    public int getAmount(Player player) {
        return this.readFunction.apply(this.target).intValue();
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        int current = this.readFunction.apply(this.target).intValue();
        int change = clickType.isShiftClick() ? 10 : 1;
        if (clickType.isRightClick()) {
            change = -change;
        }
        this.writeFunction.accept(this.target, Integer.valueOf(current + change));
        this.saveFunction.accept(this.target);
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player p0) {
        return new ItemBuilder(getMaterial(p0)).name(getName(p0)).lore(getDescription(p0)).amount(getAmount(p0)).build();
    }
}
