package com.github.cawtoz.style.util;

import de.tr7zw.nbtapi.NBTEntity;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.LivingEntity;

@UtilityClass
public class EntityUtil {

    public void applyNBTTag(LivingEntity livingEntity) {
        NBTEntity entity = new NBTEntity(livingEntity);
        entity.setBoolean("Silent", true);
        entity.setBoolean("NoAI", true);
        entity.setBoolean("Invulnerable", true);
    }

}




