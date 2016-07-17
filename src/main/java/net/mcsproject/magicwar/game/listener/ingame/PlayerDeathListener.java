package net.mcsproject.magicwar.game.listener.ingame;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.db.obj.DatabaseStatistic;
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
		if (e.getEntity().getKiller() != null) {
			e.setDeathMessage(ChatUtils.fromConfig("playerdeath").replaceAll("%p", e.getEntity().getDisplayName()).replaceAll("%k", e.getEntity().getKiller().getDisplayName()));
			/*this.addDeath(e.getEntity());
			MagicWar.getInstance().getMongodb().getConnection().getStatistic(e.getEntity().getKiller().getUniqueId().toString(), s -> {
				if (s == null) {
					s = new DatabaseStatistic();
					s.setUuid(e.getEntity().getUniqueId().toString());
				}
				s.setKills(s.getKills() + 1);
				s.setPlayedGames(s.getPlayedGames() + 1);
				MagicWar.getInstance().getMongodb().getConnection().updateStatistic(s, DatabaseStatistic.AccessRule.KILLS, DatabaseStatistic.AccessRule.PLAYED_GAMES);
			});*/
		} else {
			//this.addDeath(e.getEntity());
		}
		e.getEntity().setGameMode(GameMode.SPECTATOR);
		e.getEntity().sendMessage("Du bist nun Zuschauer"); //TODO config

		if (Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).count() < 2) {
			Player winner = Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == GameMode.SURVIVAL).findFirst().get();
			/*MagicWar.getInstance().getMongodb().getConnection().getStatistic(winner.getUniqueId().toString(), s -> {
				if (s == null) {
					s = new DatabaseStatistic();
					s.setUuid(winner.getUniqueId().toString());
				}
				s.setWins(s.getWins() + 1);
				s.setPlayedGames(s.getPlayedGames() + 1);
				MagicWar.getInstance().getMongodb().getConnection().updateStatistic(s, DatabaseStatistic.AccessRule.WINS, DatabaseStatistic.AccessRule.PLAYED_GAMES);
			});*/
			Bukkit.broadcastMessage(ChatUtils.fromConfig("winner").replaceAll("%p", winner.getDisplayName()));
			MagicWar.getInstance().setGamePhase(GamePhase.RESTARTING);
		}
	}

	private void addDeath(Player p) {
		MagicWar.getInstance().getMongodb().getConnection().getStatistic(p.getUniqueId().toString(), s -> {
			if (s == null) {
				s = new DatabaseStatistic();
				s.setUuid(p.getUniqueId().toString());
			}
			s.setDeaths(s.getDeaths() + 1);
			s.setPlayedGames(s.getPlayedGames() + 1);
			MagicWar.getInstance().getMongodb().getConnection().updateStatistic(s, DatabaseStatistic.AccessRule.DEATHS, DatabaseStatistic.AccessRule.PLAYED_GAMES);
		});
	}

}
