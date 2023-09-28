package xyz.kiradev.eclipse.player;

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
import org.bukkit.configuration.file.FileConfiguration;
import xyz.kiradev.eclipse.Eclipse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Profile {
    private static final Map<UUID, Profile> profiles = new ConcurrentHashMap<>();

    private final UUID uuid;
    private int kills, deaths, streak, bestStreak;
    private boolean inFFA;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.kills = 0;
        this.deaths = 0;
        this.streak = 0;
        this.bestStreak = 0;
        this.inFFA = false;
    }

    public void saveOrCreate() {
        FileConfiguration config = Eclipse.getInstance().getProfilesFile().getConfiguration();
        String id = uuid.toString();
        config.set(id + ".kills", kills);
        config.set(id + ".deaths", kills);
        config.set(id + ".streak", kills);
        config.set(id + ".best-streak", kills);
        config.set(id + ".in-ffa", inFFA);
        profiles.putIfAbsent(uuid, this);
    }
}
