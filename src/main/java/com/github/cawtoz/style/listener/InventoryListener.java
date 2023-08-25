package com.github.cawtoz.style.listener;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.util.profile.ProfileUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)  event.getWhoClicked();
        if (ProfileUtil.get(player) == null) return;
        if (event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        if (ProfileUtil.get(player).hasCosmetic(CosmeticType.OUTFIT)) event.setCancelled(true);
    }

}
