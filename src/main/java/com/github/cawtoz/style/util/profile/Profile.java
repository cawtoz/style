package com.github.cawtoz.style.util.profile;

import com.github.cawtoz.style.cosmetic.Cosmetic;
import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.file.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Profile {

    private final UUID id;

    private final List<Cosmetic> cosmetics = new ArrayList<>();

    public Profile(UUID id) {
        this.id = id;
    }

    public boolean hasCosmetic(CosmeticType cosmeticType) {
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.getType() == cosmeticType) return true;
        }
        return false;
    }

    public Cosmetic getCosmetic(CosmeticType cosmeticType) {
        for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic.getType() == cosmeticType) return cosmetic;
        }
        return null;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        for (Cosmetic value : cosmetics) {
            if (cosmetic.getType() == value.getType()) {
                value = cosmetic;
                return;
            }
        }
        cosmetics.add(cosmetic);
    }

    public void removeCosmetic(CosmeticType cosmeticType) {
        cosmetics.removeIf(cosmetic -> cosmetic.getType() == cosmeticType);
    }

    public void save() {
        FileUtil.set("profiles", id.toString() + ".NAME", Bukkit.getPlayer(id).getName());
        for (CosmeticType cosmeticType : CosmeticType.values()) {
            String path = id.toString() + ".COSMETICS." + cosmeticType.toString();
            if (hasCosmetic(cosmeticType)) {
                FileUtil.set("profiles", path, getCosmetic(cosmeticType).getName());
            } else {
                FileUtil.set("profiles", path, "NONE");
            }
        }
    }

    public void load() {
        for (String uuid : FileUtil.getKeys("profiles")) {
            if (!(UUID.fromString(uuid).equals(id))) return;
            for (CosmeticType cosmeticType : CosmeticType.values()) {
                String cosmeticName = FileUtil.getString("profiles", uuid + ".COSMETICS." + cosmeticType.toString());
                if (CosmeticUtil.doesCosmeticExist(cosmeticType, cosmeticName)) {
                    CosmeticUtil.setCosmetic(Bukkit.getPlayer(id), cosmeticType, cosmeticName);
                }
            }
        }
    }

}
