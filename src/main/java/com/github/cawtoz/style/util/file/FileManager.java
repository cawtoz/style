package com.github.cawtoz.style.util.file;

import com.github.cawtoz.style.Style;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    private static final Map<String, FileConfiguration> files = new HashMap<>();
    private static final List<String> fileNames = new ArrayList<>();

    public static void loadFiles() {
        fileNames.add("messages.yml");
        fileNames.add("colors.yml");
        fileNames.add("profiles.yml");
        fileNames.add("menus.yml");
        fileNames.add("cosmetics/balloons.yml");
        fileNames.add("cosmetics/guardians.yml");
        fileNames.add("cosmetics/outfits.yml");
        fileNames.forEach(FileManager::loadFile);
    }

    public static void loadFile(String fileName) {
        File configFile = new File(Style.getInstance().getDataFolder(), fileName);
        if (!configFile.exists()) {
            Style.getInstance().saveResource(fileName, false);
        }
        files.put(fileName, YamlConfiguration.loadConfiguration(configFile));
    }

    public static FileConfiguration getFile(String fileName) {
        return files.get(fileName + ".yml");
    }

}
