package com.github.cawtoz.style.util.profile;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.*;

@UtilityClass
public class ProfileUtil {

    private static final Map<Player, Profile> profiles = new HashMap<>();

    public static Profile get(Player target) {
        return profiles.get(target);
    }

    public static Profile create(Player player) {
        profiles.put(player, new Profile(player.getUniqueId()));
        return get(player);
    }

    public static void remove(Player player) {
        profiles.remove(player);
    }

}
