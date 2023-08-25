package com.github.cawtoz.style.util.menu.pagination.button;

import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.menu.pagination.PaginatedMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@Getter @AllArgsConstructor
public class JumpToPageButton extends Button {

	private int page;
	private PaginatedMenu menu;
	private boolean current;
	
	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder itemBuilder = new ItemBuilder(current ? Material.ENCHANTED_BOOK : Material.BOOK);
		itemBuilder.setName("&aPage " + page);
		itemBuilder.setAmount(page);
		
		if(current) {
			itemBuilder.setLore(
					"",
					" &eCurrent page",
					""
					);
		}

		return itemBuilder.build();
	}

	@Override
	public void clicked(Player player, int i, ClickType clickType, int hb) {
		menu.modPage(player, page - menu.getPage());
		Button.playNeutral(player);
	}
}
