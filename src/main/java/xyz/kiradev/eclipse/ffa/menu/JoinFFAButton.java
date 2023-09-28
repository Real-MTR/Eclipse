package xyz.kiradev.eclipse.ffa.menu;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import me.andyreckt.menu.Button;
import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import xyz.kiradev.eclipse.kit.menu.KitsMenu;

public class JoinFFAButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.IRON_SWORD)
                .name("&bJoin FFA")
                .lore("&7Click to join and play the FFA.")
                .flag(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS)
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new KitsMenu().openMenu(player);
    }
}