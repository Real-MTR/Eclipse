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
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.kiradev.eclipse.Eclipse;
import xyz.kiradev.eclipse.util.C;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Profile {
    private static final Map<UUID, Profile> profiles = new ConcurrentHashMap<>();

    private final UUID uuid;
    private int kills, deaths, streak, bestStreak, coins;
    private boolean inFFA;
    private List<String> ownedKits;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.kills = 0;
        this.deaths = 0;
        this.streak = 0;
        this.bestStreak = 0;
        this.inFFA = false;
        this.ownedKits = new ArrayList<>();
    }

    public void saveOrCreate() {
        FileConfiguration config = Eclipse.getInstance().getProfilesFile().getConfiguration();
        String id = uuid.toString();
        List<String> list = config.getStringList("profiles");
        if(!list.contains(id)) list.add(id);
        config.set("profiles", list);
        config.set(id + ".kills", kills);
        config.set(id + ".deaths", kills);
        config.set(id + ".streak", kills);
        config.set(id + ".best-streak", kills);
        config.set(id + ".coins", coins);
        config.set(id + ".in-ffa", inFFA);
        config.set(id + ".owned-kits", ownedKits);
        Eclipse.getInstance().getProfilesFile().save();
        profiles.putIfAbsent(uuid, this);
    }

    public void loadProfile() {
        FileConfiguration config = Eclipse.getInstance().getProfilesFile().getConfiguration();
        String id = uuid.toString();
        this.kills = config.getInt(id + ".kills");
        this.deaths = config.getInt(id + ".deaths");
        this.streak = config.getInt(id + ".streak");
        this.bestStreak = config.getInt(id + ".best-streak");
        this.coins = config.getInt(id + ".coins");
        this.inFFA = config.getBoolean(id + ".in-ffa");
        this.ownedKits = config.getStringList(".owned-kits");
        profiles.putIfAbsent(uuid, this);
    }

    public void deleteProfile() {
        FileConfiguration config = Eclipse.getInstance().getProfilesFile().getConfiguration();
        String id = uuid.toString();
        Player player = Bukkit.getPlayer(uuid);
        if(player != null && player.isOnline()) {
            player.kickPlayer(C.color("&cYour profile has been deleted. \n&cYou may relog now."));
        }
        config.set(id, null);
        Eclipse.getInstance().getProfilesFile().save();
        profiles.remove(uuid);
    }

    public static void init() {
        FileConfiguration config = Eclipse.getInstance().getProfilesFile().getConfiguration();
        for(String uuids : config.getStringList("profiles")) {
            UUID uuid = UUID.fromString(uuids);
            Profile profile = new Profile(uuid);
            profile.loadProfile();
        }
    }

    public static Profile getProfile(UUID uuid) {
        return profiles.values().stream().filter(profile -> profile.getUuid().toString().equals(uuid.toString())).findFirst().orElse(null);
    }
}
