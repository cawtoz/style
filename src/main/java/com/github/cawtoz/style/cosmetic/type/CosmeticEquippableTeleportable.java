package com.github.cawtoz.style.cosmetic.type;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;

@Getter @Setter
public abstract class CosmeticEquippableTeleportable extends CosmeticEquippable implements InterfaceTeleportable {

    public CosmeticEquippableTeleportable(LivingEntity livingEntity, String name, CosmeticType type) {
        super(livingEntity, name, type);
    }

}
