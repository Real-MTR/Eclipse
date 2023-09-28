package xyz.kiradev.eclipse.kit;

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
import me.andyreckt.menu.buttons.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import xyz.kiradev.eclipse.Eclipse;
import xyz.kiradev.eclipse.util.InventoryUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Kit {
    private static Map<UUID, Kit> kits = new ConcurrentHashMap<>();

    private final UUID uuid;
    private String name;

    private ItemStack[] armorContents, inventoryContents;
    private int cost;

    public Kit(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.armorContents = new ItemStack[]{};
        this.inventoryContents = new ItemStack[]{};
        this.cost = 100;
    }

    public void saveOrCreate() {
        FileConfiguration config = Eclipse.getInstance().getKitsFile().getConfiguration();
        String id = uuid.toString();
        List<String> list = config.getStringList("kits");
        if(!list.contains(id)) list.add(id);
        config.set("kits", list);
        config.set(id + ".name", name);
        config.set(id + ".inventory-contents", InventoryUtil.serializeInventory(inventoryContents));
        config.set(id + ".armor-contents", InventoryUtil.serializeInventory(armorContents));
        config.set(id + ".cost", cost);
        Eclipse.getInstance().getKitsFile().save();
        kits.putIfAbsent(uuid, this);
    }

    public void loadKit() {
        FileConfiguration config = Eclipse.getInstance().getKitsFile().getConfiguration();
        String id = uuid.toString();
        this.name = config.getString(id + ".name");
        this.inventoryContents = InventoryUtil.deserializeInventory(config.getString(id + ".inventory-contents"));
        this.armorContents = InventoryUtil.deserializeInventory(config.getString(id + ".armor-contents"));
        this.cost = config.getInt(id + ".cost");
        kits.putIfAbsent(uuid, this);
    }

    public void deleteKit() {
        FileConfiguration config = Eclipse.getInstance().getKitsFile().getConfiguration();
        String id = uuid.toString();
        config.set(id, null);
        Eclipse.getInstance().getKitsFile().save();
        kits.remove(uuid);
    }

    public static void init() {
        FileConfiguration config = Eclipse.getInstance().getKitsFile().getConfiguration();
        for(String uuids : config.getStringList("profiles")) {
            UUID uuid = UUID.fromString(uuids);
            Kit kit = new Kit(uuid, config.getString(uuid.toString() + ".name"));
            kit.loadKit();
        }
        if(getKitByName("Default") == null) {
            Kit kit = new Kit(UUID.randomUUID(), "Default");
            kit.setArmorContents(new ItemStack[]{
                    new ItemBuilder(Material.IRON_HELMET).build(),
                    new ItemBuilder(Material.IRON_CHESTPLATE).build(),
                    new ItemBuilder(Material.IRON_LEGGINGS).build(),
                    new ItemBuilder(Material.IRON_BOOTS).build()
            });
            kit.setInventoryContents(new ItemStack[]{new ItemBuilder(Material.DIAMOND_SWORD).build(), new ItemBuilder(Material.ENDER_PEARL).amount(16).build()});
        }
    }

    public static Kit getKitByUUID(UUID uuid) {
        return kits.values().stream().filter(kit -> kit.getUuid().toString().equals(uuid.toString())).findFirst().orElse(null);
    }

    public static Kit getKitByName(String name) {
        return kits.values().stream().filter(kit -> kit.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}