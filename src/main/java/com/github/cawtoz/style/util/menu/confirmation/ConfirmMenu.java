package com.github.cawtoz.style.util.menu.confirmation;

import com.github.cawtoz.style.util.menu.Menu;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.menu.confirmation.button.ConfirmButton;
import com.github.cawtoz.style.util.menu.util.Callback;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
public class ConfirmMenu extends Menu {
	
    private String title;
    private Callback<Boolean> response;
    private boolean closeAfterResponse;
    private Button centerButton;
    
    @Override
    public String getTitle(Player player) {
        return this.title;
    }
    
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        
        buttons.put(1, new ConfirmButton(true, response, closeAfterResponse));
        
        if (centerButton != null) {
        	buttons.put(4, centerButton);
        }
        
        buttons.put(7, new ConfirmButton(false, response, closeAfterResponse));
        
        return buttons;
    }
}
