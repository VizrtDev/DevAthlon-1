package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntityType() == EntityType.PLAYER)
			e.setDeathMessage(ChatUtils.fromConfig("playerdeath").replaceAll("%p", e.getEntity().getDisplayName()).replaceAll("%k", e.getEntity().getKiller().getDisplayName()));
		e.getEntity().setGameMode(GameMode.SPECTATOR);
		e.getEntity().sendMessage("Du bist nun Zuschauer"); //TODO config
	}
}
