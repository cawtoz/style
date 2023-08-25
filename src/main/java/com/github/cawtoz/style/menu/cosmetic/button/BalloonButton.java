package com.github.cawtoz.style.menu.cosmetic.button;

import com.github.cawtoz.style.menu.balloon.BalloonMenu;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BalloonButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(HeadUtil.createCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ3ZjVlMmU1MjM1YjY3OWY4ZGI5YzQyZTg1OWM4OGFhNzgyN2IxZmI1MTgyYzA3NzAzYzQ1NmU5MTI1Y2Y1ZiJ9fX0="))
                .setName(FileUtil.getString("menus", "COSMETICS-MENU.ITEMS.BALLOONS-ITEM.TITLE"))
                .setLore(FileUtil.getStringList("menus", "COSMETICS-MENU.ITEMS.BALLOONS-ITEM.LORE"))
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        new BalloonMenu().openMenu(player);
    }

}
