package xyz.kiradev.eclipse.util;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import lombok.Getter;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum Items {
    JOIN_FFA_ITEM(Material.DIAMOND_SWORD, "&eJoin FFA", "&7Click to play FFA");

    private final ItemStack item;
    private final Material material;
    private final String name;
    private final String[] lore;

    Items(Material material, String name, String... lore) {
        this.material = material;
        this.name = name;
        this.lore = lore;

        this.item = new ItemBuilder(material).name(name).lore(lore).build();
    }
}
