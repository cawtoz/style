package com.github.cawtoz.style.util.menu.pagination.button;

import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.menu.button.Button;
import com.github.cawtoz.style.util.menu.pagination.PaginatedMenu;
import com.github.cawtoz.style.util.menu.pagination.ViewAllPagesMenu;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class PageButton extends Button {

	private int mod;
	private PaginatedMenu menu;

	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder itemBuilder = new ItemBuilder(Material.ARROW);

		if (hasNext(player)) {
			itemBuilder.setName(mod > 0 ? "&aNext Page" : "&cPrevious Page");
		} else {
			itemBuilder.setName(mod > 0 ? "&aLast Page" : "&aFirst Page");
		}

		itemBuilder.setLore(
				"",
				" &eRight click to",
				" &ejump to a page.",
				""
				);
		
		return itemBuilder.build();
	}

	@Override
	public void clicked(Player player, int i, ClickType clickType, int hb) {
		if (clickType.equals(ClickType.RIGHT)) {
			new ViewAllPagesMenu(menu).openMenu(player);
			playNeutral(player);
		} else {
			if (hasNext(player)) {
				menu.modPage(player, mod);
				Button.playNeutral(player);
			} else {
				Button.playFail(player);
			}
		}
		
	}

	private boolean hasNext(Player player) {
		int pg = menu.getPage() + mod;
		return pg > 0 && menu.getPages(player) >= pg;
	}

}
