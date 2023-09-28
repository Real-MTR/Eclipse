package xyz.kiradev.eclipse;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kiradev.eclipse.util.FileManager;

@Getter
public final class Eclipse extends JavaPlugin {

    @Getter
    private static Eclipse instance;

    private FileManager profilesFile;

    @Override
    public void onEnable() {
        instance = this;

        this.profilesFile = new FileManager(this, "profiles.yml");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}