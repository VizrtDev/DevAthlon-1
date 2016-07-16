package net.mcsproject.magicwar.game.commands;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.db.obj.DatabaseStatistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if (args.length == 1) {
			MagicWar.getInstance().getMongodb().getConnection().getPlayer(args[0], p -> {
				if (p == null) {
					commandSender.sendMessage("§cDer Spieler war noch nie auf diesem Server!");
					return;
				}
				MagicWar.getInstance().getMongodb().getConnection().getStatistic(p.getUuid(), statistic -> this.sendStats(commandSender, args[0], statistic));
			});
			return true;
		}
		if (commandSender instanceof Player) {
			Player p = (Player) commandSender;
			MagicWar.getInstance().getMongodb().getConnection().getStatistic(p.getUniqueId().toString(), statistic -> this.sendStats(commandSender, p.getName(), statistic));
		}
		return true;
	}

	private void sendStats(CommandSender commandSender, String s, DatabaseStatistic statistic) {
		commandSender.sendMessage("§8----------------------------------");
		commandSender.sendMessage("§7Stats von §" + s);
		commandSender.sendMessage("§7- Kills: §e" + statistic.getKills());
		commandSender.sendMessage("§7- Deaths: §e" + statistic.getDeaths());
		commandSender.sendMessage("§7- K/D: §e" + Math.round(statistic.getKills() / statistic.getDeaths() * 10) / 10);
		commandSender.sendMessage("§7- Wins: §e" + statistic.getWins());
		commandSender.sendMessage("§7- Played games: §e" + statistic.getPlayedGames());
		commandSender.sendMessage("§7- Win ratio: §e" + Math.round(statistic.getWins() / statistic.getPlayedGames() * 100) + "%");
		commandSender.sendMessage("§8----------------------------------");
	}

}
