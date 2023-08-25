package com.github.cawtoz.style.listener;

import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.util.menu.MenuListener;
import lombok.experimental.UtilityClass;
import org.bukkit.event.Listener;

@UtilityClass
public class EventManager {

    public void register() {
        addListener(new PlayerListener());
        addListener(new InventoryListener());
        addListener(new MenuListener(Style.getInstance()));
    }

    public void addListener(Listener listener) {
        Style.getInstance().getServer().getPluginManager().registerEvents(listener, Style.getInstance());
    }

}
