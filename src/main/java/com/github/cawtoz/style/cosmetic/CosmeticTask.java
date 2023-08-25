package com.github.cawtoz.style.cosmetic;

import com.github.cawtoz.style.cosmetic.type.CosmeticEquippable;
import com.github.cawtoz.style.cosmetic.type.InterfaceTeleportable;
import com.github.cawtoz.style.util.profile.ProfileUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CosmeticTask extends BukkitRunnable {

    private static CosmeticTask task;

    private CosmeticTask() {}

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ProfileUtil.get(player) == null) continue;
            ProfileUtil.get(player).getCosmetics().forEach(cosmetic -> {
                if (cosmetic instanceof CosmeticEquippable) ((CosmeticEquippable) cosmetic).updateArmor();
                if (cosmetic instanceof InterfaceTeleportable) ((InterfaceTeleportable) cosmetic).makeAnimation();
            });
        }
    }

    public static CosmeticTask getTask() {
        if (task == null) task = new CosmeticTask();
        return task;
    }

}
