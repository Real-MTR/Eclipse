package xyz.kiradev.eclipse;

import lombok.Getter;
import me.andyreckt.menu.MenuAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kiradev.eclipse.ffa.FFAManager;
import xyz.kiradev.eclipse.kit.Kit;
import xyz.kiradev.eclipse.listener.ProfileListener;
import xyz.kiradev.eclipse.player.Profile;
import xyz.kiradev.eclipse.util.FileManager;

@Getter
public final class Eclipse extends JavaPlugin {

    @Getter
    private static Eclipse instance;

    private MenuAPI menuAPI;
    private FileManager profilesFile, kitsFile, ffaFile;
    private FFAManager ffaManager;

    @Override
    public void onEnable() {
        instance = this;

        setupAPI();
        setupKits();
        setupProfiles();
    }

    private void setupAPI() {
        this.menuAPI = new MenuAPI(this);
    }

    private void setupProfiles() {
        this.profilesFile = new FileManager(this, "profiles.yml");

        Profile.init();
        Bukkit.getPluginManager().registerEvents(new ProfileListener(), this);
    }

    private void setupKits() {
        this.kitsFile = new FileManager(this, "kits.yml");

        Kit.init();
    }

    private void setupFFA() {
        this.ffaFile = new FileManager(this, "ffa.yml");
        this.ffaManager = FFAManager.load();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}