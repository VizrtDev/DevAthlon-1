package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RestartingCountdown extends Countdown {

	public RestartingCountdown() {
		super(15, true);
	}

	@Override
	public void sendMessage(Player p) {
		if (getTime() % 5 == 0 || getTime() <= 5) {
			p.sendMessage("§7» §aDer Server startet neu in " + getTime() + " Sekunden");
		}
	}

	@Override
	public void onTick() {

	}

	@Override
	public void onInit() {

	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
		Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer("Spielende"));
	}

}
