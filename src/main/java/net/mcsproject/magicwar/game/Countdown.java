package net.mcsproject.magicwar.game;

import net.mcsproject.magicwar.MagicWar;
import org.bukkit.Bukkit;

public abstract class Countdown {

	private int timer;

	public Countdown(int startTime) {
		this.timer = startTime;

		Bukkit.getScheduler().runTaskTimer(MagicWar.getInstance(), () -> {

		}, 0L, 20L);
	}

	public abstract void sendMessage();

}
