package net.mcsproject.magicwar.game.listener.pregame;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getFrom().getBlockX() == e.getFrom().getBlockX() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) {
			return;
		}
		e.setCancelled(true);
	}

}
