package com.github.cawtoz.style.listener;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.impl.Balloon;
import com.github.cawtoz.style.util.profile.Profile;
import com.github.cawtoz.style.util.profile.ProfileUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

public class BalloonListener implements Listener {

    @EventHandler
    public void onEntityUnleashEvent(PlayerUnleashEntityEvent event) {
        if (isEntityBalloon(event.getPlayer(), event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (isEntityBalloon((Player) event.getDamager(), event.getEntity())) event.setCancelled(true);
    }

    private boolean isEntityBalloon(Player player, Entity entity) {
        if (ProfileUtil.get(player) == null) return false;
        Profile profile = ProfileUtil.get(player);

        if (!profile.hasCosmetic(CosmeticType.BALLOON)) return false;
        Entity leashEntityBalloon = ((Balloon) profile.getCosmetic(CosmeticType.BALLOON)).getLeashEntityBalloon();

        return leashEntityBalloon.equals(entity);
    }


}
