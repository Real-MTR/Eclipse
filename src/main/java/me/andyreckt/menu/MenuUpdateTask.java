package me.andyreckt.menu;

import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/MenuUpdateTask.class */
public class MenuUpdateTask extends BukkitRunnable {
    public MenuUpdateTask(Plugin plugin) {
        runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    public void run() {
        for (Map.Entry<UUID, Menu> entry : Menu.getCurrentlyOpenedMenus().entrySet()) {
            UUID key = entry.getKey();
            Menu value = entry.getValue();
            Player player = Bukkit.getPlayer(key);
            if (player != null && value.isAutoUpdate()) {
                value.openMenu(player);
            }
        }
    }
}
