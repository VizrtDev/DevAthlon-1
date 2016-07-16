package net.mcsproject.magicwar.game;

import net.mcsproject.magicwar.MagicWar;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown {

	private int timer;
	private final BukkitTask task;

	public Countdown(int startTime) {
		this.timer = startTime;

		this.onStart();

		this.task = Bukkit.getScheduler().runTaskTimer(MagicWar.getInstance(), () -> {
			if (this.timer == 0) {
				Countdown.this.task.cancel();
				int next = MagicWar.getInstance().getGamePhase().ordinal() + 1;
				if (next == GamePhase.values().length - 1) {
					Bukkit.getServer().shutdown();
					return;
				}
				MagicWar.getInstance().setGamePhase(GamePhase.fromOrdinal(next));
				return;
			}

			this.sendMessage();

			timer--;
		}, 0L, 20L);

		this.onEnd();
	}

	public abstract void sendMessage();

	public abstract void onStart();

	public abstract void onEnd();

}
