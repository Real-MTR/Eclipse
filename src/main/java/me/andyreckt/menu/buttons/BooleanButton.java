package me.andyreckt.menu.buttons;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import me.andyreckt.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/buttons/BooleanButton.class */
public final class BooleanButton<T> extends Button {
    private ItemStack item;
    private final T target;
    private final String trait;
    private final BiConsumer<T, Boolean> writeFunction;
    private final Function<T, Boolean> readFunction;
    private final Consumer<T> saveFunction;

    public BooleanButton(ItemStack item, T target, String trait, BiConsumer<T, Boolean> writeFunction, Function<T, Boolean> readFunction) {
        this(item, target, trait, writeFunction, readFunction, i -> {
        });
    }

    public BooleanButton(T target, String trait, BiConsumer<T, Boolean> writeFunction, Function<T, Boolean> readFunction) {
        this(target, trait, writeFunction, readFunction, i -> {
        });
    }

    public BooleanButton(T target, String trait, BiConsumer<T, Boolean> toDo, Function<T, Boolean> getFrom, Consumer<T> saveFunction) {
        this.target = target;
        this.trait = trait;
        this.writeFunction = toDo;
        this.readFunction = getFrom;
        this.saveFunction = saveFunction;
    }

    public BooleanButton(ItemStack item, T target, String trait, BiConsumer<T, Boolean> toDo, Function<T, Boolean> getFrom, Consumer<T> saveFunction) {
        this.item = item;
        this.target = target;
        this.trait = trait;
        this.writeFunction = toDo;
        this.readFunction = getFrom;
        this.saveFunction = saveFunction;
    }

    @Override // me.andyreckt.menu.Button
    public ItemStack getButtonItem(Player p0) {
        boolean current = this.readFunction.apply(this.target).booleanValue();
        if (this.item == null) {
            return new ItemBuilder(Material.WOOL).name((this.readFunction.apply(this.target).booleanValue() ? "&a" : "&c") + this.trait).durability((short) (this.readFunction.apply(this.target).booleanValue() ? 5 : 14)).build();
        }
        ItemBuilder itemBuilder = new ItemBuilder(this.item);
        String[] strArr = new String[3];
        strArr[0] = "";
        strArr[1] = current ? "&aEnabled" : "&7Enabled";
        strArr[2] = !current ? "&cDisabled" : "&7Disabled";
        return itemBuilder.lore(strArr).build();
    }

    private String yesNo(boolean apply) {
        return apply ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No";
    }

    @Override // me.andyreckt.menu.Button
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        boolean current = this.readFunction.apply(this.target).booleanValue();
        this.writeFunction.accept(this.target, Boolean.valueOf(!current));
        this.saveFunction.accept(this.target);
    }
}
