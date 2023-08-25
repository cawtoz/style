package com.github.cawtoz.style.cosmetic;

import com.github.cawtoz.style.util.file.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.LivingEntity;

@Getter @Setter
public abstract class Cosmetic {

    private LivingEntity player;
    private final String name;
    private String displayName;
    private final CosmeticType type;
    private String permission;

    public Cosmetic(LivingEntity player, String name, CosmeticType type) {
        this.player = player;
        this.name = name;
        this.displayName = FileUtil.getString(type.getFileName(), name + ".DISPLAY-NAME");
        this.type = type;
        this.permission = "style.cosmetic." + type.getName() + "." + name.toLowerCase();
    }

    public abstract Cosmetic create();
    public abstract void destroy();

}
