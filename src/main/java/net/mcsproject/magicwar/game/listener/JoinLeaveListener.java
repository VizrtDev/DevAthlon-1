package net.mcsproject.magicwar.game.listener;

import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatUtils.fromConfig("playerjoin").replaceAll("%p", e.getPlayer().getDisplayName()));
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatUtils.fromConfig("playerquit").replaceAll("%p", e.getPlayer().getDisplayName()));
	}
}
