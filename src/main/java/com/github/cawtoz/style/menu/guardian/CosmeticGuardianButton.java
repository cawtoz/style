package com.github.cawtoz.style.menu.guardian;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.impl.Guardian;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CosmeticGuardianButton extends Button {

    private final Guardian guardian;

    public CosmeticGuardianButton(Guardian guardian) {
        this.guardian = guardian;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(HeadUtil.createCustomSkull(guardian.getHeadValue()))
                .setName(FileUtil.getString("menus", "GUARDIANS-MENU.GUARDIAN-ITEM.TITLE", "%GUARDIAN%", guardian.getDisplayName()))
                .setLore(FileUtil.getStringList("menus", "GUARDIANS-MENU.GUARDIAN-ITEM.LORE", "%GUARDIAN%", guardian.getDisplayName()))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();
        CosmeticUtil.toggleCosmetic(player, CosmeticType.GUARDIAN, guardian.getName());
    }

}
