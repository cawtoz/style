package com.github.cawtoz.style.command;

import co.aikar.commands.BukkitCommandManager;
import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CommandManager {

    public void register() {
        BukkitCommandManager cm = new BukkitCommandManager(Style.getInstance());
        cm.registerCommand(new StyleCommand());
        cm.registerCommand(new CosmeticCommand());
        cm.registerCommand(new CosmeticsCommand());
        addCommandCompletion("balloons", CosmeticUtil.getCosmetics(CosmeticType.BALLOON), cm);
        addCommandCompletion("guardians", CosmeticUtil.getCosmetics(CosmeticType.GUARDIAN), cm);
        addCommandCompletion("outfits", CosmeticUtil.getCosmetics(CosmeticType.OUTFIT), cm);
    }

    private void addCommandCompletion(String id, List<String> list, BukkitCommandManager commandManager) {
        commandManager.getCommandCompletions().registerStaticCompletion(id, list);
    }

}
