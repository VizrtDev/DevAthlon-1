package net.mcsproject.magicwar.game;

import net.mcsproject.magicwar.game.phases.*;

import java.lang.reflect.InvocationTargetException;

public enum GamePhase {
	LOBBY(LobbyCountdown.class),
	PREGAME(PregameCountdown.class),
	INGAME(IngameCountdown.class),
	DEATHMATCH(DeathmatchCountdown.class),
	RESTARTING(RestartingCountdown.class);

	private Class<? extends Countdown> countdownClass;
	private Countdown countdown;

	GamePhase(Class<? extends Countdown> countdown) {
		this.countdownClass = countdown;
	}

	public Countdown getCountdown() {
		if (this.countdown == null) {
			try {
				this.countdown = countdownClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return this.countdown;
	}

	public static GamePhase fromOrdinal(int n) {
		return values()[n];
	}
}
