package com.github.cawtoz.style.util.menu.confirmation.button;

import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.menu.Menu;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.menu.util.Callback;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class ConfirmButton extends Button {
	
    private boolean confirm;
    private Callback<Boolean> callback;
    private boolean closeAfterResponse;
    
    @Override
    public ItemStack getButtonItem(Player player) {
    	ItemBuilder itemBuilder = new ItemBuilder(Material.WOOL);
    	itemBuilder.setName(confirm ? "&a&lConfirm" : "&c&lCancel");
    	itemBuilder.setDurability(confirm ? 5 : 14);

        return itemBuilder.build();
    }
    
    @Override
    public void clicked(Player player, ClickType clickType) {
        if (confirm) {
            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0f, 0.1f);
        } else {
            player.playSound(player.getLocation(), Sound.DIG_GRAVEL, 20.0f, 0.1f);
        }
        
        if (closeAfterResponse) {
            Menu menu = Menu.currentlyOpenedMenus.get(player.getName());
            if (menu != null) {
                menu.setClosedByMenu(true);
            }
            
            player.closeInventory();
        }
        
        callback.callback(this.confirm);
    }
}
