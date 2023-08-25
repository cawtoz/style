package com.github.cawtoz.style.cosmetic.type;

import com.github.cawtoz.style.cosmetic.Cosmetic;
import com.github.cawtoz.style.cosmetic.CosmeticType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

@Getter @Setter
public abstract class CosmeticTeleportable extends Cosmetic implements InterfaceTeleportable {

    private Location location;

    public CosmeticTeleportable(LivingEntity livingEntity, String name, CosmeticType type) {
        super(livingEntity, name, type);
        this.location = livingEntity.getLocation();
    }

}
