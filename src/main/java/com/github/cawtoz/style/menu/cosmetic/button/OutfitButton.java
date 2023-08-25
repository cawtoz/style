package com.github.cawtoz.style.menu.cosmetic.button;

import com.github.cawtoz.style.menu.outfit.OutfitMenu;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .setColor(Color.RED)
                .setName(FileUtil.getString("menus", "COSMETICS-MENU.ITEMS.OUTFITS-ITEM.TITLE"))
                .setLore(FileUtil.getStringList("menus", "COSMETICS-MENU.ITEMS.OUTFITS-ITEM.LORE"))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        new OutfitMenu().openMenu(player);
    }

}
