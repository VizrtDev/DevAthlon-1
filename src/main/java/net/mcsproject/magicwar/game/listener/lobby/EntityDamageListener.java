package net.mcsproject.magicwar.game.listener.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
}
