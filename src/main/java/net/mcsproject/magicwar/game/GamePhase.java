package net.mcsproject.magicwar.game;

import net.mcsproject.magicwar.game.phases.*;

import java.lang.reflect.InvocationTargetException;

public enum GamePhase {
	LOBBY(LobbyCountdown.class),
	PREGAME(PregameCountdown.class),
	INGAME(IngameCountdown.class),
	DEATHMATCH(DeathmatchCountdown.class),
	RESTARTING(RestartingCountdown.class);

	private Countdown countdown;

	GamePhase(Class<? extends Countdown> countdown) {
		try {
			this.countdown = countdown.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Countdown getCountdown() {
		return this.countdown;
	}

	public static GamePhase fromOrdinal(int n) {
		return values()[n];
	}
}
