package com.github.cawtoz.style.util.file;

import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.util.XMaterial;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Simple utility to manage configuration files
 * @author cawtoz
 */

@UtilityClass
public class FileUtil {

    public List<Color> getColors(String fileName, String path) {

        if (!fileName.equals("colors")) {
            path = FileUtil.getString(fileName, path) + ".COLORS";
        }

        List<Color> colors = new ArrayList<>();

        for (String color : FileUtil.getStringList("colors", path)) {
            String[] values = color.split("-");
            if (values.length != 3) continue;
            colors.add(Color.fromRGB(
                    Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    Integer.parseInt(values[2])
            ));
        }

        return colors;
    }

    public Material getMaterial(String fileName, String path) {
        return XMaterial.matchXMaterial(FileManager.getFile(fileName).getString(path)).orElse(XMaterial.AIR).parseMaterial();
    }

    public String getString(String fileName, String path) {
        return FileManager.getFile(fileName).getString(path);
    }

    public String getString(String fileName, String path, String target, String replacement) {
        return FileManager.getFile(fileName).getString(path).replace(target, replacement);
    }

    public List<String> getStringList(String fileName, String path) {
        return FileManager.getFile(fileName).getStringList(path);
    }

    public List<String> getStringList(String fileName, String path, String target, String replacement) {
        List<String> messages = FileManager.getFile(fileName).getStringList(path);
        List<String> modifiedMessages = new ArrayList<>();
        FileManager.getFile(fileName).getStringList(path).forEach(line -> {
            modifiedMessages.add(line.replace(target, replacement));
        });
        return modifiedMessages;
    }


    public boolean getBoolean(String fileName, String path) {
        return FileManager.getFile(fileName).getBoolean(path);
    }

    public int getInt(String fileName, String path) {
        return FileManager.getFile(fileName).getInt(path);
    }

    public double getDouble(String fileName, String path) {
        return FileManager.getFile(fileName).getDouble(path);
    }

    public boolean contains(String fileName, String path) {
        return FileManager.getFile(fileName).contains(path);
    }

    public void set(String fileName, String path, Object value) {

        File file = new File(Style.getInstance().getDataFolder(), fileName + ".yml");
        FileManager.getFile(fileName) .set(path, value);
        try {
            FileManager.getFile(fileName).save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getKeys(String fileName) {
        return new ArrayList<>(FileManager.getFile(fileName).getKeys(false));
    }

    public Set<String> getKeys(String fileName, String path) {
        File file = new File(Style.getInstance().getDataFolder(), fileName);
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = yamlConfiguration.createSection(path);
        if (section == null) return null;
        return section.getKeys(false);
    }

}
