package com.github.cawtoz.style.cosmetic.impl;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.type.CosmeticEquippable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class Outfit extends CosmeticEquippable {

    private ItemStack[] oldArmorContents;

    public Outfit(LivingEntity livingEntity, String name) {
        super(livingEntity, name, CosmeticType.OUTFIT);
    }

    @Override
    public Outfit create() {
        oldArmorContents = getPlayer().getEquipment().getArmorContents();
        setEntityEquippable(getPlayer());
        return this;
    }

    @Override
    public void destroy() {
        getPlayer().getEquipment().setArmorContents(oldArmorContents);
    }

}
