package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.magic.ItemValues;
import net.mcsproject.magicwar.game.magic.MagicCauldron;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDropListener implements Listener {
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getItemDrop().getFallDistance() < 1) {
					if (e.getItemDrop().getLocation().getBlock().getType() == Material.CAULDRON) {
						BlockState caul = e.getItemDrop().getLocation().getBlock().getState();
						MaterialData cd = caul.getData();
						if (cd.getData() < 3) {
							cd.setData((byte) (cd.getData() + 1));
							caul.update();

							MagicCauldron.getInstance().addValue(e.getItemDrop().getLocation().getBlock().getLocation(),
									ItemValues.valueOf(e.getItemDrop().getItemStack().getType().toString()).getValue());

							e.getItemDrop().remove();
						}
					}
					this.cancel();
				}
			}
		}.runTaskTimer(MagicWar.getInstance(), 10L, 10L);
	}
}
