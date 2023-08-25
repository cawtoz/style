package com.github.cawtoz.style.menu.outfit;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.impl.Outfit;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CosmeticOutfitButton extends Button {

    private final Outfit outfit;

    public CosmeticOutfitButton(Outfit outfit) {
        this.outfit = outfit;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(outfit.getChestPlate())
                .setColor(outfit.getColorChestPlate().get(0))
                .setName(FileUtil.getString("menus", "OUTFITS-MENU.OUTFIT-ITEM.TITLE", "%OUTFIT%", outfit.getDisplayName()))
                .setLore(FileUtil.getStringList("menus", "OUTFITS-MENU.OUTFIT-ITEM.LORE", "%OUTFIT%", outfit.getDisplayName()))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.closeInventory();
        CosmeticUtil.toggleCosmetic(player, CosmeticType.OUTFIT, outfit.getName());
    }

}
