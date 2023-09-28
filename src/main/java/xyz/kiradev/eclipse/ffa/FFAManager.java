package xyz.kiradev.eclipse.ffa;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.kiradev.eclipse.Eclipse;
import xyz.kiradev.eclipse.player.Profile;
import xyz.kiradev.eclipse.util.C;

@Getter
@Setter
public class FFAManager {

    private int players, maxPlayers;
    private Location spawnLocation;

    public FFAManager() {
        this.players = 0;
        this.maxPlayers = 100;
        this.spawnLocation = null;
    }

    public void addPlayer(Profile profile) {
        if(players >= maxPlayers || spawnLocation == null) {
            C.sendMessage(profile, "&cThe ffa is not joinable at the moment. Reason: " + (players >= maxPlayers ? "FFA is full" : "No spawn location"));
        } else {
            players++;
            profile.setInFFA(true);
        }
    }

    public void removePlayer(Profile profile) {
        if(players > 0) players--;
        profile.setInFFA(false);
    }

    public void save() {
        FileConfiguration config = Eclipse.getInstance().getFfaFile().getConfiguration();
        config.set("info.max-players", maxPlayers);
        config.set("info.spawn-location.x", spawnLocation.getX());
        config.set("info.spawn-location.y", spawnLocation.getY());
        config.set("info.spawn-location.z", spawnLocation.getZ());
        config.set("info.spawn-location.yaw", spawnLocation.getYaw());
        config.set("info.spawn-location.pitch", spawnLocation.getPitch());
        config.set("info.spawn-location.world", spawnLocation.getWorld().getName());
        Eclipse.getInstance().getKitsFile().save();
    }

    public static FFAManager load() {
        FFAManager ffaManager = new FFAManager();
        FileConfiguration config = Eclipse.getInstance().getFfaFile().getConfiguration();
        ffaManager.setMaxPlayers(config.getInt("info.max-players"));
        Location location = new Location(Bukkit.getWorld(config.getString("info.spawn-location.world")),
                config.getDouble("info.spawn-location.x"),
                config.getDouble("info.spawn-location.y"),
                config.getDouble("info.spawn-location.z"),
                config.getInt("info.spawn-location.yaw"),
                config.getInt("info.spawn-location.pitch"));
        ffaManager.setSpawnLocation(location);
        return ffaManager;
    }
}
