package com.github.cawtoz.style.listener;

import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;


public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(Style.getInstance(), () -> { CosmeticUtil.loadCosmetics(event.getPlayer()); }, 1);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CosmeticUtil.destroyCosmetics(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        CosmeticUtil.destroyCosmetics(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        CosmeticUtil.updateCosmeticsLocation(event.getPlayer());
    }

    public void onPlayerDeath(PlayerDeathEvent event) {
        CosmeticUtil.destroyCosmetics(event.getEntity());
    }

}
