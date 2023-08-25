package com.github.cawtoz.style.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.util.ChatUtil;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import org.bukkit.entity.Player;

@CommandAlias("cosmetic|c")
public class CosmeticCommand extends BaseCommand {

    @Default
    @HelpCommand
    public void onCosmetic(Player player) {
        ChatUtil.sendMsg(player, "&cUsage /cosmetic <type> <name>");
    }

    @Subcommand("clear")
    public void onClear(Player player) {
        CosmeticUtil.removeCosmetics(player);
        ChatUtil.sendMsg(player, "&eAll cosmetics cleared.");
    }

    @Subcommand("balloon")
    @CommandCompletion("@balloons")
    public void onBalloon(Player player, String[] args) {
        use(player, CosmeticType.BALLOON, args);
    }

    @Subcommand("guardian")
    @CommandCompletion("@guardians")
    public void onAngel(Player player, String[] args) {
        use(player, CosmeticType.GUARDIAN, args);
    }

    @Subcommand("outfit")
    @CommandCompletion("@outfits")
    public void onOutfit(Player player, String[] args) {
        use(player, CosmeticType.OUTFIT, args);
    }

    public void use(Player player, CosmeticType cosmeticType, String[] args) {
        if (args.length != 1) {
            ChatUtil.sendMsg(player, "&cUsage /cosmetic " + cosmeticType.getName().toLowerCase() + " <name>");
            return;
        }
        CosmeticUtil.toggleCosmetic(player, cosmeticType, args[0]);
    }

}
