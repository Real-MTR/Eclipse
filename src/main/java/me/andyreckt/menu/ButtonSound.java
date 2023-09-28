package me.andyreckt.menu;

import org.bukkit.Sound;

/* loaded from: Sovereign-1.0.jar:me/andyreckt/menu/ButtonSound.class */
public enum ButtonSound {
    CLICK(Sound.CLICK),
    SUCCESS(Sound.SUCCESSFUL_HIT),
    FAIL(Sound.DIG_GRASS);
    
    private final Sound sound;

    ButtonSound(Sound sound) {
        this.sound = sound;
    }

    public Sound getSound() {
        return this.sound;
    }
}
