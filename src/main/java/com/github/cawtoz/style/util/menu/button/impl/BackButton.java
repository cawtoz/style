package com.github.cawtoz.style.util.menu.button.impl;

import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.menu.Menu;
import com.github.cawtoz.style.util.menu.button.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackButton extends Button {
	
    private Menu back;
    private ItemStack backItem;
    
    public BackButton(Menu back) {
        this.back = back;
        this.backItem = new ItemStack(Material.BED);
    }
    
    public BackButton(Menu back, ItemStack backItem) {
		this.back = back;
		this.backItem = backItem;
	}
    
    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(backItem)
        		.setName("&c&lBack")
        		.setLore(
        				"",
        				" &eClick here to return to",
        				" &ethe previous menu.",
        				""
        				)
        		.build();
    }
    
    @Override
    public void clicked(Player player, ClickType clickType) {
        Button.playNeutral(player);
        back.openMenu(player);
    }
}
