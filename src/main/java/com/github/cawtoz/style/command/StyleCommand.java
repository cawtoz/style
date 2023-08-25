package com.github.cawtoz.style.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.ChatUtil;
import com.github.cawtoz.style.util.file.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("style")
public class StyleCommand extends BaseCommand {

    @Default
    @HelpCommand
    public void onStyle(Player player) {
        ChatUtil.sendMsg(player,
                "&8&m                                                                                ",
                "&6&l STYLE COSMETICS",
                "&6&l ┏━▶ &e/style info &fto view info.",
                "&6&l ┣━▶ &e/style reload &fto reload config.",
                "&6&l ┣━▶ &e/cosmetic <type> <name> &fto put cosmetic.",
                "&6&l ┗━▶ &e/cosmetics &fto open cosmetics menu.",
                "&8&m                                                                                "
        );
    }

    @Subcommand("info")
    public void onInfo(Player player) {
        ChatUtil.sendMsg(player,
                "&8&m                                                                      ",
                "&6&l STYLE INFO",
                "&6&l ┏━▶ &eAuthors &f" + Style.getInstance().getDescription().getAuthors(),
                "&6&l ┣━▶ &eVersion &f" + Style.getInstance().getDescription().getVersion(),
                "&6&l ┣━▶ &eSupport &fhttps://discord.gg/ZzvcNqrSXA",
                "&6&l ┗━▶ &e/style help &fto see all commands.",
                "&8&m                                                                      "
        );
    }

    @Subcommand("reload")
    @CommandPermission("style.reload")
    public void onReload(Player player) {
        Bukkit.getOnlinePlayers().forEach(CosmeticUtil::destroyCosmetics);
        FileManager.loadFiles();
        Bukkit.getOnlinePlayers().forEach(CosmeticUtil::loadCosmetics);
        ChatUtil.sendMsg(player, "&aThe configuration has been reloaded successfully");
    }


}
