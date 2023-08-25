package com.github.cawtoz.style.menu.cosmetic.button;

import com.github.cawtoz.style.menu.guardian.GuardianMenu;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GuardianButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(HeadUtil.createCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNmMDc0NGU3YjljM2JmYjMzM2FjMzRkZDE1MGU4NGNiYzMzMjhiMzE5ZThjN2NmYzk4NTAxMTFmYzVjNWUwNSJ9fX0="))
                .setName(FileUtil.getString("menus", "COSMETICS-MENU.ITEMS.GUARDIANS-ITEM.TITLE"))
                .setLore(FileUtil.getStringList("menus", "COSMETICS-MENU.ITEMS.GUARDIANS-ITEM.LORE"))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        new GuardianMenu().openMenu(player);
    }

}
