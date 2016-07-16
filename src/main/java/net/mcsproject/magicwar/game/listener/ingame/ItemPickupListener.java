package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.game.magic.ItemValues;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemPickupListener implements Listener {
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if (!e.getItem().getItemStack().getItemMeta().hasLore()) {
			e.getPlayer().getInventory().addItem(new ItemModifier(e.getItem().getItemStack()).lore("Wert§7: §e" + ItemValues.valueOf(e.getItem().getItemStack().getType().toString()).getValue()).get());
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 0);
			e.setCancelled(true);
			e.getItem().remove();
			e.getItem().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 5);
		}
	}
}
