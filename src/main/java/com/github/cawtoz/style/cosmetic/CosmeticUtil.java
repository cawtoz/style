package com.github.cawtoz.style.cosmetic;

import com.github.cawtoz.style.cosmetic.impl.Balloon;
import com.github.cawtoz.style.cosmetic.impl.Guardian;
import com.github.cawtoz.style.cosmetic.impl.Outfit;
import com.github.cawtoz.style.cosmetic.type.InterfaceTeleportable;
import com.github.cawtoz.style.menu.balloon.CosmeticBallonButton;
import com.github.cawtoz.style.menu.guardian.CosmeticGuardianButton;
import com.github.cawtoz.style.menu.outfit.CosmeticOutfitButton;
import com.github.cawtoz.style.util.ChatUtil;
import com.github.cawtoz.style.util.file.FileUtil;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.profile.Profile;
import com.github.cawtoz.style.util.profile.ProfileUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class CosmeticUtil {

    public void toggleCosmetic(Player player, CosmeticType cosmeticType, String cosmeticName) {

        if (!(doesCosmeticExist(cosmeticType, cosmeticName))) {
            ChatUtil.sendMsg(player, FileUtil.getString("messages", cosmeticType.toString() + ".NOT-EXIST").replace("%" + cosmeticType + "%", cosmeticName));
            return;
        }

        Cosmetic cosmetic = buildCosmetic(player, cosmeticType, cosmeticName);

        if (!player.hasPermission(cosmetic.getPermission())) {
            ChatUtil.sendMsg(player, "&cYou don't have the " + cosmetic.getType().getName() + " &f" + cosmetic.getDisplayName() + "&c.");
            return;
        }

        Profile profile = (ProfileUtil.get(player) == null) ? ProfileUtil.create(player) : ProfileUtil.get(player);
        if (profile.hasCosmetic(cosmeticType)) {
            cosmetic = profile.getCosmetic(cosmeticType);
            if (cosmetic.getName().equals(cosmeticName)) {
                removeCosmetic(player, cosmeticType);
                ChatUtil.sendMsg(player, FileUtil.getString("messages", cosmeticType + ".DISABLE").replace("%" + cosmeticType + "%", cosmetic.getDisplayName()));
                return;
            }
        }

        setCosmetic(player, cosmeticType, cosmeticName);
        ChatUtil.sendMsg(player, FileUtil.getString("messages", cosmeticType + ".ENABLE").replace("%" + cosmeticType + "%", cosmetic.getDisplayName()));

    }

    public void setCosmetic(Player player, CosmeticType cosmeticType, String cosmeticName) {
        removeCosmetic(player, cosmeticType);
        Profile profile = (ProfileUtil.get(player) == null) ? ProfileUtil.create(player) : ProfileUtil.get(player);
        profile.setCosmetic(buildCosmetic(player, cosmeticType, cosmeticName).create());
        profile.save();
    }

    public void removeCosmetic(Player player, CosmeticType cosmeticType) {
        if (ProfileUtil.get(player) == null) return;
        Profile profile = ProfileUtil.get(player);
        if (profile.getCosmetic(cosmeticType) == null) return;
        profile.getCosmetic(cosmeticType).destroy();
        profile.removeCosmetic(cosmeticType);
        profile.save();
    }

    public void removeCosmetics(Player player) {
        if (ProfileUtil.get(player) == null) return;
        destroyCosmetics(player);
        Profile profile = ProfileUtil.get(player);
        profile.getCosmetics().clear();
        profile.save();
    }

    public void destroyCosmetics(Player player) {
        if (ProfileUtil.get(player) == null) return;
        Profile profile = ProfileUtil.get(player);
        profile.getCosmetics().forEach(Cosmetic::destroy);
    }

    public void updateCosmeticsLocation(Player player) {
        if (ProfileUtil.get(player) == null) return;
        ProfileUtil.get(player).getCosmetics().forEach(cosmetic -> {
            if (cosmetic instanceof InterfaceTeleportable) {
                ((InterfaceTeleportable) cosmetic).updateLocation(player.getLocation());
            }
        });
    }

    public boolean doesCosmeticExist(CosmeticType cosmeticType, String cosmeticName) {
        return getCosmetics(cosmeticType).contains(cosmeticName);
    }

    public List<String> getCosmetics(CosmeticType cosmeticType) {
        switch (cosmeticType) {
            case BALLOON:
                return FileUtil.getKeys("cosmetics/balloons");
            case GUARDIAN:
                return FileUtil.getKeys("cosmetics/guardians");
            case OUTFIT:
                return FileUtil.getKeys("cosmetics/outfits");
        }
        return null;
    }

    public Cosmetic buildCosmetic(Player player, CosmeticType cosmeticType, String cosmeticName) {
        switch (cosmeticType) {
            case BALLOON:
                return new Balloon(player, cosmeticName);
            case GUARDIAN:
                return new Guardian(player, cosmeticName);
            case OUTFIT:
                return new Outfit(player, cosmeticName);
        }
        return null;
    }

    public Map<Integer, Button> getCosmeticsButtons(Player player, CosmeticType cosmeticType) {
        Map<Integer, Button> buttons = new HashMap<>();

        CosmeticUtil.getCosmetics(cosmeticType).forEach(cosmetic -> {

            switch (cosmeticType) {
                case BALLOON:
                    buttons.put(buttons.size(), new CosmeticBallonButton((Balloon) buildCosmetic(player, cosmeticType, cosmetic)));
                    break;
                case OUTFIT:
                    buttons.put(buttons.size(), new CosmeticOutfitButton((Outfit) buildCosmetic(player, cosmeticType, cosmetic)));
                    break;
                case GUARDIAN:
                    buttons.put(buttons.size(), new CosmeticGuardianButton((Guardian) buildCosmetic(player, cosmeticType, cosmetic)));
                    break;
            }
        });

        return buttons;
    }

    public void loadCosmetics(Player player) {
        for (String uuid : FileUtil.getKeys("profiles")) {

            if (!(UUID.fromString(uuid).equals(player.getUniqueId()))) continue;
            ProfileUtil.create(player);

            for (CosmeticType cosmeticType : CosmeticType.values()) {
                String cosmeticName = FileUtil.getString("profiles", uuid + ".COSMETICS." + cosmeticType.toString());
                if (CosmeticUtil.doesCosmeticExist(cosmeticType, cosmeticName)) {
                    ProfileUtil.get(player).setCosmetic(buildCosmetic(player, cosmeticType, cosmeticName).create());
                }
            }

            break;
        }

    }

}
