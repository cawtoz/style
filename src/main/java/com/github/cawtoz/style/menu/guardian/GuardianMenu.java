package com.github.cawtoz.style.menu.guardian;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.Map;

public class GuardianMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player player) {
        return FileUtil.getString("menus", "GUARDIANS-MENU.TITLE");
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        return CosmeticUtil.getCosmeticsButtons(player, CosmeticType.GUARDIAN);
    }

}
