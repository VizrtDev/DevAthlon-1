package net.mcsproject.magicwar.game.listener;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.GamePhase;
import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatUtils.fromConfig("playerjoin").replaceAll("%p", e.getPlayer().getDisplayName()));

		e.getPlayer().getInventory().clear();

		if (MagicWar.getInstance().getGamePhase() == GamePhase.LOBBY && !MagicWar.getInstance().getGamePhase().getCountdown().isStarted()
				&& Bukkit.getOnlinePlayers().size() > 1) {
			MagicWar.getInstance().getGamePhase().getCountdown().start();
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatUtils.fromConfig("playerquit").replaceAll("%p", e.getPlayer().getDisplayName()));
	}
}
