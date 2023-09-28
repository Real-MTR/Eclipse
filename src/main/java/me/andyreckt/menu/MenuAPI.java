package me.andyreckt.menu;

import org.bukkit.plugin.java.JavaPlugin;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/MenuAPI.class */
public final class MenuAPI {
    public MenuAPI(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ButtonListener(plugin), plugin);
        new MenuUpdateTask(plugin);
    }
}
