package com.github.cawtoz.style.menu.cosmetic;

import com.github.cawtoz.style.menu.cosmetic.button.BalloonButton;
import com.github.cawtoz.style.menu.cosmetic.button.GuardianButton;
import com.github.cawtoz.style.menu.cosmetic.button.OutfitButton;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.Menu;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CosmeticMenu extends Menu {

    public CosmeticMenu() {
        setPlaceholder(FileUtil.getBoolean("menus", "COSMETICS-MENU.FILL.ENABLE"));
    }

    @Override
    public String getTitle(Player player) {
        return FileUtil.getString("menus", "COSMETICS-MENU.TITLE");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(FileUtil.getInt("menus", "COSMETICS-MENU.ITEMS.BALLOONS-ITEM.SLOT"), new BalloonButton());
        buttons.put(FileUtil.getInt("menus", "COSMETICS-MENU.ITEMS.OUTFITS-ITEM.SLOT"), new OutfitButton());
        buttons.put(FileUtil.getInt("menus", "COSMETICS-MENU.ITEMS.GUARDIANS-ITEM.SLOT"), new GuardianButton());
        return buttons;
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }

}
