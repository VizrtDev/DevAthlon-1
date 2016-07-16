package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.GamePhase;
import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (false) //TODO
			e.setDeathMessage(ChatUtils.fromConfig("playerdeath").replaceAll("%p", e.getEntity().getDisplayName()).replaceAll("%k", e.getEntity().getKiller().getDisplayName()));
		e.getEntity().setGameMode(GameMode.SPECTATOR);
		e.getEntity().sendMessage("Du bist nun Zuschauer"); //TODO config

		if (Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).count() == 1) {
			Player winner = Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).findFirst().get();
			Bukkit.broadcastMessage(ChatUtils.fromConfig("winner").replaceAll("%p", winner.getDisplayName()));
			MagicWar.getInstance().setGamePhase(GamePhase.RESTARTING);
		}
	}
}
