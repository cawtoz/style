package com.github.cawtoz.style.util;

import org.bukkit.Color;
import org.bukkit.Location;

public class LocationUtil {

    public static Location createLocation(Location location, double side, double frontal, double height) {
        double angle = Math.toRadians(location.getYaw());
        double newX = location.getX() - Math.sin(angle) * frontal + Math.cos(angle) * side;
        double newY = location.getY() + height;
        double newZ = location.getZ() + Math.cos(angle) * frontal + Math.sin(angle) * side;
        return new Location(location.getWorld(), newX, newY, newZ, location.getYaw(), 0);
    }

    public static Color lerpColor(Color color1, Color color2, double t) {
        t = Math.max(0, Math.min(1, t));

        int r1 = color1.getRed();
        int g1 = color1.getGreen();
        int b1 = color1.getBlue();

        int r2 = color2.getRed();
        int g2 = color2.getGreen();
        int b2 = color2.getBlue();

        int r = (int) (r1 + (r2 - r1) * t);
        int g = (int) (g1 + (g2 - g1) * t);
        int b = (int) (b1 + (b2 - b1) * t);

        return Color.fromRGB(r, g, b);
    }

}
