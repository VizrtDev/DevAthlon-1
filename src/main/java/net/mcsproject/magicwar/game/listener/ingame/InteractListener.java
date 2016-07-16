package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.game.magic.MagicCauldron;
import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.game.magic.MagicalItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;

public class InteractListener implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.CAULDRON) {
				int value = MagicCauldron.getInstance().getValue(e.getClickedBlock().getLocation());
				if (value == 0) {
					return;
				}

				MagicalItem item = MagicalItems.getInstance().getByValue(value);

				BlockState bs = e.getClickedBlock().getState();
				MaterialData md = bs.getData();
				md.setData((byte) 0);
				bs.update();

				MagicCauldron.getInstance().removeCauldron(e.getClickedBlock().getLocation());
				if (item != null) {
					e.getPlayer().getInventory().addItem(item.getItem());
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 1);
				} else {
					e.getPlayer().sendMessage("§cEs gibt kein Item mit diesem Wert!");
				}
			}
		}
	}
}
