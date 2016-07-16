package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import org.bukkit.Bukkit;

public class RestartingCountdown extends Countdown {

	public RestartingCountdown() {
		super(15, true);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
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
