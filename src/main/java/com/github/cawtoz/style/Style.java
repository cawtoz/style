package com.github.cawtoz.style;

import com.github.cawtoz.style.command.CommandManager;
import com.github.cawtoz.style.cosmetic.CosmeticTask;
import com.github.cawtoz.style.listener.EventManager;
import com.github.cawtoz.style.cosmetic.CosmeticUtil;
import com.github.cawtoz.style.util.License;
import com.github.cawtoz.style.util.file.FileManager;
import com.github.cawtoz.style.util.menu.runnable.MenuUpdateRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Style extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!new License().getEnabled().equals("&aEnabled")) {
            getPluginLoader().disablePlugin(this);
            return;
        }
        FileManager.loadFiles();
        CommandManager.register();
        EventManager.register();
        CosmeticTask.getTask().runTaskTimer(this, 0, 1L);
        getServer().getScheduler().runTaskTimer(this, new MenuUpdateRunnable(), 10L, 10L);
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(CosmeticUtil::destroyCosmetics);
    }

    public static Style getInstance() {
        return getPlugin(Style.class);
    }

}
