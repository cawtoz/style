package com.github.cawtoz.style.cosmetic;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum CosmeticType {

    BALLOON("balloon", "cosmetics/balloons"),
    GUARDIAN("guardian", "cosmetics/guardians"),
    OUTFIT("outfit", "cosmetics/outfits");

    private final String name;
    private final String fileName;

    CosmeticType(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public static List<String> getNames() {
        return Arrays.stream(CosmeticType.values()).map(CosmeticType::getName).collect(Collectors.toList());
    }

}
