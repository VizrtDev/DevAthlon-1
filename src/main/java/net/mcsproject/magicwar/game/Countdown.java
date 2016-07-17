package net.mcsproject.magicwar.game;

import lombok.Getter;
import net.mcsproject.magicwar.MagicWar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown {

	@Getter
	private int time;
	private int startTime;
	private BukkitTask task;

	@Getter
	private boolean started = false;
	private boolean enableXP = true;

	public Countdown(int startTime, boolean xp) {
		this.time = startTime;
		this.startTime = startTime;
		this.enableXP = xp;
		this.onInit();
	}

	public void start() {
		this.onStart();
		this.started = true;

		this.task = Bukkit.getScheduler().runTaskTimer(MagicWar.getInstance(), () -> {
			if (this.time == -1) {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.setLevel(0);
					p.setExp((float) 0.000001);
				});
				Countdown.this.task.cancel();
				this.onEnd();
				int next = MagicWar.getInstance().getGamePhase().ordinal() + 1;
				if (next == GamePhase.values().length) {
					Bukkit.getServer().shutdown();
					return;
				}
				GamePhase phase = GamePhase.fromOrdinal(next);
				MagicWar.getInstance().setGamePhase(phase);
				phase.getCountdown().start();
				return;
			}

			Bukkit.getOnlinePlayers().forEach(p -> {
				if (enableXP) {
					p.setLevel(time);
					p.setExp((float) time / startTime);
				}
				this.sendMessage(p);
			});

			this.onTick();

			time--;
		}, 0L, 20L);
	}

	public void stop() {
		if (this.task != null) {
			this.task.cancel();
			this.task = null;
		}
	}

	public abstract void sendMessage(Player p);

	public abstract void onTick();

	public abstract void onInit();

	public abstract void onStart();

	public abstract void onEnd();

}
