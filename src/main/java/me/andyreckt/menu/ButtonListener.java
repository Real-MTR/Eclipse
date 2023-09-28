package me.andyreckt.menu;

import java.util.Objects;
import me.andyreckt.menu.pagination.PaginatedMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.kiradev.eclipse.Eclipse;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/ButtonListener.class */
public class ButtonListener implements Listener {
    private final Plugin plugin;

    public ButtonListener(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void onInventoryClose(Player player) {
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getUniqueId());
        if (openMenu == null) {
            return;
        }
        openMenu.setInventory(player.getOpenInventory().getTopInventory());
        openMenu.onClose(player);
        Menu.currentlyOpenedMenus.remove(player.getUniqueId());
        if (openMenu instanceof PaginatedMenu) {
            return;
        }
        player.setMetadata("scanglitch", new FixedMetadataValue(Eclipse.getInstance(), true));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getUniqueId());
        if (openMenu == null) {
            return;
        }
        openMenu.setInventory(event.getInventory());
        openMenu.onClose(player);
        Menu.currentlyOpenedMenus.remove(player.getUniqueId());
        player.setMetadata("scanglitch", new FixedMetadataValue(this.plugin, true));
    }

    @EventHandler
    public void onButtonPress(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getUniqueId());
        if (openMenu != null) {
            if (event.getSlot() != event.getRawSlot()) {
                if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
                    event.setCancelled(true);
                }
            } else if (openMenu.getButtons().containsKey(Integer.valueOf(event.getSlot()))) {
                Button button = openMenu.getButtons().get(Integer.valueOf(event.getSlot()));
                boolean cancel = button.shouldCancel(player, event.getSlot(), event.getClick());
                if (!cancel && (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                    if (event.getCurrentItem() != null) {
                        player.getInventory().addItem(new ItemStack[]{event.getCurrentItem()});
                    }
                } else {
                    event.setCancelled(cancel);
                }
                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());
                if (Menu.currentlyOpenedMenus.containsKey(player.getUniqueId())) {
                    Menu newMenu = Menu.currentlyOpenedMenus.get(player.getUniqueId());
                    if (newMenu == openMenu) {
                        boolean buttonUpdate = button.shouldUpdate(player, event.getSlot(), event.getClick());
                        if (buttonUpdate) {
                            openMenu.setClosedByMenu(true);
                            newMenu.openMenu(player);
                        }
                        boolean menuUpdate = newMenu.isUpdateAfterClick();
                        if (menuUpdate) {
                            openMenu.setClosedByMenu(true);
                            newMenu.openMenu(player);
                        }
                    }
                } else if (button.shouldUpdate(player, event.getSlot(), event.getClick())) {
                    openMenu.setClosedByMenu(true);
                    openMenu.openMenu(player);
                }
                if (event.isCancelled()) {
                    BukkitScheduler scheduler = Bukkit.getScheduler();
                    Plugin plugin = this.plugin;
                    Objects.requireNonNull(player);
                    scheduler.runTaskLater(plugin, player::updateInventory, 2L);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ItemStack[] contents;
        ItemMeta meta;
        Player player = event.getPlayer();
        if (player.hasMetadata("scanglitch")) {
            player.removeMetadata("scanglitch", this.plugin);
            for (ItemStack it : player.getInventory().getContents()) {
                if (it != null && (meta = it.getItemMeta()) != null && meta.hasDisplayName() && meta.getDisplayName().contains("§b§c§d§e")) {
                    player.getInventory().remove(it);
                }
            }
        }
    }
}
