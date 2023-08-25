package com.github.cawtoz.style.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.cawtoz.style.menu.cosmetic.CosmeticMenu;
import org.bukkit.entity.Player;

@CommandAlias("cosmetics|cm")
public class CosmeticsCommand extends BaseCommand {

    @Default
    public void onCosmetics(Player player) {
        new CosmeticMenu().openMenu(player);
    }

}
