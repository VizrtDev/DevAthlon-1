package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MagicBook extends MagicalItem {

	private Inventory inventory;

	public MagicBook(HashMap<Integer, MagicalItem> hashMap) {
		super(new ItemModifier(new ItemStack(Material.ENCHANTED_BOOK)).name("§cMagic Book").get());

		this.inventory = Bukkit.createInventory(null, 9, "§cMagic Book");

		for (Map.Entry<Integer, MagicalItem> entry : hashMap.entrySet()) {
			this.inventory.addItem(new ItemModifier(entry.getValue().getItem()).lore("Wert§7: §e" + entry.getKey()).get());
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		e.getPlayer().openInventory(this.inventory);
	}

}
