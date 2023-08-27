package com.github.cawtoz.style.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ColorUtil {

    public String formatColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public List<String> formatColor(List<String> messages) {
        List<String> messageFormat = new ArrayList<>();
        messages.forEach(message -> { messageFormat.add(ChatColor.translateAlternateColorCodes('&', message)); });
        return messageFormat;
    }

    public Color getNextColor(List<Color> colors, double progress) {

        int size = colors.size();
        int currentIndex = (int) (progress * (size - 1));
        int nextIndex = (currentIndex + 1) % size;

        Color color1 = colors.get(currentIndex);
        Color color2 = colors.get(nextIndex);

        int red = lerp(color1.getRed(), color2.getRed(), progress * (size - 1) - currentIndex);
        int green = lerp(color1.getGreen(), color2.getGreen(), progress * (size - 1) - currentIndex);
        int blue = lerp(color1.getBlue(), color2.getBlue(), progress * (size - 1) - currentIndex);

        return Color.fromRGB(red, green, blue);
    }

    private int lerp(int a, int b, double t) {
        return (int) (a + (b - a) * t);
    }

}
