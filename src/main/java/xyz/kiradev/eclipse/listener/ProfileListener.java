package xyz.kiradev.eclipse.listener;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import xyz.kiradev.eclipse.menu.main.FFAMenu;
import xyz.kiradev.eclipse.player.Profile;
import xyz.kiradev.eclipse.util.C;
import xyz.kiradev.eclipse.util.Items;

public class ProfileListener implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        try {
            Profile profile = Profile.getProfile(event.getUniqueId());
            if (profile == null) {
                profile = new Profile(event.getUniqueId());
                profile.saveOrCreate();
            }
            profile.loadProfile();
        } catch (Exception e) {
            C.sendMessage(Bukkit.getConsoleSender(), "&cAn error occurred while loading " + event.getName() + "'s profile.");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[]{null, null, null, null});

        player.getInventory().setItem(4, Items.JOIN_FFA_ITEM.getItem());
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!event.getAction().name().toLowerCase().contains("right")) return;
        if(event.getItem() == null) return;
        if(event.getItem().isSimilar(Items.JOIN_FFA_ITEM.getItem())) {
            new FFAMenu().openMenu(player);
        }
    }
}