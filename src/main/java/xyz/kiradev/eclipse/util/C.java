package xyz.kiradev.eclipse.util;

/*
 *
 * Eclipse is a property of Kira-Development-Team
 * It was created @ 28/09/2023
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@UtilityClass
public class C {
    public String color(String text) { return ChatColor.translateAlternateColorCodes('&', text); }
    public void sendMessage(CommandSender sender, String message) { sender.sendMessage(color(message)); }
}
