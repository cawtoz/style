package com.github.cawtoz.style.menu.balloon;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.impl.Balloon;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CosmeticBallonButton extends Button {

    private final Balloon balloon;

    public CosmeticBallonButton(Balloon balloon) {
        this.balloon = balloon;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(HeadUtil.createCustomSkull(balloon.getHeadValue()))
                .setName(FileUtil.getString("menus", "BALLOONS-MENU.BALLOON-ITEM.TITLE", "%BALLOON%", balloon.getDisplayName()))
                .setLore(FileUtil.getStringList("menus", "BALLOONS-MENU.BALLOON-ITEM.LORE", "%BALLOON%", balloon.getDisplayName()))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();
        CosmeticUtil.toggleCosmetic(player, CosmeticType.BALLOON, balloon.getName());
    }

}
