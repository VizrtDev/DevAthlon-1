package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.game.magic.MagicCauldron;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.CAULDRON) {
			MagicCauldron.getInstance().removeCauldron(event.getBlock().getLocation());
		}
	}

}
