package net.mcsproject.magicwar.game.listener;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.GamePhase;
import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatUtils.fromConfig("playerjoin").replaceAll("%p", e.getPlayer().getDisplayName()));

		e.getPlayer().getInventory().clear();
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		e.getPlayer().setHealth(20);
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setTotalExperience(0);

		e.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation());

		/*DatabasePlayer databasePlayer = new DatabasePlayer();
		databasePlayer.setUuid(e.getPlayer().getUniqueId().toString());
		databasePlayer.setLastName(e.getPlayer().getName());
		databasePlayer.setLastNameLowercase(e.getPlayer().getName().toLowerCase());
		MagicWar.getInstance().getMongodb().getConnection().updatePlayer(databasePlayer);*/

		e.getPlayer().sendMessage("Dieses Plugin ist nur ein proof of concept, kein vollwertiges Spiel!");

		if (MagicWar.getInstance().getGamePhase() == GamePhase.LOBBY && !MagicWar.getInstance().getGamePhase().getCountdown().isStarted()
				&& Bukkit.getOnlinePlayers().size() > 0) {
			MagicWar.getInstance().getGamePhase().getCountdown().start();
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatUtils.fromConfig("playerquit").replaceAll("%p", e.getPlayer().getDisplayName()));

		if (MagicWar.getInstance().getGamePhase() != GamePhase.LOBBY && Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).count() == 1) {
			Player winner = Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).findFirst().get();
			Bukkit.broadcastMessage(ChatUtils.fromConfig("winner").replaceAll("%p", winner.getDisplayName()));
			MagicWar.getInstance().setGamePhase(GamePhase.RESTARTING);
		}
	}
}
